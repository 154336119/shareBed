package com.slb.sharebed.http.bean;

public class ConfigEntity {

    /**
     * aboutus : <p>这是关于我们<br/></p>
     * declare : <p>特约声明</p>
     * xieyi : <p>这是用户协议修改之后的</p>
     */

    private String aboutus;
    private String declare;
    private String xieyi;

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getDeclare() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare = declare;
    }

    public String getXieyi() {
        return xieyi;
    }

    public void setXieyi(String xieyi) {
        this.xieyi = xieyi;
    }
}
