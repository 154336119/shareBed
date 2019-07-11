package com.slb.sharebed.ui.presenter;

import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.ui.contract.MineContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MinePresenter extends AbstractBaseFragmentPresenter<MineContract.IView>
    implements MineContract.IPresenter<MineContract.IView>{
}
