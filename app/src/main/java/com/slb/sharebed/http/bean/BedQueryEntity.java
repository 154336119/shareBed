package com.slb.sharebed.http.bean;

public class BedQueryEntity {


    /**
     * id : 8
     * order_code : BO19072651045113
     * branch_id : 1
     * user_id : 6
     * bed_id : 12
     * single_price : 50
     * start_time : 2019-07-26 17:12:05
     * stop_time : 2019-07-26 17:12:05
     * use_time : 0
     * total_money : 0
     * state : 0
     */

    private int id;
    private String order_code;
    private int branch_id;
    private int user_id;
    private int bed_id;
    private int single_price;
    private String start_time;
    private String stop_time;
    private int use_time;
    private int total_money;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBed_id() {
        return bed_id;
    }

    public void setBed_id(int bed_id) {
        this.bed_id = bed_id;
    }

    public int getSingle_price() {
        return single_price;
    }

    public void setSingle_price(int single_price) {
        this.single_price = single_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public int getUse_time() {
        return use_time;
    }

    public void setUse_time(int use_time) {
        this.use_time = use_time;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
