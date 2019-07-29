package com.slb.sharebed.ui.presenter;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.slb.sharebed.Base;
import com.slb.sharebed.http.RetrofitSerciveFactory;
import com.slb.sharebed.http.bean.ConfigEntity;
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
						//测试
						entity.setIsDeposit(1);
//						entity.setIsIdentified(1);
						Base.setUserEntity(entity);
						LiveEventBus.get().with("User_info").post(entity);
					}

					@Override
					public void onStart() {
					}
				});
	}

	@Override
	public void getConfigInfo() {
		RetrofitSerciveFactory.provideComService().getConfig(Base.getUserEntity().getToken())
				.lift(new BindPrssenterOpterator<HttpMjResult<ConfigEntity>>(this))
				.compose(RxUtil.<HttpMjResult<ConfigEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<ConfigEntity>())
				.subscribe(new BaseSubscriber<ConfigEntity>(this.mView) {
					@Override
					public void onNext(ConfigEntity entity) {
						Base.setConfigEntity(entity);
						LiveEventBus.get().with("Config_info").post(entity);
					}

					@Override
					public void onStart() {
					}
				});
	}
}
