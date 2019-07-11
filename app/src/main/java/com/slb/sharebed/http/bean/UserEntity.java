package com.slb.sharebed.http.bean;

public class UserEntity {

    /**
     * id : 3
     * mobile : 15208305795
     * nick_name : 手机用户152****5795
     * logo : https://api.dddiancan.com/nopic.png
     * wechat_openid : 
     * business_license : 
     * platform : 1
     * state : 0
     * token : 162051c447b3481d6f3e86886bfcb4cc00c4f80e2e471851ca640573c3490ce5
     */

    private Integer id;
    private String mobile;
    private String nick_name;
    private String logo;
    private String wechat_openid;
    private String business_license;
    private Integer platform;
    private Integer state;
    private String token;

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    private String refuse_reason;//失败原因
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWechat_openid() {
        return wechat_openid;
    }

    public void setWechat_openid(String wechat_openid) {
        this.wechat_openid = wechat_openid;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
