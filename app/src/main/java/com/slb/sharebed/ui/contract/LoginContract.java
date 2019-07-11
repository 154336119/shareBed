package com.slb.sharebed.ui.contract;

import android.app.Activity;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Gifford on 2017/11/7.
 */

public class LoginContract {
	public interface IView extends IBaseLoadingDialogView {
		void loginSuccess();
		void varifyCodeSuccess();
		void showCountdown();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		/*** 获取验证码*/
		void getCode(String mobile);
		/*** 登陆*/
		void login( String mobile, String verifyCode);
		/*** 微信登陆*/
		void thirdLogin(SHARE_MEDIA platform, UMShareAPI mShareAPI, Activity context);
		/*** 查询用户信息*/
		void getUserInfo(String token);

	}
}
