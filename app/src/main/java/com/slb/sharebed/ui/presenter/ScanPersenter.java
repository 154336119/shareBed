package com.slb.sharebed.ui.presenter;

import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.ui.contract.ScanContract;

public class ScanPersenter extends AbstractBasePresenter<ScanContract.IView> implements ScanContract.IPresenter<ScanContract.IView> {
    @Override
    public void beadOpen(String bedCode) {
//        RetrofitSerciveFactory.provideComService().bedOpen(Base.getUserEntity().getToken(),bedCode)
//                .lift(new BindPrssenterOpterator<HttpMjResult< Object>>(this))
//                .compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
//                .map(new HttpMjEntityFun< Object>())
//                .subscribe(new BaseSubscriber<Object>(this.mView) {
//                    @Override
//                    public void onNext(Object entity) {
//                        super.onNext(entity);
//                        mView.showMsg("验证码发送成功");
//                        mView.showCountdown();
//                        mView.varifyCodeSuccess();
//                    }
//                });
    }
}
