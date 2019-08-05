package com.slb.sharebed.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.TvVersion)
    TextView TvVersion;
    @BindView(R.id.TvPhone)
    TextView TvPhone;
    @BindView(R.id.RlKefu)
    RelativeLayout RlKefu;
    @Override
    protected String setToolbarTitle() {
        return "关于我们";
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        if(Base.getConfigEntity()!=null && Base.getConfigEntity().getKEFU_TEL()!=null) {
            TvPhone.setText(Base.getConfigEntity().getKEFU_TEL().getConfig_value());
        }
    }

    @OnClick(R.id.RlKefu)
    public void onViewClicked() {
        callPhone();
    }

    @PermissionRequest({Manifest.permission.CALL_PHONE})
    private void callPhone() {
        if (Base.getConfigEntity() != null && Base.getConfigEntity().getKEFU_TEL() != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + Base.getConfigEntity().getKEFU_TEL().getConfig_value());
            intent.setData(data);
            startActivity(intent);
        }
    }

    @PermissionRequestFailedCallback
    private void failed(String[] failedPermissions) {
        showToastMsg("获取权限失败，操作无法完成");
    }
}
