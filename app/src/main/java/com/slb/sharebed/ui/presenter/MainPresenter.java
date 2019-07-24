package com.slb.sharebed.ui.presenter;

import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.UpdateEntity;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.sharebed.ui.contract.MainContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;


/**
 * Created by Gifford on 2017/11/7.
 */

public class MainPresenter extends AbstractBasePresenter<MainContract.IView>
		implements MainContract.IPresenter<MainContract.IView>{
	@Override
	public void getUpdateInfo() {
		RetrofitSerciveFactory.provideComService().getUpdateInfo(1)
				.lift(new BindPrssenterOpterator<HttpMjResult<UpdateEntity>>(this))
				.compose(RxUtil.<HttpMjResult<UpdateEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UpdateEntity>())
				.subscribe(new BaseSubscriber<UpdateEntity>(this.mView) {
					@Override
					public void onNext(UpdateEntity entity) {
						if (entity.getVersion_num() > Base.getVersionCode(Base.getContext())){
							mView.tipUpdate(entity);
						}
					}

					@Override
					public void onStart() {
					}
				});
	}

	@Override
	public void getUserInfo() {
		RetrofitSerciveFactory.provideComService().getUserINfo(Base.getUserEntity().getToken())
				.lift(new BindPrssenterOpterator<HttpMjResult<UserEntity>>(this))
				.compose(RxUtil.<HttpMjResult<UserEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UserEntity>())
				.subscribe(new BaseSubscriber<UserEntity>(this.mView) {
					@Override
					public void onNext(UserEntity entity) {
						Base.setUserEntity(entity);
					}

					@Override
					public void onStart() {
					}
				});
	}
}
