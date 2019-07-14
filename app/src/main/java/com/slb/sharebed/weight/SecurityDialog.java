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
import com.slb.sharebed.R;

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
        view.findViewById(R.id.RlUrgentPeople).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        view.findViewById(R.id.RlAuth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
//
//        //设置紧急联系人
//        ((TextView)view.findViewById(R.id.TvUrgentPeople)).setText("");
//        //实名认证
//        ((TextView)view.findViewById(R.id.TvAuth)).setText("");

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
