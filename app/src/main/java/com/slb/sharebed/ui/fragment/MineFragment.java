package com.slb.sharebed.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.slb.sharebed.Base;
import com.slb.sharebed.MyConstants;
import com.slb.sharebed.R;
import com.slb.sharebed.event.OrderNumRefreshEvent;
import com.slb.sharebed.ui.activity.SettingActivity;
import com.slb.sharebed.ui.activity.WebViewActivity;
import com.slb.sharebed.ui.contract.MineContract;
import com.slb.sharebed.ui.presenter.MinePresenter;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.slb.frame.utils.ActivityUtil;
import com.slb.frame.utils.ImageLoadUtil;

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
    @BindView(R.id.IvSetting)
    ImageView IvSetting;
    @BindView(R.id.IvMsg)
    ImageView IvMsg;
    @BindView(R.id.RlOrderList)
    RelativeLayout RlOrderList;
    @BindView(R.id.mTvYiXiaDanCount)
    TextView mTvYiXiaDanCount;
    @BindView(R.id.BtnOrder)
    RelativeLayout BtnOrder;
    @BindView(R.id.mTvDaiFaHuoCount)
    TextView mTvDaiFaHuoCount;
    @BindView(R.id.BtnOrderSend)
    RelativeLayout BtnOrderSend;
    @BindView(R.id.mTvDaiShouHuoCount)
    TextView mTvDaiShouHuoCount;
    @BindView(R.id.BtnOrderReceive)
    RelativeLayout BtnOrderReceive;
    @BindView(R.id.mTvDoneCount)
    TextView mTvDoneCount;
    @BindView(R.id.BtnOrderDone)
    RelativeLayout BtnOrderDone;
    @BindView(R.id.mTvCancelCount)
    TextView mTvCancelCount;
    @BindView(R.id.btnOrderCancel)
    RelativeLayout btnOrderCancel;
    @BindView(R.id.RlAddr)
    RelativeLayout RlAddr;
    @BindView(R.id.RlInvitation)
    RelativeLayout RlInvitation;
    @BindView(R.id.RlOptinion)
    RelativeLayout RlOptinion;


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
        if(!TextUtils.isEmpty(Base.getUserEntity().getNick_name())){
            TvName.setText(Base.getUserEntity().getNick_name());
        }
        if(!TextUtils.isEmpty(Base.getUserEntity().getLogo())){
            ImageLoadUtil.loadImage(_mActivity,Base.getUserEntity().getLogo(),TvHead);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.IvSetting, R.id.IvMsg, R.id.RlOrderList, R.id.BtnOrder, R.id.BtnOrderSend, R.id.BtnOrderReceive, R.id.BtnOrderDone, R.id.btnOrderCancel, R.id.RlAddr, R.id.RlInvitation, R.id.RlOptinion})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.IvSetting:
                //设置
                ActivityUtil.next(_mActivity, SettingActivity.class);
                break;
            case R.id.IvMsg:
                //消息中心
                break;
            case R.id.RlOrderList:
                break;
            case R.id.BtnOrder:
                bundle.putInt("POS",0);
                break;
            case R.id.BtnOrderSend:
                bundle.putInt("POS",1);
                break;
            case R.id.BtnOrderReceive:
                bundle.putInt("POS",2);
                break;
            case R.id.BtnOrderDone:
                bundle.putInt("POS",3);
                break;
            case R.id.btnOrderCancel:
                bundle.putInt("POS",4);
                break;
            case R.id.RlAddr:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_shouhuodizhiguanli + Base.getUserEntity().getToken());
                bundle.putString("title", "收货地址管理");
                ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
                break;
            case R.id.RlInvitation:
                break;
            case R.id.RlOptinion:
                bundle.putString("url", MyConstants.h5Url + MyConstants.url_yijianfankui + Base.getUserEntity().getToken());
                bundle.putString("title", "意见反馈");
                ActivityUtil.next(_mActivity, WebViewActivity.class,bundle,false);
                break;
        }
    }


    @Override
    protected boolean rxBusRegist() {
        return true;
    }
}
