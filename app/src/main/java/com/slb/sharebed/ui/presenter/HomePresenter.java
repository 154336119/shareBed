package com.slb.sharebed.ui.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.orhanobut.logger.Logger;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.utils.DateUtils;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.BedQueryEntity;
import com.slb.sharebed.http.bean.ConfigEntity;
import com.slb.sharebed.http.bean.OrderFeeDetailEntity;
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

    @Override
    public void querUsedBedInfo() {
        RetrofitSerciveFactory.provideComService().getUsedBedInfo(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<BedQueryEntity>>(this))
                .compose(RxUtil.<HttpMjResult<BedQueryEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun<BedQueryEntity>())
                .subscribe(new BaseSubscriber<BedQueryEntity>(this.mView) {
                    @Override
                    public void onNext(BedQueryEntity entity) {
                        if(entity!=null && !TextUtils.isEmpty(entity.getStart_time())){
                            querOrderFee();
                        }else{
                            mView.showLockView();
                        }
                    }

                    @Override
                    public void onStart() {
                    }
                });
    }

    @Override
    public void querOrderFee() {
        RetrofitSerciveFactory.provideComService().getOrderFee(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<OrderFeeDetailEntity>>(this))
                .compose(RxUtil.<HttpMjResult<OrderFeeDetailEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun<OrderFeeDetailEntity>())
                .subscribe(new BaseSubscriber<OrderFeeDetailEntity>(this.mView) {
                    @Override
                    public void onNext(OrderFeeDetailEntity entity) {
                        mView.showOpenView(entity.getSeconds());
                    }

                    @Override
                    public void onStart() {
                    }
                });

    }

    @Override
    public void bedFinish() {
        RetrofitSerciveFactory.provideComService().bedFinish(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult< Object>>(this))
                .compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< Object>())
                .subscribe(new BaseSubscriber<Object>(this.mView) {
                    @Override
                    public void onNext(Object entity) {
                        super.onNext(entity);
                        querUsedBedInfo();
                    }
                });
    }
}
