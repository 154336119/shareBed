package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.ui.contract.LoginContract;
import com.slb.sharebed.ui.presenter.LoginPresenter;
import com.slb.sharebed.weight.CountTimerButton;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.setLightMode(this);
//        StatusBarUtil.setDarkMode(this);
        mShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        mShareAPI.setShareConfig(config);
        if (Base.getUserEntity() != null && !TextUtils.isEmpty(Base.getUserEntity().getToken()) && Base.getUserEntity().getState() == 2) {
            ActivityUtil.next(this, MainActivity.class, null, true);
        } else if (Base.getUserEntity() != null && !TextUtils.isEmpty(Base.getUserEntity().getToken())) {
            mPresenter.getUserInfo(Base.getUserEntity().getToken());
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

    @OnClick({R.id.BtnGetCode, R.id.btnLogin, R.id.TvWxLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnGetCode:
                mPresenter.getCode(edtMobile.getText().toString());
                break;
            case R.id.btnLogin:
                mPresenter.login(edtMobile.getText().toString(), edtVCode.getText().toString());
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

}
