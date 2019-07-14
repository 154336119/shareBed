package com.slb.sharebed.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.jaeger.library.StatusBarUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.http.bean.WebBean;
import com.slb.sharebed.weight.ShareDialog;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.slb.sharebed.MyConstants.url_token;


public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String url;
    private String title;
    private int isShare = 0;
    private UMWeb mUMWeb;

    public void setMybackListener(){
        setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                    return;
                }
               finish();
            }
        });
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isShare == 1){
            getMenuInflater().inflate(R.menu.menu_login, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showShareDialog(mUMWeb);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        isShare = getIntent().getIntExtra("isShare",0);
        if(isShare == 1){
            UMWeb web = new UMWeb(getIntent().getStringExtra("shareUrl")+"&ifshare=2");
            web.setTitle(getIntent().getStringExtra("shareTitle"));
            web.setDescription(getIntent().getStringExtra("shareSubTitle"));
            web.setThumb(new UMImage(WebViewActivity.this,getIntent().getStringExtra("shareLogo")) );
            mUMWeb = web;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView == null) {
            return;
        }
        mWebView.onResume();
    }
    @Override
    protected String setToolbarTitle() {
        return title;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.setLightMode(this);
        setMybackListener();
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
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
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
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
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
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
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
                if (newProgress != 100) {
                    progressbar.setVisibility(View.VISIBLE);
                } else {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.addJavascriptInterface(new JavaScriptObject(), "jsAndroid");
        mWebView.loadUrl(url);
    }
    private class JavaScriptObject {
        @JavascriptInterface
        public void appPush(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Bundle bundle = new Bundle();
            if(data.ifToken == 1){
                if(data.url.contains("?")){
                    bundle.putString("url",MyConstants.h5Url + data.url + url_token + Base.getUserEntity().getToken());
                }else{
                    bundle.putString("url",MyConstants.h5Url + data.url + "?token=" + Base.getUserEntity().getToken());
                }
            }else{
                bundle.putString("url",MyConstants.h5Url + data.url);
            }
            bundle.putString("title",data.title);
            bundle.putInt("isShare",data.isShare);

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
            ActivityUtil.next(WebViewActivity.this, WebViewActivity.class,bundle,100);
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
            setResult(100, intent);
            finish();
        }

        @JavascriptInterface
        public void appShare(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);

            UMImage image = new UMImage(WebViewActivity.this, R.mipmap.logo);
            final UMWeb web = new UMWeb(data.url);
            web.setTitle(data.title);
            web.setThumb(image);
            web.setDescription(data.content);
        }

        @JavascriptInterface
        public void appClose(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            finish();
            finish();
        }

        @JavascriptInterface
        public void appLink(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            //linkType（首页：index，个人中心：my , 上传凭证：upProve ,登录：login） 。
            if ("index".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
                ActivityUtil.next(WebViewActivity.this, MainActivity.class,bundle,true);
                finish();
            } else if ("my".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,2);
                ActivityUtil.next(WebViewActivity.this, MainActivity.class,bundle,true);
                finish();
            }else if ("login".equals(data.linkType)) {
                ActivityUtil.next(WebViewActivity.this, LoginActivity.class);
                finish();
            }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView == null) {
            return;
        }
        mWebView.onPause();
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
//                this.url = sb.toString();
                mWebView.loadUrl(sb.toString());
              }
            }
        }

    /**
     * 测试
     */
    public void showShareDialog(final UMWeb web){
        ShareDialog dialog = new ShareDialog();
        dialog.setOnButtonClick(new ShareDialog.OnButtonClick() {
            @Override
            public void onShare(int type) {
                SHARE_MEDIA share_media  = SHARE_MEDIA.WEIXIN;
                if(type == ShareDialog.VX_FRIEND){
                    share_media = SHARE_MEDIA.WEIXIN;
                }else if(type == ShareDialog.VX_CIRCLE){
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                }
                new ShareAction(WebViewActivity.this)
                        .setPlatform(share_media)//传入平台
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .share();
            }
        });
        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @Subscribe
    public void finishActivity(FinishAcitivtyEvent event) {
        finish();
    }
}
