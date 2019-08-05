package com.slb.sharebed.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hwangjr.rxbus.RxBus;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.event.BedFinishEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;

/**
 * 结束用床 弹窗
 */
public class FinshBedDialogAcitivty extends BaseActivity {
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
        return R.layout.dialog_finish_bed;
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
                //结束用床
                RxBus.get().post(new BedFinishEvent());
                finish();
                break;
            case R.id.BtnRight:
                //重试
                finish();
                break;

        }
    }

}
