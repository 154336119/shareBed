package com.slb.sharebed.http.bean;

public class ContactBean {

    /**
     * id : 3
     * user_id : 5
     * mobile : 15432232323
     * name : 里
     * state : 0
     * create_time : 2019-08-04 12:15:52
     */

    private int id;
    private int user_id;
    private String mobile;
    private String name;
    private int state;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
