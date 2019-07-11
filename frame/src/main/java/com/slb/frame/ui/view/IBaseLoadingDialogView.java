package com.slb.frame.ui.view;

/**
 * 描述：
 * Created by Administrator
 * on 2016/9/14.
 */
public interface IBaseLoadingDialogView extends IBaseView {
   /** 显示正在加载提示框*/
   public void showLoadingDialog(String msg);
   /** 销毁正在加载提示框*/
   public void loadingDialogDismiss();
}
