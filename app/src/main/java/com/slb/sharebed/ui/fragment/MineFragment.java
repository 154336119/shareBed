package com.slb.sharebed.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.frame.utils.ImageLoadUtil;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.sharebed.ui.activity.SettingActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;
import com.slb.sharebed.ui.contract.MineContract;
import com.slb.sharebed.ui.presenter.MinePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.slb.sharebed.MyConstants.url_deposit;
import static com.slb.sharebed.MyConstants.url_service;


public class MineFragment
        extends BaseMvpFragment<MineContract.IView, MineContract.IPresenter>
        implements MineContract.IView {

    Unbinder unbinder;
    @BindView(R.id.TvHead)
    CircleImageView TvHead;
    @BindView(R.id.TvName)
    TextView TvName;
    @BindView(R.id.IvAuthState)
    ImageView IvAuthState;
    @BindView(R.id.TvUserInfo)
    TextView TvUserInfo;
    @BindView(R.id.TvMsg)
    TextView TvMsg;
    @BindView(R.id.TvSetting)
    TextView TvSetting;
    @BindView(R.id.TvKefu)
    TextView TvKefu;
    @BindView(R.id.IvNoDeposit)
    ImageView IvNoDeposit;
    @BindView(R.id.RlDeposit)
    RelativeLayout RlDeposit;


    @Override
    protected boolean hasToolbar() {
        return false;
    }


    public static MineFragment newInstance() {
        MineFragment instance = new MineFragment();
        return instance;
    }

    @Override
    public MineContract.IPresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        registerLiveDateBus();

        if(!TextUtils.isEmpty(Base.getUserEntity().getNickName())){
            TvName.setText(Base.getUserEntity().getNickName());
        }
        if(!TextUtils.isEmpty(Base.getUserEntity().getLogo())){
            ImageLoadUtil.loadImage(_mActivity,Base.getUserEntity().getLogo(),TvHead);
        }


        //实名认证
        if(Base.getUserEntity().getIsIdentified()!=1){
            IvAuthState.setImageResource(R.mipmap.qurenzheng);
        }else{
            IvAuthState.setImageResource(R.mipmap.yirenzheng);
        }

        //押金状态
        if(Base.getUserEntity().getIsIdentified() == 1){
            IvAuthState.setImageResource(R.mipmap.yirenzheng);
        }else{
            IvAuthState.setImageResource(R.mipmap.qurenzheng);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    protected boolean rxBusRegist() {
        return true;
    }

    @OnClick({R.id.TvUserInfo, R.id.TvMsg, R.id.TvSetting, R.id.TvKefu, R.id.RlDeposit,R.id.IvAuthState})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.IvAuthState:
                if(Base.getUserEntity().getIsIdentified()==0){
                    bundle.putString("url", MyConstants.h5Url + MyConstants.url_certification
                            + Base.getUserEntity().getToken());
                    bundle.putString("title","实名认证");
                    ActivityUtil.next(getActivity(), WebViewActivity.class,bundle,false);
                }
                break;
            case R.id.TvUserInfo:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_person
                        + Base.getUserEntity().getToken());
                bundle.putString("title","个人信息");
                ActivityUtil.next(getActivity(),WebViewActivity.class,bundle,false);
                break;
            case R.id.TvMsg:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_message
                        + Base.getUserEntity().getToken());
                bundle.putString("title","消息中心");
                ActivityUtil.next(getActivity(),WebViewActivity.class,bundle,false);
                break;
            case R.id.TvSetting:
                ActivityUtil.next(getActivity(), SettingActivity.class);
                break;
            case R.id.TvKefu:
                bundle.putString("url", MyConstants.h5Url + url_service
                        + Base.getUserEntity().getToken());
                bundle.putString("title","客服中心");
                ActivityUtil.next(getActivity(), WebViewActivity.class,bundle,false);
                break;
            case R.id.RlDeposit:
                bundle.putString("url", MyConstants.h5Url + url_deposit
                        + Base.getUserEntity().getToken());
                bundle.putString("title","押金");
                ActivityUtil.next(getActivity(), WebViewActivity.class,bundle,false);
                break;
        }
    }

    public void registerLiveDateBus(){
        LiveEventBus.get().with("User_info", UserEntity.class)
                .observe(this, new Observer<UserEntity>() {
                    @Override
                    public void onChanged(@Nullable UserEntity entity) {
                        if(entity.getIsDeposit() == 1){
                            IvNoDeposit.setVisibility(View.GONE);
                        }else{
                            IvNoDeposit.setVisibility(View.VISIBLE);
                        }
                        if(entity.getIsIdentified() == 1){
                            IvAuthState.setImageResource(R.mipmap.yirenzheng);
                        }else{
                            IvAuthState.setImageResource(R.mipmap.qurenzheng);
                        }
                    }
                });
    }
}
