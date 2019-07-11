package com.slb.sharebed.http.bean;

import java.io.Serializable;

public class WebBean implements Serializable {

    private static final long serialVersionUID = -2034838654151599403L;

    public String type;


    public int isRefund; //0右上角啥也不加， 1酒店订单退款 2团购订单退款 -1会员说明

    public String imgurl;


    public String phoneNumber;

    public String payType;// 1支付宝、2微信支付;


    public boolean isRefresh;
    public boolean isHideNarBar; //是否隐藏导航栏

    public String linkType;//（首页：index，酒店详情：hotelDetails）。

    public String id;// 酒店id
    public String restId;
    public String price;

    // 标题：
    public String title;
    //副标题：
    public String subTitle;
    // 封面图：
    public String headImg;
    //内容：
    public String content;
    //路径：
    public String url;


    //分享的标题
    public String shareTitle;
    //分享的副标题
    public String shareSubTitle ;
    //完整的url路径
    public String shareUrl ;
    //分享出去的封面图(logo)地址
    public String shareLogo ;

    public String orderId;

    public int ifToken = 0;
    public String addressId;


    //是否显示分享按钮
    public int isShare = 0;
}