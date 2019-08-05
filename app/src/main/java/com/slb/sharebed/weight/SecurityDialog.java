package com.slb.sharebed.weight;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.slb.frame.utils.ActivityUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.ui.activity.NoIdentifieActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;

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
            }
        });
//
        if (Base.getUserEntity().getIsIdentified() == 1) {
            //已实名认证
            ((TextView)view.findViewById(R.id.TvAuth)).setText("已认证");
        }else if(Base.getUserEntity().getIsIdentified() == 3){
            ((TextView)view.findViewById(R.id.TvAuth)).setText("提交审核");
        }else{
            //未实名认证
            view.findViewById(R.id.RlAuth).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", MyConstants.h5Url + MyConstants.url_certification
                            + Base.getUserEntity().getToken());
                    bundle.putString("title","实名认证");
                    ActivityUtil.next(getActivity(),WebViewActivity.class,bundle,false);
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
}
