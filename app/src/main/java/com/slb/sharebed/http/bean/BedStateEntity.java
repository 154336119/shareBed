package com.slb.sharebed.http.bean;

public class BedStateEntity {

    /**
     * id : 26
     * order_code : BO19081284071242
     * single_price : 5
     * start_time : 
     * state : 2
     * state_text : 开锁中
     *
     * state：0,2,3分别对应：开锁成功,开锁中,开锁失败
     */

    private Long id;
    private String order_code;
    private Long single_price;
    private String start_time;
    private Long state;
    private String state_text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public Long getSingle_price() {
        return single_price;
    }

    public void setSingle_price(Long single_price) {
        this.single_price = single_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }
}
