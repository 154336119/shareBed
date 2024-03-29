package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jaeger.library.StatusBarUtil;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.ui.contract.BindPhoneContract;
import com.slb.sharebed.ui.contract.LoginContract;
import com.slb.sharebed.ui.contract.RegisterContract;
import com.slb.sharebed.ui.presenter.BindPhonePresenter;
import com.slb.sharebed.ui.presenter.LoginPresenter;
import com.slb.sharebed.ui.presenter.RegisterPresenter;
import com.slb.sharebed.weight.CountTimerButton;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BindPhoneActivity extends BaseMvpActivity<BindPhoneContract.IView, BindPhoneContract.IPresenter>
        implements BindPhoneContract.IView {
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtVCode)
    EditText edtVCode;
    @BindView(R.id.BtnGetCode)
    CountTimerButton BtnGetCode;
    @BindView(R.id.btnRegister)
    ImageView btnRegister;
    String openid =null;
    String type=null;
    String nickName=null;
    String iconurl=null;

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        openid =  getIntent().getStringExtra("openid");
        type =  getIntent().getStringExtra("type");
        nickName =  getIntent().getStringExtra("nickName");
    }

    @Override
    protected String setToolbarTitle() {
        return "绑定手机号";
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        StatusBarUtil.setTransparentForImageView(this, null);
//        StatusBarUtil.setLightMode(this);

    }

    @Override
    public void loginSuccess() {
        RxBus.get().post(new FinishAcitivtyEvent());
        ActivityUtil.next(this, MainActivity.class, null, true);
    }

    @Override
    public BindPhoneContract.IPresenter initPresenter() {
        return new BindPhonePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @OnClick({R.id.BtnGetCode, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnGetCode:
                mPresenter.getCode(edtMobile.getText().toString());
                break;
            case R.id.btnRegister:
                mPresenter.bind(openid,type,nickName,iconurl,edtMobile.getText().toString(),edtVCode.getText().toString(),"1");
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
