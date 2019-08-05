package com.slb.sharebed.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSecurityActivity extends BaseActivity {
    @BindView(R.id.TvAuthState)
    TextView TvAuthState;
    @BindView(R.id.RlAuth)
    RelativeLayout RlAuth;
    @BindView(R.id.TvPhone)
    TextView TvPhone;
    @BindView(R.id.RlChangePhone)
    RelativeLayout RlChangePhone;
    @BindView(R.id.TvLoginOut)
    TextView TvLoginOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_security;
    }
    @Override
    protected String setToolbarTitle() {
        return "账号安全";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (Base.getUserEntity().getIsIdentified() == 1) {
            //已实名认证
            TvAuthState.setText("已认证");
        }else if(Base.getUserEntity().getIsIdentified() == 3){
            TvAuthState.setText("提交审核");
        } else {
            //未实名认证
            TvAuthState.setText("未认证");
        }


        TvPhone.setText(xinPhone());
    }


    @OnClick({R.id.RlAuth, R.id.RlChangePhone, R.id.TvLoginOut})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.RlAuth:
                if (Base.getUserEntity().getIsIdentified() == 0) {
                    bundle.putString("url", MyConstants.h5Url + MyConstants.url_certification
                            + Base.getUserEntity().getToken());
                    bundle.putString("title","实名认证");
                    ActivityUtil.next(this, WebViewActivity.class,bundle,false);
                }
                break;
            case R.id.RlChangePhone:
                break;
            case R.id.TvLoginOut:
                PushAgent mPushAgent = PushAgent.getInstance(this);
                mPushAgent.deleteAlias("xikeqiche", Base.getUserEntity().getToken(), new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean b, String s) {

                    }
                });
                Base.setUserEntity(null);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                ActivityUtil.next(this,LoginActivity.class,null,true);
                finish();
                break;
        }
    }

    private String xinPhone() {

        String mobile = Base.getUserEntity().getMobile();
        String str = "";
        for (int i = 0; i < mobile.length(); i++) {
            if (i == mobile.length() - 11) {
                str += mobile.charAt(i);
            } else if (i == mobile.length() - 10) {
                str += mobile.charAt(i);
            } else if (i == mobile.length() - 9) {
                str += mobile.charAt(i);
            } else if (i == mobile.length() - 3) {
                str += mobile.charAt(i);
            } else if (i == mobile.length() - 2) {
                str += mobile.charAt(i);
            } else if (i == mobile.length() - 1) {
                str += mobile.charAt(i);
            } else {
                str += "*";
            }
        }
        return str;
    }
}
