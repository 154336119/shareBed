package com.slb.frame.http2.retrofit;


/**
 * 描述：Entity - 网络请求统一返回格式
 * Created by Lee
 * on 2016/10/13.
 */
public class HttpResult<T,A> {

    /**
     * code : 0
     * msg : 成功
     */

    private int code;
    private String msg;
    private HttpDataResutl<T,A> data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpDataResutl<T, A> getData() {
        return data;
    }

    public void setData(HttpDataResutl<T, A> data) {
        this.data = data;
    }
}
