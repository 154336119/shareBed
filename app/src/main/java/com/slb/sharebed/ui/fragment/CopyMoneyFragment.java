package com.slb.sharebed.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyApplication;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.event.RefreshUserInfoEvent;
import com.slb.sharebed.http.bean.PayEntity;
import com.slb.sharebed.http.bean.PayResult;
import com.slb.sharebed.http.bean.WebBean;
import com.slb.sharebed.ui.activity.LoginActivity;
import com.slb.sharebed.ui.activity.MainActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;
import com.slb.sharebed.ui.contract.WebViewFragmentContract;
import com.slb.sharebed.ui.presenter.WebViewFragmentPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.slb.sharebed.MyConstants.url_token;

public class CopyMoneyFragment extends BaseMvpFragment<WebViewFragmentContract.IView, WebViewFragmentContract.IPresenter>
        implements WebViewFragmentContract.IView {
    Unbinder unbinder;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String url;
    private String title;
    private int isShare = 0;
    private UMWeb mUMWeb;

    private int mPayType; //支付什么类型 1,支付宝，2，微信
    private String mOrderCode;
    private static final int SDK_PAY_FLAG = 1;


    public static CopyMoneyFragment newInstance() {
        CopyMoneyFragment fragment = new CopyMoneyFragment();
        return fragment;
    }

    @Override
    public WebViewFragmentContract.IPresenter initPresenter() {
        return new WebViewFragmentPresenter();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(WebViewActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        toPaySuccessActivity();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        toPayFaildActivity();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };



    @Override
    public int getLayoutId() {
        return R.layout.fragment_money;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if(isShare == 1){
//            getMenuInflater().inflate(R.menu.menu_share, menu);
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        showShareDialog(mUMWeb);
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void getIntentExtras() {
//        super.getIntentExtras();
//        title = getIntent().getStringExtra("title");
//        url = getIntent().getStringExtra("url");
//        Logger.d("========url:"+url);
//        isShare = getIntent().getIntExtra("isShare",0);
//        if(isShare == 1){
//            UMWeb web = new UMWeb(getIntent().getStringExtra("shareUrl")+"&ifshare=2");
//            web.setTitle(getIntent().getStringExtra("shareTitle"));
//            web.setDescription(getIntent().getStringExtra("shareSubTitle"));
//            web.setThumb(new UMImage(WebViewActivity.this,getIntent().getStringExtra("shareLogo")) );
//            mUMWeb = web;
//        }
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mWebView == null) {
//            return;
//        }
//        mWebView.onResume() ;
//        BaseResp resp = MyApplication.getInstance().getResp();
//        if (resp != null) {
//            if (resp.errCode == 0) {
//                //微信支付成功
//                MyApplication.getInstance().setResp(null);
//                toPaySuccessActivity();
//            } else {
//                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                MyApplication.getInstance().setResp(null);
//                toPayFaildActivity();
//            }
//        }
//    }
    @Override
    protected String setToolbarTitle() {
        return title;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        StatusBarUtil.setTransparentForImageView(_mActivity, null);
        StatusBarUtil.setLightMode(_mActivity);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultFontSize((int) getResources().getDimension(R.dimen.tv15));
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false); //设置内置的缩放控件。
        settings.setSupportMultipleWindows(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setGeolocationEnabled(true);
        String dir = _mActivity.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);

        mWebView.setLongClickable(true);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
//              return shouldOverrideUrlByMJ(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(_mActivity);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

            //设置响应js 的Confirm()函数
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(_mActivity);
                b.setTitle("Confirm");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                b.create().show();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(progressbar!=null){
                    if (newProgress != 100) {
                        progressbar.setVisibility(View.VISIBLE);
                    } else {
                        progressbar.setVisibility(View.GONE);
                    }
                }
            }
        });
        mWebView.addJavascriptInterface(new JavaScriptObject(), "jsAndroid");
        String UR =MyConstants.h5Url + MyConstants.url_wallet + Base.getUserEntity().getToken();
        mWebView.loadUrl(MyConstants.h5Url + MyConstants.url_wallet + Base.getUserEntity().getToken());
        return rootView;
    }

    private class JavaScriptObject {
        @JavascriptInterface
        public void appPush(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Bundle bundle = new Bundle();
            if(data.ifToken == 1){
                if(data.url.contains("?")){
                    bundle.putString("url", MyConstants.h5Url + data.url + url_token + Base.getUserEntity().getToken());
                }else{
                    bundle.putString("url",MyConstants.h5Url + data.url + "?token=" + Base.getUserEntity().getToken());
                }
            }else{
                bundle.putString("url",MyConstants.h5Url + data.url);
            }
            bundle.putString("title",data.title);
//            bundle.putInt("isShare",data.isShare);

            if(!TextUtils.isEmpty(data.shareTitle)){
                bundle.putString("shareTitle",data.shareTitle);
            }
            if(!TextUtils.isEmpty(data.shareSubTitle)){
                bundle.putString("shareSubTitle",data.shareSubTitle);
            }
            if(!TextUtils.isEmpty(data.shareUrl)){
                bundle.putString("shareUrl",data.shareUrl);
            }
            if(!TextUtils.isEmpty(data.shareLogo)){
                bundle.putString("shareLogo",data.shareLogo);
            }
            //选择支付方式
//            RxBus.get().post(new FinishAcitivtyEvent());
//            ActivityUtil.next(WebViewActivity.this, ChoisePayTypeActiivty.class);
            ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,100);
        }



        @JavascriptInterface
        public void appReturn(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Intent intent = new Intent();
            if(!TextUtils.isEmpty(data.type)){
                intent.putExtra("type",data.type);
            }
            if(!TextUtils.isEmpty(data.addressId)){
                intent.putExtra("addressId",data.addressId);
            }

            intent.putExtra("isRefresh", data.isRefresh);
//            setResult(100, intent);
//            finish();
        }

        @JavascriptInterface
        public void appShare(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);

            UMImage image = new UMImage(_mActivity, R.mipmap.logo);
            final UMWeb web = new UMWeb(data.url);
            web.setTitle(data.title);
            web.setThumb(image);
            web.setDescription(data.content);
        }

        @JavascriptInterface
        public void appClose(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
//            finish();
//            finish();
        }

        @JavascriptInterface
        public void appLink(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            //linkType  首页：index，个人中心：my , 钱包：wallet ,登录：login） 。
            if ("index".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,true);
                RxBus.get().post(new RefreshUserInfoEvent());
            } else if ("my".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,3);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,true);
                RxBus.get().post(new RefreshUserInfoEvent());
            } else if ("wallet".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,2);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,true);
            } else if ("login".equals(data.linkType)) {
                ActivityUtil.next(_mActivity, LoginActivity.class);
            }
        }


        @JavascriptInterface
        public void appPay(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            mPayType = data.payType;
            mOrderCode = data.orderCode;
            mPresenter.getPayParam(mPayType,mOrderCode);
//            getPay(data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onPause() {
        super.onPause();
//        if (mWebView == null) {
//            return;
//        }
//        mWebView.onPause();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 100){
            if (intent.getBooleanExtra("isRefresh", false)){
                StringBuffer sb = new StringBuffer();
                sb.append(this.url);
                if(!TextUtils.isEmpty(intent.getStringExtra("type"))){
                    sb.append("&type="+intent.getStringExtra("type"));
                }
                if(!TextUtils.isEmpty(intent.getStringExtra("addressId"))){
                    sb.append("&addressId="+intent.getStringExtra("addressId"));
                }
                //发票数据
                if(!TextUtils.isEmpty(intent.getStringExtra("invoiceType"))){
                    sb.append("&invoiceType="+intent.getStringExtra("invoiceType"));
                }
                if(!TextUtils.isEmpty(intent.getStringExtra("invoiceTitle"))){
                    sb.append("&invoiceTitle="+intent.getStringExtra("invoiceTitle"));
                }
                if(!TextUtils.isEmpty(intent.getStringExtra("invoiceTax"))){
                    sb.append("&invoiceTax="+intent.getStringExtra("invoiceTax"));
                }
//                this.url = sb.toString();
                mWebView.loadUrl(sb.toString());
            }
        }
    }
//
//    /**
//     * 测试
//     */
//    public void showShareDialog(final UMWeb web){
//        ShareDialog dialog = new ShareDialog();
//        dialog.setOnButtonClick(new ShareDialog.OnButtonClick() {
//            @Override
//            public void onShare(int type) {
//                SHARE_MEDIA share_media  = SHARE_MEDIA.WEIXIN;
//                if(type == ShareDialog.VX_FRIEND){
//                    share_media = SHARE_MEDIA.WEIXIN;
//                }else if(type == ShareDialog.VX_CIRCLE){
//                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
//                }
//                new ShareAction(WebViewActivity.this)
//                        .setPlatform(share_media)//传入平台
//                        .withMedia(web)
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//
//                            }
//                        })
//                        .share();
//            }
//        });
//        dialog.show(getSupportFragmentManager(), "Dialog");
//    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @Subscribe
    public void finishActivity(FinishAcitivtyEvent event) {
//        finish();
    }


    private void getPay(final WebBean webBean) {
    }
//        Ref.getService().getPayInfo(webBean.mPayType, webBean.mOrderCode)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PayEntity>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(PayEntity data) {
//                        if (data.code == 200) {
//                            if ("1".equals(webBean.mPayType)) {
//                                payAli(data.data.payUrl);
//                            } else {
//                                payWx(data.data);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

    private void payAli(final String payInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(_mActivity);
                Map<String, String> result = alipay.payV2(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * 微信支付
     */
    public void payWx(PayEntity signData) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(_mActivity, null);
        msgApi.registerApp(MyConstants.WX_APP_ID);
        PayReq req = new PayReq();
        req.appId = signData.appid;
        req.partnerId = signData.partnerid;
        req.prepayId = signData.prepayid;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = signData.noncestr;
        req.timeStamp = signData.timestamp;
        req.sign = signData.sign;
        MyApplication.getInstance().setResp(null);
        msgApi.sendReq(req);
    }

    @Override
    public void toPaySuccessActivity() {
//        hideWaitDialog();
        Logger.d("支付成功");
//        Bundle bundle = new Bundle();
//        bundle.putInt(MyConstants.TYPE,TYPE_103);
//        ActivityUtil.next(this, SuccessActivity.class,bundle,true);
//        RxBus.get().post(new OrderRefreshEvent());
//        RxBus.get().post(new FinishAcitivtyEvent());
    }

    @Override
    public void toPayFaildActivity() {
        Logger.d("支付失败");
//        Bundle bundle = new Bundle();
//        bundle.putInt("POS",0);
//        ActivityUtil.next(this, OrderListActiivty.class,bundle,true);
//        RxBus.get().post(new OrderRefreshEvent());
//        RxBus.get().post(new FinishAcitivtyEvent());
    }

    @Override
    public void getPayParamSuccess(PayEntity entity) {
        if (mPayType == 1) {
            payAli(entity.payUrl);
        } else {
            payWx(entity);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mWebView.loadUrl(MyConstants.h5Url + MyConstants.url_wallet + Base.getUserEntity().getToken());
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            mWebView.loadUrl(MyConstants.h5Url + MyConstants.url_wallet + Base.getUserEntity().getToken());
        }
    }
}

