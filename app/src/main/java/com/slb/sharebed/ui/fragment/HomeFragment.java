package com.slb.sharebed.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.ui.activity.NoDepositAcitivty;
import com.slb.sharebed.ui.activity.NoIdentifieActivity;
import com.slb.sharebed.ui.activity.ScanAcitivty;
import com.slb.sharebed.ui.contract.HomeContract;
import com.slb.sharebed.ui.presenter.HomePresenter;
import com.slb.sharebed.weight.SecurityDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;


public class HomeFragment
        extends BaseMvpFragment<HomeContract.IView, HomeContract.IPresenter>
        implements HomeContract.IView {

    @BindView(R.id.IvSecurity)
    ImageView IvSecurity;
    @BindView(R.id.IvSetting)
    ImageView IvSetting;
    @BindView(R.id.IvKefu)
    ImageView IvKefu;
    Unbinder unbinder;
    @BindView(R.id.IvSacn)
    ImageView IvSacn;

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    public static HomeFragment newInstance() {
        HomeFragment instance = new HomeFragment();

        return instance;
    }

    @Override
    public HomeContract.IPresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }


    @OnClick({R.id.IvSecurity, R.id.IvSetting, R.id.IvKefu,R.id.IvSacn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IvSecurity:
                SecurityDialog dialog = new SecurityDialog();
                dialog.show(_mActivity.getSupportFragmentManager(), "Dialog");
                break;
            case R.id.IvSetting:
                break;
            case R.id.IvKefu:
                break;
            case R.id.IvSacn:
                if(Base.getUserEntity().getIsDeposit() == 0){
                    //未交押金
                    ActivityUtil.next(_mActivity, NoDepositAcitivty.class);
                    return;
                }
                if(Base.getUserEntity().getIsIdentified() == 0){
                    //未实名认证
                    ActivityUtil.next(_mActivity, NoIdentifieActivity.class);
                    return;
                }

                toScanActivity();
                break;
        }
    }

    @PermissionRequest({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    private void toScanActivity(){
        ActivityUtil.next(_mActivity, ScanAcitivty.class);
    }

    @PermissionRequestFailedCallback
    private void failed(String[] failedPermissions) {
        showToastMsg("获取权限失败，操作无法完成");
    }

}
