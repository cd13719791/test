package com.moyou.moyouRms.model.notification;

import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.util.PropertiesUtil;

public class PushObject {
	private String alert;// 推送 alert
	private String sendUserNickname;// 用户的昵称
	private String pushId;// 极光推送 id

	public String getAlert() {
		return alert;
	}

	public void setAlert(PushAlertEnum pushAlertEnum) {
		this.alert = PropertiesUtil.bundle(pushAlertEnum.getKey(), sendUserNickname);
	}
	
	public String getSendUserNickname() {
		return sendUserNickname;
	}

	public void setSendUserNickname(String sendUserNickname) {
		this.sendUserNickname = sendUserNickname;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
}
