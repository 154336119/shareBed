package com.slb.sharebed.http.bean;

import com.slb.sharebed.R;
import com.slb.sharebed.ui.activity.SuccessActivity;

/**
 * Created by Gifford on 2017/11/21.
 *
 * 系统投资者分类
 */

public enum SuccessTypeEnum {
	TYPE_100("注册成功","恭喜您，注册成功！","欢迎您的使用","进入首页", SuccessActivity.TYPE_100, R.mipmap.rectangle);


	/**
	 * TYPE_100 - 成功页面——注册成功
	 */
	private String title;
	private String titleContent;
	private String content;
	private String btnText;
	private int imgId;
	private int type;
	SuccessTypeEnum(String title, String titleContent, String content, String btnText, int type,int imgId) {
		this.title = title;
		this.titleContent = titleContent;
		this.content = content;
		this.btnText = btnText;
		this.type = type;
		this.imgId = imgId;
	}

	public static SuccessTypeEnum getEnumForType(int type){
		SuccessTypeEnum data=null;
		for (SuccessTypeEnum successTypeEnum : SuccessTypeEnum.values()){
			if(type==(successTypeEnum.getType())){
				data= successTypeEnum;
				break;
			}
		}return data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleContent() {
		return titleContent;
	}

	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

}
