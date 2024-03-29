package com.slb.sharebed.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.slb.sharebed.http.bean.PayEntity;

/**
 * Created by Gifford on 2017/11/7.
 */

public class ScanContract {
	public interface IView extends IBaseLoadingDialogView {
		void openSuccess();
		void openFailed();
		/**按开关提示框*/
		void showClickOpenTipsDialog();
		/**开锁中*/
		void openIng();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void beadOpen(String id);
		void queryOpenState();
	}
}

