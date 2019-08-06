package com.slb.sharebed.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.hwangjr.rxbus.RxBus;
import com.orhanobut.logger.Logger;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.event.FinishAcitivtyEvent;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.sharebed.ui.contract.BindPhoneContract;
import com.slb.sharebed.ui.contract.LoginContract;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;


/**
 * Created by Gifford on 2017/11/7.
 */

public class BindPhonePresenter extends AbstractBasePresenter<BindPhoneContract.IView>
		implements BindPhoneContract.IPresenter<BindPhoneContract.IView>{
	@Override
	public void getCode(String mobile) {
		RetrofitSerciveFactory.provideComService().sendMsgCode(mobile,3)
				.lift(new BindPrssenterOpterator<HttpMjResult< Object>>(this))
				.compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun< Object>())
				.subscribe(new BaseSubscriber<Object>(this.mView) {
					@Override
					public void onNext(Object entity) {
						super.onNext(entity);
						mView.showMsg("验证码发送成功");
						mView.showCountdown();
						mView.varifyCodeSuccess();
					}
				});
	}

	@Override
	public void bind(String openid, String type, String nickName, String logo, String mobile, String verifyCode, String platform) {
		RetrofitSerciveFactory.provideComService().bindQqWechat(openid,type,nickName,logo,mobile,verifyCode,platform)
				.lift(new BindPrssenterOpterator<HttpMjResult< UserEntity>>(this))
				.compose(RxUtil.<HttpMjResult< UserEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun< UserEntity>())
				.subscribe(new BaseSubscriber<UserEntity>(this.mView) {
					@Override
					public void onNext(UserEntity entity) {
						super.onNext(entity);
						setLoginUserInfo(entity);
					}
				});
	}

	private void setLoginUserInfo(UserEntity entity){
		Base.setUserEntity(entity);
		PushAgent mPushAgent = PushAgent.getInstance(Base.getContext());
		mPushAgent.setAlias(Base.getUserEntity().getToken(), "xikeqiche", new UTrack.ICallBack() {
			@Override
			public void onMessage(boolean b, String s) {
				Logger.d(b + s);
			}
		});
			mView.loginSuccess();
	}

}
