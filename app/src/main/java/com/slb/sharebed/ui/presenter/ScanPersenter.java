package com.slb.sharebed.ui.presenter;

import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.BedStateEntity;
import com.slb.sharebed.ui.contract.ScanContract;

public class ScanPersenter extends AbstractBasePresenter<ScanContract.IView> implements ScanContract.IPresenter<ScanContract.IView> {
    @Override
    public void beadOpen(String bedCode) {
        RetrofitSerciveFactory.provideComService().bedOpen(Base.getUserEntity().getToken(),bedCode)
                .lift(new BindPrssenterOpterator<HttpMjResult< Object>>(this))
                .compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< Object>())
                .subscribe(new BaseSubscriber<Object>(this.mView) {
                    @Override
                    public void onNext(Object entity) {
                        super.onNext(entity);
//                        mView.openSuccess();
                        mView.showClickOpenTipsDialog();
                        queryOpenState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.openFailed();
                    }
                });
    }


    @Override
    public void queryOpenState() {
        RetrofitSerciveFactory.provideComService().bedOpenQueryState(Base.getUserEntity().getToken())
                .lift(new BindPrssenterOpterator<HttpMjResult<BedStateEntity>>(this))
                .compose(RxUtil.<HttpMjResult< BedStateEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< BedStateEntity>())
                .subscribe(new BaseSubscriber<BedStateEntity>(this.mView) {
                    @Override
                    public void onNext(BedStateEntity entity) {
                        super.onNext(entity);
                        if(entity.getState() == 3){
                            //开锁失败
                            mView.openFailed();
                        }else if(entity.getState() == 0){
                            //开锁成功
                            mView.openSuccess();
                        }else if(entity.getState() == 2){
                            //开锁中
                            mView.openIng();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.openFailed();
                    }
                });
    }
}
