package com.slb.sharebed.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.jaeger.library.StatusBarUtil;
import com.slb.frame.utils.rx.RxBus;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.event.RefreshUserInfoEvent;
import com.slb.sharebed.http.bean.UpdateEntity;
import com.slb.sharebed.ui.contract.MainContract;
import com.slb.sharebed.ui.fragment.CopyMoneyFragment;
import com.slb.sharebed.ui.fragment.MoneyFragment;
import com.slb.sharebed.ui.fragment.OrderFragment;
import com.slb.sharebed.ui.presenter.MainPresenter;
import com.slb.sharebed.util.ExitDoubleClick;
import com.slb.frame.http2.exception.ResponseExceptionEventArgs;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.ui.fragment.BaseFragment;
import com.slb.sharebed.ui.fragment.HomeFragment;
import com.slb.sharebed.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseMvpActivity<MainContract.IView, MainContract.IPresenter>implements MainContract.IView , RadioGroup.OnCheckedChangeListener{
    private Subscription loginOutSub;
    public static final String RESPONSE_EXCEPTION_LOGINOUT = "ResponseExceptionEventArgs";
    private Observable<ResponseExceptionEventArgs> loginOutObservable;
    public static final int HOME_HOME = 0;
    public static final int HOME_ORDER= 1;
    public static final int HOME_MONEY = 2;
    public static final int HOME_MINE = 3;
    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;
    @BindView(R.id.bottomBar)
    RadioGroup bottomBar;
    private int prePosition;
    private BaseFragment[] mFragments = new BaseFragment[4];
    protected boolean hasToolbar() {
        return false;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @Override
    public MainContract.IPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getUpdateInfo();
        mPresenter.getUserInfo();
        mPresenter.getConfigInfo();
        if (savedInstanceState == null) {
            mFragments[HOME_HOME] = HomeFragment.newInstance();
            mFragments[HOME_ORDER] = OrderFragment.newInstance();
            mFragments[HOME_MONEY] = MoneyFragment.newInstance();
            mFragments[HOME_MINE] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.mainFrame, HOME_HOME,
                    mFragments[HOME_HOME],
                    mFragments[HOME_ORDER],
                    mFragments[HOME_MONEY],
                    mFragments[HOME_MINE]);
        } else {
            mFragments[HOME_HOME] = findFragment(HomeFragment.class);
            mFragments[HOME_ORDER] = findFragment(OrderFragment.class);
            mFragments[HOME_MONEY] = findFragment(CopyMoneyFragment.class);
            mFragments[HOME_MINE] = findFragment(MineFragment.class);
        }
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        StatusBarUtil.setLightMode(this);
        prePosition = 0;
        bottomBar.setOnCheckedChangeListener(this);
        bottomBar.check(R.id.rb_home);
        loginOutObservable = RxBus.getInstance().register(RESPONSE_EXCEPTION_LOGINOUT);
        loginOutSub = loginOutObservable.subscribeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .unsubscribeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseExceptionEventArgs>() {
                    @Override
                    public void call(ResponseExceptionEventArgs args) {
//                        PushAgent mPushAgent = PushAgent.getInstance(Base.getContext());
//                        mPushAgent.deleteAlias("xikeqiche", Base.getUserEntity().getToken(), new UTrack.ICallBack() {
//                            @Override
//                            public void onMessage(boolean b, String s) {
//
//                            }
//                        });
                        Base.setUserEntity(null);
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                showHideFragment(mFragments[0], mFragments[prePosition]);
                prePosition = 0;
                break;
            case R.id.rb_order:
                showHideFragment(mFragments[1], mFragments[prePosition]);
                prePosition = 1;
                break;
            case R.id.rb_money:
                showHideFragment(mFragments[2], mFragments[prePosition]);
                prePosition = 2;
                break;
            case R.id.rb_mine:
                showHideFragment(mFragments[3], mFragments[prePosition]);
                prePosition = 3;
                break;
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            ExitDoubleClick.getInstance(this).doDoubleClick(3000, null);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
            int selct=intent.getIntExtra(MyConstants.HOME_SELECTED_FRAGMENT,2);
            if(selct==0){
                bottomBar.check(R.id.rb_home);
            }else if(selct == 2){
                bottomBar.check(R.id.rb_mine);
            }
    }

    @Override
    public void tipUpdate(final UpdateEntity entity) {
        showUpadateDialog("更新提示！", entity.getUpgrade_desc(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Uri uri = Uri.parse(entity.getUpgrade_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Subscribe
    public void refreshUserInfoEvent(RefreshUserInfoEvent event) {
        mPresenter.getUserInfo();
    }
}
