package com.slb.sharebed.event;

/**
 * Created by juan on 2018/7/29.
 */

public class ObdConnectStateEvent {
    boolean isConnect;

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public ObdConnectStateEvent(boolean isConnect) {
        this.isConnect = isConnect;
    }
}
