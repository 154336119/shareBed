package com.slb.sharebed.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.orhanobut.logger.Logger;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.sharebed.ui.contract.LoginContract;
import com.slb.sharebed.ui.contract.WebViewContract;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;


/**
 * Created by Gifford on 2017/11/7.
 */

public class WebViewPresenter extends AbstractBasePresenter<WebViewContract.IView> implements WebViewContract.IPresenter<WebViewContract.IView> {

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
