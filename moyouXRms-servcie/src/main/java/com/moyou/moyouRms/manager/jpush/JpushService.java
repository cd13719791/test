package com.moyou.moyouRms.manager.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.model.notification.UserNotification;

public interface JpushService {
	/**
	 * 推送
	 * 
	 * @param payload
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	PushResult sendPush(PushPayload payload) throws APIConnectionException, APIRequestException;

	/**
	 * try catch 推送
	 * 
	 * @param userNotification
	 */
	int sendPushTryCatch(UserNotification userNotification);

	/**
	 * 自定义消息，发送自定义消息
	 * 
	 * @param userNotification
	 * @return
	 */
	int sendMessgePushCustomMsgToDB(UserNotification userNotification);

	/**
	 * 通知，发送自定义消息
	 * 
	 * @param userNotification
	 * @return
	 */
	int sendNotificationPushCustomMsgToDB(UserNotification userNotification);

	/**
	 * 只推送不持久化
	 * 
	 * @param sendUserId
	 *            发送者用户 id
	 * @param receiveUserId
	 *            接受者用户 id
	 * @param pushAlertEnum
	 *            推送 alert 枚举
	 */
	void sendPushTryCatch(Integer sendUserId, Integer receiveUserId, PushAlertEnum pushAlertEnum);

	/**
	 * 通知推送 只推送 不持久化
	 * 
	 * @param payload
	 * @return
	 */
	boolean sendBarPush(String[] pushChat, PushAlertEnum pushAlertEnum, String msg);
}
