package com.slb.sharebed.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.jaeger.library.StatusBarUtil;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.event.RefreshUserInfoEvent;
import com.slb.sharebed.ui.contract.LoginContract;
import com.slb.sharebed.ui.presenter.LoginPresenter;
import com.slb.sharebed.weight.CountTimerButton;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;


public class LoginActivity extends BaseMvpActivity<LoginContract.IView, LoginContract.IPresenter>
        implements LoginContract.IView {
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtVCode)
    EditText edtVCode;
    @BindView(R.id.BtnGetCode)
    CountTimerButton BtnGetCode;
    @BindView(R.id.btnLogin)
    ImageView btnLogin;
    @BindView(R.id.TvWxLogin)
    TextView TvWxLogin;
    @BindView(R.id.TvQQLogin)
    TextView TvQQLogin;

    private UMShareAPI mShareAPI;

    @Override
    protected String setToolbarTitle() {
        return "登录";
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActivityUtil.next(this,RegisterActivity.class);
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        mShareAPI.setShareConfig(config);
        if (Base.getUserEntity() != null && !TextUtils.isEmpty(Base.getUserEntity().getToken())) {
            ActivityUtil.next(this, MainActivity.class, null, true);
        } 

    }

    @Override
    public void loginSuccess() {
        ActivityUtil.next(this, MainActivity.class, null, true);
    }

    @Override
    public LoginContract.IPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.BtnGetCode, R.id.btnLogin, R.id.TvWxLogin, R.id.TvQQLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnGetCode:
                mPresenter.getCode(edtMobile.getText().toString());
                break;
            case R.id.btnLogin:
                mPresenter.login(edtMobile.getText().toString(), edtVCode.getText().toString());
//                ActivityUtil.next(this,MainActivity.class);
                break;
            case R.id.TvWxLogin:
                mPresenter.thirdLogin(SHARE_MEDIA.WEIXIN, mShareAPI, LoginActivity.this);
                break;
            case R.id.TvQQLogin:
                mPresenter.thirdLogin(SHARE_MEDIA.QQ ,mShareAPI, LoginActivity.this);
                break;
        }
    }


    @Override
    public void varifyCodeSuccess() {

    }

    @Override
    public void showCountdown() {
        BtnGetCode.startCountTimer();
    }
    @PermissionRequestFailedCallback
    private void failed(String[] failedPermissions) {
        showToastMsg("获取权限失败，操作无法完成");
    }

    @Override
    public void toBindPhonePage(Map<String,String> map) {
        Bundle bundle = new Bundle();
        bundle.putString("openid",map.get("uid"));
        bundle.putString("type",map.get("platform"));
        bundle.putString("nickName",map.get("name"));
        bundle.putString("iconurl", map.get("iconurl"));
        ActivityUtil.next(this,BindPhoneActivity.class,bundle,false);
    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }
    @Subscribe
    public void onFinishAcitivtyEvent(FinishAcitivtyEvent event) {
        finish();
    }
}
