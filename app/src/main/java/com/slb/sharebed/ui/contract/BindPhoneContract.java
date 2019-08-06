package com.slb.sharebed.ui.contract;

import android.app.Activity;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Gifford on 2017/11/7.
 */

public class BindPhoneContract {
	public interface IView extends IBaseLoadingDialogView {
		void loginSuccess();
		void varifyCodeSuccess();
		void showCountdown();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		/*** 获取验证码*/
		void getCode(String mobile);
		/*** 登陆*/
		void bind(String openid, String type, String nickName, String logo,String mobile, String verifyCode,String platform);
	}
}
