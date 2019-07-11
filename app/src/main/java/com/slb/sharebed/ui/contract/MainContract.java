package com.slb.sharebed.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.slb.sharebed.http.bean.UpdateEntity;

/**
 * Created by Gifford on 2017/11/29.
 */

public class MainContract {
	public interface IView extends IBaseLoadingDialogView {
		void tipUpdate(UpdateEntity entity);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getUpdateInfo();
	}
}
