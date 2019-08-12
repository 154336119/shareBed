package com.slb.sharebed.ui.presenter;

import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.ui.contract.WebViewContract;
import com.slb.sharebed.ui.contract.WebViewFragmentContract;


/**
 * Created by Gifford on 2017/11/7.
 */

public class WebViewFragmentPresenter extends AbstractBaseFragmentPresenter<WebViewFragmentContract.IView> implements WebViewFragmentContract.IPresenter<WebViewFragmentContract.IView> {

//    @Override
//    public void getPayState(int payType, String orderCode) {
//        Subscription subscribe = RetrofitSerciveFactory.provideComService().getPayState(payType, orderCode)
//                .lift(new BindPrssenterOpterator<HttpMjResult<String>>(this))
//                .compose(RxUtil.<HttpMjResult<String>>applySchedulersForRetrofit())
//                .map(new HttpMjEntityFun<String>())
//                .subscribe(new BaseSubscriber<String>(this.mView) {
//                    @Override
//                    public void onNext(String entity) {
//                        mView.toPaySuccessActivity();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.toPayFaildActivity();
//                    }
//                });
//    }

	@Override
	public void getPayParam(int payType, String orderCode) {
//		RetrofitSerciveFactory.provideComService().getPayParams(payType,orderCode)
//				.lift(new BindPrssenterOpterator<HttpMjResult<PayEntity>>(this))
//				.compose(RxUtil.<HttpMjResult<PayEntity>>applySchedulersForRetrofit())
//				.map(new HttpMjEntityFun< PayEntity>())
//				.subscribe(new BaseSubscriber<PayEntity>(this.mView) {
//					@Override
//					public void onNext(PayEntity entity) {
//						super.onNext(entity);
//						mView.getPayParamSuccess(entity);
//					}
//				});
	}
}
