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

    public static final String h5Url = "http://h5.gxcw.meijiangkj.com";

    public static final String WX_APP_ID = "wx6e626c6693c3c055";

    public static final String appendCss = "<html><style>.divtest1 { line-height:1.5;letter-spacing:1.0px;text-align:justify;color:#333333;margin-right:15px;margin-left:15px;margin-top:15px;} .divtest1 img {  margin:auto;max-width:100%;min-width:100%;vertical-align:middle;}}</style><div class=\"divtest1\">";
    /**
     *
     */
    public static final String url_token = "&token=";

    /**
     * 结束之后--消费明细
     */
    public static final String url_feeDetails = "/feeDetails";
    /**
     * 余额充值
     */
    public static final String url_recharge = "/recharge";
    /**
     * 消费-支付成功
     * 参数type：1实名认证提交成功 2物品遗落提交成功 3开具发票提交成功 4余额支付成功 5押金支付成功
     */
    public static final String url_subSucc4 = "/subSucc?type=4";
    /**
     * 紧急联系人
     */
    public static final String url_linkman = "/linkman?token=";
    /**
     * 新增紧急联系人
     */
    public static final String url_addLinkman = "/addLinkman";
    /**
     * 修改紧急联系人
     */
    public static final String url_addLinkman_change = "/addLinkman?id=";
    /**
     * 实名认证
     */
    public static final String url_certification= "/certification?token=";
    /**
     * 实名认证--提交审核
     * 参数type：1实名认证提交成功 2物品遗落提交成功 3开具发票提交成功 4余额支付成功 5押金支付成功
     */
    public static final String url_subSucc1 = "/subSucc?type=1";
    /**
     * 客服中心
     */
    public static final String url_service = "/service?token=";
    /**
     * 物品遗落
     */
    public static final String url_goodsLose = "/goodsLose";
    /**
     * 物品遗落-提交成功
     */
    public static final String url_subSucc2 = "/subSucc?type=2";
    /**
     * 开具发票
     */
    public static final String url_invoice = "/invoice?totalMoney=0";
    /**
     * 开具发票--提交成功
     */
    public static final String url_subSucc3 = "/subSucc?type=3";
    /**
     * 处理进度
     */
    public static final String url_pingpaixueche = "/dealplan";
    /**
     * 交押金
     */
    public static final String url_deposit = "/deposit?token=";
    /**
     * 押金-支付成功
     */
    public static final String url_subSucc5 = "/subSucc?type=5";
    /**
     * 法律声明与平台规则
     */
    public static final String url_law = "/law?token=";
    /**
     * 用户指南
     */
    public static final String url_guide = "/guide?token=";
    /**
     * 订单列表
     */
    public static final String url_order = "/order?token=";
    /**
     * 钱包
     */
    public static final String url_wallet = "/wallet?token=";
    /**
     * 提现
     */
    public static final String url_kiting = "/kiting?token=";
    /**
     * 添加银行卡
     */
    public static final String url_addBankCard = "/addBankCard?token=";
    /**
     * 提现成功
     */
    public static final String url_kitingSucc = "/kitingSucc";
    /**
     * 交易明细
     */
    public static final String url_dealDetails = "/dealDetails?token=";
    /**
     * 押金-退款成功
     */
    public static final String url_refundSucc = "/refundSucc";
    /**
     * 个人信息
     */
    public static final String url_person = "/person?token=";
    /**
     * 消息中心
     */
    public static final String url_message = "/message?token=";







    //图片选择
    public static final int REQUEST_CODE_PROOF_IMG_PICK=1009;
    public static final int REQUEST_CODE_PROOF_IMG_PREVIEW=1008;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    /**类型*/
    public static final String TYPE="type";
    public static final String HOME_SELECTED_FRAGMENT="home_selected_fragment";
}
