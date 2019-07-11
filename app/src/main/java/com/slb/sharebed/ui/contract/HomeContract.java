package com.slb.sharebed.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2018/1/5.
 */

public class HomeContract {
	public interface IView extends IBaseLoadingDialogView {
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
	}
}
