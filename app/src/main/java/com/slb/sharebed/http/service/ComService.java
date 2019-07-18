package com.slb.sharebed.http.service;

import com.slb.sharebed.http.bean.ConfigEntity;
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
    @POST("app/user/info")
    Observable<HttpMjResult<UserEntity>> getUserINfo(@Field("mobile") String mobile,@Field("scene") String scene);


    /**
     * 升级
     */
    @FormUrlEncoded
    @POST("/app/version/check"  )
    Observable<HttpMjResult<UpdateEntity>> getUpdateInfo(@Field("platform") int platform);

    /**
     * 支付
     */
    @FormUrlEncoded
    @POST("/app/config/paymentCode"  )
    Observable<HttpMjResult<PayTypeEntity>> getPayTypeConfig(@Field("token") Integer token);

    /**
     * 我的- 用户协议
     */
    @FormUrlEncoded
    @POST("/app/config/aboutus"  )
    Observable<HttpMjResult<ConfigEntity>> getConfig(@Field("token") Integer token);


    /**
     *  用户信息
     */
    @FormUrlEncoded
    @POST("/app/user/info"  )
    Observable<HttpMjResult<UserEntity>> getUserInfo(@Field("token") String token);
}
