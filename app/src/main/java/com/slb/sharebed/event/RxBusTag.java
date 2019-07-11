package com.slb.sharebed.event;

/**
 * Created by Gifford on 2017/11/9.
 */

public class RxBusTag {
	public static final String CLOSE_LEFTMENU="close_leftmenu";
	public static final String OPEN_LEFTMENU="open_leftmenu";
	public static final String INVESTOR_UPDATE="investor_update";
	public static final String INVESTOR_UPDATE_PROOF="investor_update_proof";
	public static final String ELIGIBLE_CHILD_RECYCLER_CLICK="eligible_child_recycler_click";
	public static final String ELIGIBLE_UPLOAD="eligible_upload";
	public static final String NET_CHANG="net_change";
	public static final String INVESTOR_REFRESH="investor_refresh";
	public static final String INVESTOR_REFRESH_FRIST="investor_refresh_FRIST";
	public static final String VideoRecordOverEvent="video_record_over";
	public static final String LaterUploadEvent="later_upload";

	public static final String MAIN_TAB_CHANGE="main_tab_change";
	public static final String REFRESH_ORDER_LIST="refresh_order_list";
	public static final String REFRESH_VISIT_LIST="refresh_visit_list";
	public static final String MESSAGE_CHECKED="msg_check";
	public static final String MESSAGE_READED="msg_readed";
	public static final String MESSAGE_UP="msg_up";
	public static final String MESSAGE_RECEIVED="msg_received";
	public static final String VIDEO_COMPLATE="video_complate";

	public static final String MINE_MOBILE = "mine_mobile";
	public static final String MSG_READ = "msg_read";

	public static final String LOGIN_MECHANISM="login_mechanism";
	/**风险揭示书签署*/
	public static final String RISK_SIGN="risk_sign";
	public static final String SUPPLEMENT_SIGN="supplement_sign";

	public static final String INVESTOR_PROOF_CHANGED="investor_proof_changed";
	public static final String INVESTOR_DETAIL_EIGIBLE_PROOF_SELECT="investor_detail_eigible_proof_select";
	public static final String INVESTOR_DETALE_DEL_PROOF = "investor_detale_del_proof";
	public static final String UPLOADIDCAED="upload_idcard";
    public static final String UPLOADIDCAED_DELETE="upload_idcard_delete";
	public static final String APPOINT_PRODUCT_EVENT="appint_product_event";
	public static final String GESTURE="gesture_lock";
	public static final String ORGUEDITMANAGEROWNINVENSTEM = "OrgEditManagerOwnInvenstem";

	public static final String INVESTOR_INFO_MODULE="investor_info_module";

	public static final String VIDEO_REMOTE_COMPLETE="video_remote_complete";


	/*************************************以下为新增加云信使用的事件***********************************************/
	public static final String NimReLogin="nim_re_login";//需要重新登录
	public static final String NimLoginSuccess="nim_login_success";//登录成功
	public static final String NimKillOtherDevices="nim_kill_otherdevices";//杀掉其他设备登录账户
	public static final String NImCanelAccept="nim_incoming_canel";//呼叫被其他端截获
	public static final String NimOtherHangUp="nim_another_hangup";//对方挂断
	public static final String NimAskCalling="nim_ask_calling";//对方应答呼叫事件
	public static final String NimControlCommand="NimControlCommand";
	public static final String NimInComing="nim_incoming";//来电
	public static final String NimCallingEvent="nim_calling";//呼叫回调（不是接听）
	public static final String NimAcceptEvent="nim_accept";//接听成功
	public static final String NimJoinedChannel="nim_joined_channel";//服务器连接成功
	public static final String NimExitChannel="nim_exit_channel";//退出频道
	public static final String NimUserJoined="nim_user_joined";//用户加入频道
	public static final String NimUserLeave="nim_user_leave";//用户离开频道
	public static final String NimProtocolIncompatible="nim_protocol_incompatible";//双方协议版本不一致
	public static final String NimDisconnectServer="nim_disconnect_server";//从服务器断开连接, 当自己断网后超时
	public static final String NimCallEstablished="nim_call_established";//会话成功建立
	public static final String NimDeviceEvent="nim_device_event";//语音采集设备和视频采集设备事件通知
	public static final String NimConnectionTypeChanged="nim_connection_type_changed";//客户端网络类型发生变化
	public static final String NimFirstVideoFrameAvailable="nim_fst_video_frame_available";//用户第一帧视频数据绘制前通知
	public static final String NimFirstVideoFrameRendered="nim_fst_video_frame_rendered";//第一帧绘制通知
}
