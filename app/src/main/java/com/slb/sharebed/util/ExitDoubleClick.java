package com.slb.sharebed.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by huich on 2017/3/30.
 */

public class ExitDoubleClick extends DoubleClick {
    private static ExitDoubleClick exitDoubleClick;

    private ExitDoubleClick(Context context) {
        super(context);
    }

    public static synchronized ExitDoubleClick getInstance(Context context) {
        if(exitDoubleClick == null) {
            exitDoubleClick = new ExitDoubleClick(context);
        }

        return exitDoubleClick;
    }

    protected void afterDoubleClick() {
        ((Activity)this.mContext).finish();
        destroy();
    }

    public void doDoubleClick(int delayTime, String msg) {
        if(msg == null || msg.equals("")) {
            msg = "再按一次退出";
        }

        super.doDoubleClick(delayTime, msg);
    }

    private static void destroy() {
        exitDoubleClick = null;
    }
}

