package com.slb.sharebed.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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

import static com.slb.sharebed.MyConstants.url_deposit;

/**
 * 未交押金 弹窗
 */
public class LockFaildDialogAcitivty extends BaseActivity {
    @BindView(R.id.IvClose)
    ImageView IvClose;
    @BindView(R.id.BtnLeft)
    Button BtnLeft;
    @BindView(R.id.BtnRight)
    Button BtnRight;

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_lock_fail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.IvClose, R.id.BtnLeft,R.id.BtnRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IvClose:
                finish();
                break;
            case R.id.BtnLeft:
                //打客服
                if(Base.getConfigEntity()!=null && Base.getConfigEntity().getKEFU_TEL()!=null){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + Base.getConfigEntity().getKEFU_TEL().getConfig_value());
                    intent.setData(data);
                    startActivity(intent);
                }
                break;
            case R.id.BtnRight:
                //重试
                finish();
                break;

        }
    }
}
