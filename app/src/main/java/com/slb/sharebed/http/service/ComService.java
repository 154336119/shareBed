package com.slb.sharebed.http.service;

import com.slb.sharebed.http.bean.BedQueryEntity;
import com.slb.sharebed.http.bean.ConfigEntity;
import com.slb.sharebed.http.bean.ContactBean;
import com.slb.sharebed.http.bean.OrderFeeDetailEntity;
import com.slb.sharebed.http.bean.PayTypeEntity;
import com.slb.sharebed.http.bean.UpdateEntity;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.frame.http2.retrofit.HttpMjListResult;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.retrofit.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ComService {

    /**
     * 用户-登录
     */
    @FormUrlEncoded
    @POST("app/user/login/register")
    Observable<HttpMjResult<UserEntity>> loginOrRigister(@Field("mobile") String mobile,
                                               @Field("verifyCode") String verifyCode,
                                               @Field("platform") int platform,
                                               @Field("scene") int scene);

    /**
     * 第三方登录
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/app/user/login/wechat")
    Observable<HttpMjResult<UserEntity>> loginThird(@Field("openid") String openid,
                                                    @Field("nickName") String nickName,
                                                    @Field("logo") String logo,
                                                    @Field("platform") int platform);
    /**
     * 用户-发短信验证码
     */
    @FormUrlEncoded
    @POST("app/user/sendSms")
    Observable<HttpMjResult<Object>> sendMsgCode(@Field("mobile") String mobile,@Field("scene") Integer scene);

    /**
     * 用户-个人账号信息
     */
    @FormUrlEncoded
    @POST("app/user/queryInfo")
    Observable<HttpMjResult<UserEntity>> getUserINfo(@Field("token") String token);

    /**
     * 用户-配置
     */
    @FormUrlEncoded
    @POST("/app/config/all"  )
    Observable<HttpMjResult<ConfigEntity>> getConfig(@Field("token") String token);

    /**
     * 用户-查询当前有没有使用中的床位订单
     */
    @FormUrlEncoded
    @POST("/app/bed/query" )
    Observable<HttpMjResult<BedQueryEntity>> getUsedBedInfo(@Field("token") String token);

    /**
     * 用户-查询床位消费明细
     */
    @FormUrlEncoded
    @POST("/app/bed/orderFee" )
    Observable<HttpMjResult<OrderFeeDetailEntity>> getOrderFee(@Field("token") String token);


    /**
     * 用户-查询床位消费明细
     */
    @FormUrlEncoded
    @POST("/app/bed/finish" )
    Observable<HttpMjResult<Object>> bedFinish(@Field("token") String token);
    /**
     * 升级
     */
    @FormUrlEncoded
    @POST("/app/version/check"  )
    Observable<HttpMjResult<UpdateEntity>> getUpdateInfo(@Field("platform") int platform);



    /**
     * 用户-联系人列表
     */
    @FormUrlEncoded
    @POST("/app/urgent/contact/list"  )
    Observable<HttpMjResult<List<ContactBean>>> getContactInfo(@Field("token") String token);


    /**
     * 支付
     */
    @FormUrlEncoded
    @POST("/app/config/paymentCode"  )
    Observable<HttpMjResult<PayTypeEntity>> getPayTypeConfig(@Field("token") Integer token);



    /**
     *  用户信息
     */
    @FormUrlEncoded
    @POST("/app/user/info"  )
    Observable<HttpMjResult<UserEntity>> getUserInfo(@Field("token") String token);




    /**
     *  用户信息
     */
    @FormUrlEncoded
    @POST("/app/bed/open" )
    Observable<HttpMjResult<Object>> bedOpen(@Field("token") String token,@Field("code") String code);

}
