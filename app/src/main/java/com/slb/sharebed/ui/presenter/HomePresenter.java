package com.slb.sharebed.ui.presenter;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.ConfigEntity;
import com.slb.sharebed.ui.contract.HomeContract;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomePresenter extends AbstractBaseFragmentPresenter<HomeContract.IView>
    implements HomeContract.IPresenter<HomeContract.IView>{
    @Override
    public void getConfigInfo() {
        RetrofitSerciveFactory.provideComService().getConfig(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<ConfigEntity>>(this))
                .compose(RxUtil.<HttpMjResult<ConfigEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun<ConfigEntity>())
                .subscribe(new BaseSubscriber<ConfigEntity>(this.mView) {
                    @Override
                    public void onNext(ConfigEntity entity) {
                        Base.setConfigEntity(entity);
                        mView.showBg();
                    }

                    @Override
                    public void onStart() {
                    }
                });
    }
}
