package com.slb.sharebed.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.sharebed.ui.contract.LoginContract;
import com.slb.frame.utils.rx.RxUtil;
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

public class LoginPresenter extends AbstractBasePresenter<LoginContract.IView>
		implements LoginContract.IPresenter<LoginContract.IView>{
	@Override
	public void getCode(String mobile) {
		RetrofitSerciveFactory.provideComService().sendMsgCode(mobile,1)
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
	public void login(String mobile, String verifyCode) {
		RetrofitSerciveFactory.provideComService().loginOrRigister(mobile,verifyCode,1,1)
				.lift(new BindPrssenterOpterator<HttpMjResult<UserEntity>>(this))
				.compose(RxUtil.<HttpMjResult<UserEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UserEntity>())
				.subscribe(new BaseSubscriber<UserEntity>(this.mView) {
					@Override
					public void onNext(UserEntity entity) {
						super.onNext(entity);
						setLoginUserInfo(entity);
					}
				});

	}

	@Override
	public void thirdLogin( SHARE_MEDIA platform, UMShareAPI mShareAPI, Activity context) {
		if(!isWeixinAvilible(context)){
			mView.showMsg(context.getString(R.string.please_install_weixin));
			return;
		}
		mView.showLoadingDialog("登陆中");
		mShareAPI.getPlatformInfo(context, platform, new UMAuthListener() {
			@Override
			public void onStart(SHARE_MEDIA share_media) {

			}

			@Override
			public void onComplete(final SHARE_MEDIA share_media, int i, final Map<String, String> map) {
				Logger.d("========================="+map);
				RetrofitSerciveFactory.provideComService().loginThird(map.get("uid"),map.get("name"),map.get("iconurl"),1)
						.lift(new BindPrssenterOpterator<HttpMjResult<UserEntity>>(LoginPresenter.this))
						.compose(RxUtil.<HttpMjResult<UserEntity>>applySchedulersForRetrofit())
						.map(new HttpMjEntityFun<UserEntity>())
						.subscribe(new BaseSubscriber<UserEntity>(LoginPresenter.this.mView) {
							@Override
							public void onNext(UserEntity entity) {
								super.onNext(entity);
								setLoginUserInfo(entity);
							}
						});
			}

			@Override
			public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
				throwable.printStackTrace();
				mView.loadingDialogDismiss();
			}

			@Override
			public void onCancel(SHARE_MEDIA share_media, int i) {
				mView.loadingDialogDismiss();
			}
		});
	}

	/**
	 * 判断微信是否安装
	 */
	public static boolean isWeixinAvilible(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mm")) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public void getUserInfo(final String token) {
		RetrofitSerciveFactory.provideComService().getUserInfo(token)
				.lift(new BindPrssenterOpterator<HttpMjResult<UserEntity>>(LoginPresenter.this))
				.compose(RxUtil.<HttpMjResult<UserEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UserEntity>())
				.subscribe(new BaseSubscriber<UserEntity>(LoginPresenter.this.mView) {
					@Override
					public void onNext(UserEntity entity) {
						super.onNext(entity);
						entity.setToken(token);
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
