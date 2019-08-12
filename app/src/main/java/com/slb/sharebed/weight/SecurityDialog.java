package com.slb.sharebed.weight;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.ui.activity.NoIdentifieActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;

import cn.leo.permission.PermissionRequest;
import cn.leo.permission.PermissionRequestFailedCallback;

import static com.slb.sharebed.MyConstants.url_addLinkman;
import static com.slb.sharebed.MyConstants.url_addLinkman_change;
import static com.slb.sharebed.MyConstants.url_linkman;

@SuppressLint("ValidFragment")
public class SecurityDialog extends BottomSheetDialogFragment {
    public static int VX_CIRCLE = 0;
    public static int VX_FRIEND = 1;

    private BottomSheetBehavior mBehavior;
    public OnButtonClick mOnButtonClick;
    public interface OnButtonClick{
        void onShare(int type);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_security, null);
        view.findViewById(R.id.IvClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        view.findViewById(R.id.Iv110).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
//
        if (Base.getUserEntity().getIsIdentified() == 1) {
            //已实名认证
            ((TextView)view.findViewById(R.id.TvAuth)).setText("已提交待审核");
        }else if(Base.getUserEntity().getIsIdentified() == 2){
            ((TextView)view.findViewById(R.id.TvAuth)).setText("认证不通过");
        }else if(Base.getUserEntity().getIsIdentified() == 3){
            ((TextView)view.findViewById(R.id.TvAuth)).setText("审核通过已认证");
        } else {
            //未实名认证
            ((TextView)view.findViewById(R.id.TvAuth)).setText("未认证");
        }

        if (Base.getUserEntity().getIsIdentified() == 0 || Base.getUserEntity().getIsIdentified() == 2) {
            //未实名认证或认证失败
            view.findViewById(R.id.RlAuth).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", MyConstants.h5Url + MyConstants.url_certification
                            + Base.getUserEntity().getToken());
                    bundle.putString("title", "实名认证");
                    ActivityUtil.next(getActivity(), WebViewActivity.class, bundle, false);
                    dialog.cancel();
                }
            });
        }else if(Base.getUserEntity().getIsIdentified() == 1){
            //审核中
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("您的实名还在认证中，请与审核通过之后再进行使用");
                builder.setTitle("温馨提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
        }else if(Base.getUserEntity().getIsIdentified() == 3){
            //审核已通过
            view.findViewById(R.id.RlAuth).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", MyConstants.h5Url + MyConstants.url_subSucc1
                            + Base.getUserEntity().getToken());
                    bundle.putString("title", "实名认证");
                    ActivityUtil.next(getActivity(), WebViewActivity.class, bundle, false);
                    dialog.cancel();
                }
            });
        }

//        //设置紧急联系人
//        ((TextView)view.findViewById(R.id.TvUrgentPeople)).setText("");

        if (Base.getUserEntity().getHasContact()) {
            //已设置紧急联系人
            ((TextView)view.findViewById(R.id.TvUrgentPeople)).setText("已设置");
            view.findViewById(R.id.RlUrgentPeople).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", MyConstants.h5Url + url_addLinkman_change
                            + Base.getUserEntity().getToken());
                    bundle.putString("title","紧急联系人");
                    ActivityUtil.next(getActivity(), WebViewActivity.class,bundle,false);
                    dialog.cancel();
                }
            });
        }else{
            //未设置紧急联系人
            view.findViewById(R.id.RlUrgentPeople).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", MyConstants.h5Url + url_addLinkman
                            + Base.getUserEntity().getToken());
                    bundle.putString("title","紧急联系人");
                    ActivityUtil.next(getActivity(), WebViewActivity.class,bundle,false);
                    dialog.cancel();
                }
            });
        }
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @PermissionRequest({Manifest.permission.CALL_PHONE})
    private void callPhone() {
        if (Base.getConfigEntity() != null && Base.getConfigEntity().getKEFU_TEL() != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + 110);
            intent.setData(data);
            startActivity(intent);
        }
    }

    @PermissionRequestFailedCallback
    private void failed(String[] failedPermissions) {
        ((BaseActivity)getContext()).showToastMsg("获取权限失败，操作无法完成");
    }
}
