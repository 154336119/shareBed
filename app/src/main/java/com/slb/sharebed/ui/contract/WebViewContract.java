package com.slb.sharebed.ui.contract;

import android.app.Activity;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.slb.sharebed.http.bean.PayEntity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Gifford on 2017/11/7.
 */

public class WebViewContract {
	public interface IView extends IBaseLoadingDialogView {
		void getPayParamSuccess(PayEntity entity);
		void toPaySuccessActivity();
		void toPayFaildActivity();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getPayParam(int payType,String orderCode);
	}
}

