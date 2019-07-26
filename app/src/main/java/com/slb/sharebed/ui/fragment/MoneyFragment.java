package com.slb.sharebed.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.slb.frame.ui.fragment.BaseFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.http.bean.WebBean;
import com.slb.sharebed.ui.activity.LoginActivity;
import com.slb.sharebed.ui.activity.MainActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.slb.sharebed.MyConstants.url_token;

public class MoneyFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.root)
    FrameLayout root;

    public static MoneyFragment newInstance() {
        MoneyFragment fragment = new MoneyFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_money;
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
//        mWebView.loadUrl(MyConstants.h5Url + MyConstants.url_gouwuche + Base.getUserEntity().getToken());
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private class JavaScriptObject {

        @JavascriptInterface
        public void appPush(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            Bundle bundle = new Bundle();
            if(data.ifToken == 1){
                bundle.putString("url",MyConstants.h5Url + data.url + url_token + Base.getUserEntity().getToken());
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

            ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
        }

        @JavascriptInterface
        public void appClose(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
        }

        @JavascriptInterface
        public void appLink(String json) {
            WebBean data = new Gson().fromJson(json, WebBean.class);
            //linkType（首页：index，个人中心：my , 上传凭证：upProve ,登录：login） 。
            if ("index".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,false);
            } else if ("my".equals(data.linkType)) {
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,2);
                ActivityUtil.next(_mActivity, MainActivity.class,bundle,false);
            }  else if ("login".equals(data.linkType)) {
                ActivityUtil.next(_mActivity, LoginActivity.class);
                _mActivity.finish();
            }

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Logger.d("onHiddenChanged");
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
       if(isVisibleToUser){
           Logger.d("setUserVisibleHint");
       }
    }


}
