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
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void beadOpen(String id);
	}
}

