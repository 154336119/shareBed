package com.slb.sharebed.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.sharebed.R;
import com.slb.sharebed.http.bean.UserEntity;
import com.slb.sharebed.ui.contract.MineContract;
import com.slb.sharebed.ui.presenter.MinePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


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

    @OnClick({R.id.TvUserInfo, R.id.TvMsg, R.id.TvSetting, R.id.TvKefu, R.id.RlDeposit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.TvUserInfo:
                break;
            case R.id.TvMsg:
                break;
            case R.id.TvSetting:
                break;
            case R.id.TvKefu:
                break;
            case R.id.RlDeposit:
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
