package com.slb.sharebed.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slb.frame.ui.activity.BaseActivity;
import com.slb.sharebed.Base;
import com.slb.sharebed.R;
import com.slb.sharebed.util.FileUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by juan on 2018/9/5.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.TvVersion)
    TextView TvVersion;
    @BindView(R.id.RlAbout)
    RelativeLayout RlAbout;
    @BindView(R.id.TvCacheNum)
    TextView TvCacheNum;
    @BindView(R.id.RlClearCache)
    RelativeLayout RlClearCache;
    @BindView(R.id.RlAgreement)
    RelativeLayout RlAgreement;
    @BindView(R.id.TvLoginOut)
    TextView TvLoginOut;
    @BindView(R.id.RlSecurity)
    RelativeLayout RlSecurity;
    @BindView(R.id.RlLaw)
    RelativeLayout RlLaw;

    @Override
    protected String setToolbarTitle() {
        return getString(R.string.Settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        try {
            TvCacheNum.setText(FileUtils.getCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.RlAbout, R.id.RlClearCache, R.id.RlAgreement, R.id.RlSecurity, R.id.RlLaw,R.id.TvLoginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.RlSecurity:
                break;
            case R.id.RlLaw:
                break;
            case R.id.RlAbout:
                break;
            case R.id.RlClearCache:
                showDialog("提示！", "确定删除缓存？",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                showWaitDialog("清除缓存中");
                                Observable.create(new Observable.OnSubscribe<String>() {

                                    @Override
                                    public void call(Subscriber<? super String> subscriber) {
                                        FileUtils.cleanApplicationData(SettingActivity.this);
                                        try {
                                            String s = FileUtils
                                                    .getCacheSize(SettingActivity.this);
                                            subscriber.onNext(s);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        hideWaitDialog();
                                    }

                                    @Override
                                    public void onNext(String s) {
                                        hideWaitDialog();
                                        TvCacheNum.setText(s);
                                    }
                                });

                            }
                        });
                break;
            case R.id.RlAgreement:
//                ActivityUtil.next(this,UserAgreementActivity.class);
                break;
            case R.id.TvLoginOut:
                PushAgent mPushAgent = PushAgent.getInstance(this);
                mPushAgent.deleteAlias("xikeqiche", Base.getUserEntity().getToken(), new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean b, String s) {

                    }
                });
                Base.setUserEntity(null);

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                ActivityUtil.next(this,LoginActivity.class,null,true);
                finish();
                break;
        }
    }
}
