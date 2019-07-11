package com.slb.sharebed.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by huich on 2017/3/30.
 */

public abstract class DoubleClick {
    protected Context mContext;
    private long mStartTime;

    public DoubleClick(Context context) {
        this.mContext = context;
        this.mStartTime = -1L;
    }

    public void doDoubleClick(int delayTime, String msg) {
        if(!this.doInDelayTime(delayTime)) {
            Toast   mToast = new Toast(mContext);
            View view = LayoutInflater.from(mContext).inflate(com.slb.frame.R.layout.view_transient_notification, null, false);
            TextView textView = view.findViewById(com.slb.frame.R.id.message);
            textView.setText(msg);
            mToast.setView(view);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        }

    }

    private boolean doInDelayTime(int delayTime) {
        long nowTime = System.currentTimeMillis();
        if(nowTime - this.mStartTime <= (long)delayTime) {
            this.afterDoubleClick();
            return true;
        } else {
            this.mStartTime = nowTime;
            return false;
        }
    }

    public void doDoubleClick(int delayTime, int msgResid) {
        long nowTime = System.currentTimeMillis();
        if(nowTime - this.mStartTime <= (long)delayTime) {
            this.afterDoubleClick();
        } else {
            this.mStartTime = nowTime;
            Toast.makeText(this.mContext, msgResid, delayTime).show();
        }

    }

    protected abstract void afterDoubleClick();
}
