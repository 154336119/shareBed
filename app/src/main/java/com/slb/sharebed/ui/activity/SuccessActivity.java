package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.http.bean.SuccessTypeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends BaseActivity {
    public static final int TYPE_100 = 100; //注册成功

    @BindView(R.id.TvTitle)
    TextView TvTitle;
    @BindView(R.id.TvContent)
    TextView TvContent;
    @BindView(R.id.Btn)
    Button Btn;
    private int type;
    private SuccessTypeEnum mSuccessTypeEnum;

    @Override
    protected String setToolbarTitle() {
        return mSuccessTypeEnum.getTitle();
    }


    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        TvTitle.setText(mSuccessTypeEnum.getTitleContent());
        TvContent.setText(mSuccessTypeEnum.getContent());
        if(type == TYPE_100){
            Btn.setVisibility(View.GONE);
        }
        Btn.setText(mSuccessTypeEnum.getBtnText());
    }

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        type = getIntent().getIntExtra(MyConstants.TYPE,TYPE_100);
//        type = TYPE_100;
        mSuccessTypeEnum = SuccessTypeEnum.getEnumForType(type);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_success;
    }

    @OnClick(R.id.Btn)
    public void onViewClicked() {
    }
}
