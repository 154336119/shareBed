package com.slb.sharebed.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.sharebed.R;
import com.slb.sharebed.ui.contract.BindPhoneContract;
import com.slb.sharebed.ui.contract.ScanContract;
import com.slb.sharebed.ui.presenter.ScanPersenter;
import com.slb.sharebed.util.AndroidAdjustResizeBugFix;
import com.slb.sharebed.util.KeyBoardChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanAcitivty  extends BaseMvpActivity<ScanContract.IView, ScanContract.IPresenter>
        implements  QRCodeView.Delegate ,ScanContract.IView {

    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    @BindView(R.id.TvHandInput)
    TextView TvHandInput;
    @BindView(R.id.TvFlashlight)
    TextView TvFlashlight;
    boolean isLightOpen = false;
    @BindView(R.id.LlInputCode)
    LinearLayout LlInputCode;
    @BindView(R.id.TvFee)
    TextView TvFee;
    @BindView(R.id.BtnUsedBed)
    ImageView BtnUsedBed;
    @BindView(R.id.BtnScanCode)
    ImageView BtnScanCode;
    @BindView(R.id.EtInput)
    EditText EtInput;
    @BindView(R.id.RootView)
    RelativeLayout RootView;
    private boolean mBackEnable = false;
    private boolean mIsBtnBack = false;
    private int rootBottom = Integer.MIN_VALUE;
    KeyBoardChangeListener keyBoardChangeListener;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect r = new Rect();
            RootView.getGlobalVisibleRect(r);
            // 进入Activity时会布局，第一次调用onGlobalLayout，先记录开始软键盘没有弹出时底部的位置
            if (rootBottom == Integer.MIN_VALUE) {
                rootBottom = r.bottom;
                return;
            }
            // adjustResize，软键盘弹出后高度会变小
            if (r.bottom < rootBottom) {
                mBackEnable = false;
            } else {
                mBackEnable = true;
                if (mIsBtnBack) {
                    finish();
                }
            }
        }
    };

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
//        AndroidAdjustResizeBugFix.assistActivity(this);
        RootView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.setDarkMode(this);
        mZXingView.setDelegate(this);

        keyBoardChangeListener = new KeyBoardChangeListener(ScanAcitivty.this);
        keyBoardChangeListener.setKeyBoardListener(new KeyBoardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if(!isShow){
                    Animation animBottomOut = AnimationUtils.loadAnimation(ScanAcitivty.this,
                            R.anim.bottom_out);
                    animBottomOut.setDuration(240);
                    LlInputCode.setVisibility(View.GONE);
                    LlInputCode.startAnimation(animBottomOut);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
//
//            RootView.getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
//        } else {
//            RootView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
//        }
        super.onDestroy();
    }

    @SuppressLint("MissingPermission")
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        vibrate();
        mZXingView.startSpot(); // 开始识别
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @OnClick({R.id.TvHandInput, R.id.TvFlashlight, R.id.BtnUsedBed, R.id.BtnScanCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnUsedBed:
                break;
            case R.id.BtnScanCode:
                Animation animBottomOut = AnimationUtils.loadAnimation(ScanAcitivty.this,
                        R.anim.bottom_out);
                animBottomOut.setDuration(240);
                LlInputCode.setVisibility(View.GONE);
                LlInputCode.startAnimation(animBottomOut);

                hideKeyboard(EtInput);
                break;
            case R.id.TvHandInput:
                Animation animBottomIn = AnimationUtils.loadAnimation(ScanAcitivty.this,
                        R.anim.bottom_in);
                animBottomIn.setDuration(240);
                LlInputCode.setVisibility(View.VISIBLE);
                LlInputCode.startAnimation(animBottomIn);
                showKeyboard(EtInput);
                break;
            case R.id.TvFlashlight:
                if (isLightOpen) {
                    isLightOpen = false;
                    mZXingView.closeFlashlight(); // 关闭闪光灯
                } else {
                    isLightOpen = true;
                    mZXingView.openFlashlight(); // 打开闪光灯
                }
                break;
        }
    }

    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public ScanContract.IPresenter initPresenter() {
        return new ScanPersenter();
    }

    @Override
    public void openSuccess() {

    }

    @Override
    public void openFailed() {

    }
}

