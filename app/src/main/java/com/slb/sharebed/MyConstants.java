package com.slb.sharebed;

/**
 * 描述：业务常量
 * Created by Lee
 * on 2016/9/20.
 * //
 * //Appkey：5cee3747570df3fbb3001003
 * //        Umeng Message Secret：c03143546167352f2e2de6ee1c0dda3e
 * //        App Master Secret：zgoz0mkc0t195esfjdbyplowpbz7k0cp
 * //友盟推送设置，登录成功之后设置alias：
 * //        alias类型：xikeqiche
 * //        alias的值：用户登录成功返回的token
 * //        运行模式：production模式
 * //        platform：1安卓个、2ios
 *
 *  微信：
 * //          APP ID ：wx6e626c6693c3c055
 * //        AppSecret ：f209badd4ead402cc20a395724325284
 *
 * 接口
 * http://api.xikeqiche.com/swagger-ui.html
 * meijiang
 * meijiang666
 *
 *
 */
public class MyConstants {
    public static final String url = "http://yclx.api.kaixuanhotels.cn/";

    public static final String h5Url = "http://h5.xikeqiche.com";

    public static final String WX_APP_ID = "wx6e626c6693c3c055";

    public static final String appendCss = "<html><style>.divtest1 { line-height:1.5;letter-spacing:1.0px;text-align:justify;color:#333333;margin-right:15px;margin-left:15px;margin-top:15px;} .divtest1 img {  margin:auto;max-width:100%;min-width:100%;vertical-align:middle;}}</style><div class=\"divtest1\">";
    /**
     *
     */
    public static final String url_token = "&token=";

    /**
     * 品牌选车
     */
    public static final String url_pingpaixueche = "/optbrand?brandId=";

    /**
     * 秒杀详情
     */
    public static final String url_miaoshaxiangqing = "/seckillinfo?seckillId=";

    /**
     * 产品详情
     */
    public static final String url_chanpingxiangqing = "/goodsinfo?productId=";
    /**
     * 收货地址管理
     */
    public static final String url_shouhuodizhiguanli = "/addresslist?state=2&token=";

    /**
     * 邀请好友
     */
    public static final String url_yaoqinghaoyou = "/share";

    /**
     * 订单详情
     */
    public static final String url_dingdanxiangqing = "/orderDetail?orderId=";

    /**
     * 意见反馈
     */
    public static final String url_yijianfankui = "/feedback?token=";

    /**
     * 购物车
     */
    public static final String url_gouwuche = "/shopCar?token=";

    /**
     * 查看物流
     */
    public static final String url_chakanwuliu = "/lookLogistics?orderId=";

    //图片选择
    public static final int REQUEST_CODE_PROOF_IMG_PICK=1009;
    public static final int REQUEST_CODE_PROOF_IMG_PREVIEW=1008;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    /**类型*/
    public static final String TYPE="type";
    public static final String BUNDLE_ORDER_STATUS="bundle_order_status";
    public static final String HOME_SELECTED_FRAGMENT="home_selected_fragment";
}
