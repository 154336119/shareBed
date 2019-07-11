package danfei.shulaibao.widget;

/**
 * 错误类别请自行扩展
 * 注意在类BaseBrvahRefreshFragment的getList方法的回调中按错误码进行处理
 */
public enum AdapterDefaultEnum {
	DEFAULT(R.mipmap.zanwu,"暂无数据"),
	NO_MESSAGE(R.mipmap.zanwu,"暂无消息通知"),
	BREAK_INTERNET(R.mipmap.zanwu,"无网络"),
	NO_SEARCHRESULT(R.mipmap.zanwu,"暂无搜索结果");
	
	private int imageResId;
	private String textString;

	public int getImageResId() {
		return imageResId;
	}

	public String getTextString() {
		return textString;
	}

	AdapterDefaultEnum(int imageResId, String textString) {
		this.imageResId = imageResId;
		this.textString = textString;
	}
}
