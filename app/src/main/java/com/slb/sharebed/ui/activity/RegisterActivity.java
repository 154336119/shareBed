package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jaeger.library.StatusBarUtil;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.ui.contract.BindPhoneContract;
import com.slb.sharebed.ui.contract.RegisterContract;
import com.slb.sharebed.ui.presenter.BindPhonePresenter;
import com.slb.sharebed.ui.presenter.RegisterPresenter;
import com.slb.sharebed.weight.CountTimerButton;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseMvpActivity<RegisterContract.IView, RegisterContract.IPresenter>
        implements RegisterContract.IView {
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtVCode)
    EditText edtVCode;
    @BindView(R.id.BtnGetCode)
    CountTimerButton BtnGetCode;
    @BindView(R.id.btnRegister)
    ImageView btnRegister;
    @Override
    protected String setToolbarTitle() {
        return "注册";
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
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
    public RegisterContract.IPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.BtnGetCode, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnGetCode:
                mPresenter.getCode(edtMobile.getText().toString());
                break;
            case R.id.btnRegister:
                mPresenter.login(edtMobile.getText().toString(), edtVCode.getText().toString());
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
