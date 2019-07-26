package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.event.RefreshUserInfoEvent;
import com.slb.sharebed.http.bean.SuccessTypeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockSuccessActivity extends BaseActivity {

    @BindView(R.id.TvTitle)
    TextView TvTitle;
    @BindView(R.id.TvContent)
    TextView TvContent;
    @BindView(R.id.Btn)
    Button Btn;

    @Override
    protected String setToolbarTitle() {
        return "解锁成功";
    }


    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        TvContent.setText("已进入计时，计时收费："+Base.getConfigEntity().getBED_SINGLE_PRICE()+"元/小时");
    }

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lock_success;
    }

    @OnClick(R.id.Btn)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putInt(MyConstants.HOME_SELECTED_FRAGMENT,0);
        ActivityUtil.next(this, MainActivity.class,bundle,true);
        finish();
    }
}
