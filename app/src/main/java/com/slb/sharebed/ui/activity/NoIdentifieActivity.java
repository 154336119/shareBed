package com.slb.sharebed.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.slb.sharebed.MyConstants.url_certification;
import static com.slb.sharebed.MyConstants.url_deposit;

public class NoIdentifieActivity  extends BaseActivity {
    @BindView(R.id.IvClose)
    ImageView IvClose;
    @BindView(R.id.Btn)
    Button Btn;

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_weijiaoyajin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.IvClose, R.id.Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IvClose:
                finish();
                break;
            case R.id.Btn:
                Bundle bundle = new Bundle();
                bundle.putString("url", MyConstants.h5Url + url_certification
                        + Base.getUserEntity().getToken());
                bundle.putString("title","实名认证");
                ActivityUtil.next(this, WebViewActivity.class,bundle,false);
                finish();
                break;
        }
    }
}
