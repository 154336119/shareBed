package com.slb.sharebed.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2018/1/5.
 */

public class HomeContract {
	public interface IView extends IBaseLoadingDialogView {
		void showBg();
		//未解锁视图
		void showLockView();
		//解锁后的视图
		void showOpenView(Long miao);
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
		void getConfigInfo();
		void querUsedBedInfo();
		void querOrderFee();
		void bedFinish();
	}
}
