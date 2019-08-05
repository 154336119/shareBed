package com.slb.sharebed.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.slb.sharebed.http.bean.PayEntity;

/**
 * Created by Gifford on 2017/11/7.
 */

public class WebViewFragmentContract {
	public interface IView extends IBaseLoadingDialogView {
		void getPayParamSuccess(PayEntity entity);
		void toPaySuccessActivity();
		void toPayFaildActivity();
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
		void getPayParam(int payType, String orderCode);
	}
}

