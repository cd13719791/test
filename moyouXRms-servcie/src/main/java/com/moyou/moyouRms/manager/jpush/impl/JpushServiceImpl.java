package com.moyou.moyouRms.manager.jpush.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.jpush.PushPayloadBuilder;
import com.moyou.moyouRms.model.notification.PushObject;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.service.notification.UserNotificationService;
import com.moyou.moyouRms.util.EntityUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class JpushServiceImpl implements JpushService {
	static Logger LOG = LoggerFactory.getLogger(JpushServiceImpl.class);
	private static final String MASTER_SECRET = "7a41651994aa8e85ddbc0917";
	private static final String APP_KEY = "9f79017003d9a6d705efa9d7";
	private static final JPushClient JPUSH_CLIENT = new JPushClient(MASTER_SECRET, APP_KEY);
	@Resource
	private UserNotificationService notificationService;
	@Resource
	private UserInfoMapper infoMapper;

	/**
	 * @Title: pushMessage
	 * @Description: 推送消息
	 * @param payload
	 * @return PushResult 返回类型
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	@Override
	public PushResult sendPush(PushPayload payload) throws APIConnectionException,
			APIRequestException {
		return JPUSH_CLIENT.sendPush(payload);
	}

	/**
	 * @param userNotification
	 * @Title: sendPushTryCatch
	 * @Description: try catch 推送
	 */
	@Override
	public int sendPushTryCatch(UserNotification userNotification) {
		int result = 0;
		PushObject pushObject = pushObject(userNotification.getSendUserId(),
				userNotification.getReceiveUserId(), userNotification.getPushAlertEnum());
		if (pushObject.getPushId() == null) {
			return 0;
		}
		try {
			JPUSH_CLIENT.sendPush(PushPayloadBuilder.buildNotify(pushObject,
					userNotification.getPushAlertEnum()));
			result = notificationService.insertUserNotification(userNotification);
		} catch (APIConnectionException e) {
			LOG.error("连接错误，稍后尝试" + e);
		} catch (APIRequestException e) {
			LOG.error("极光推送内部错误", e);
			LOG.error("网络请求状态" + e.getStatus());
			LOG.error("错误状态码" + e.getErrorCode());
			LOG.error("错误信息" + e.getErrorMessage());
			LOG.error("信息ID" + e.getMsgId());
			LOG.error("极光推送错误信息:" + e.getErrorMessage());
		}
		return result;
	}

	@Override
	public int sendNotificationPushCustomMsgToDB(UserNotification userNotification) {
		if (userNotification.getSendUserId().intValue() == userNotification.getReceiveUserId()
				.intValue()) {
			return 0;
		}
		int result = 0;
		PushObject pushObject = pushObject(userNotification.getSendUserId(),
				userNotification.getReceiveUserId(), userNotification.getPushAlertEnum());
		if (pushObject.getPushId() == null) {
			return 0;
		}
		// 推送一条自定义消息给用户
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.NOTIFICATION_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, userNotification
				.getPushAlertEnum().getKey());
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, pushObject.getAlert());
		PushPayload pushPayload = EntityUtil.instanceOfPushPayload(extras, pushObject.getAlert(),
				pushObject.getPushId());
		try {
			// PushResult pr =
			JPUSH_CLIENT.sendPush(pushPayload);
			result = notificationService.insertUserNotification(userNotification);
			// System.out.println(JsonUtil.toJson(pr));
		} catch (Exception e) {
			LOG.error(
					StringUtil.getTraceInfo() + "极光推送通知消息错误:[" + JsonUtil.toJson(userNotification)
							+ "]", e);
		}
		return result;
	}

	/**
	 * 只推送不持久化
	 * 
	 * @param sendUserId
	 *            发送者用户 id
	 * @param receiveUserId
	 *            接受者用户 id
	 */
	@Override
	public void sendPushTryCatch(Integer sendUserId, Integer receiveUserId,
			PushAlertEnum pushAlertEnum) {
		PushObject pushObject = pushObject(sendUserId, receiveUserId, pushAlertEnum);
		if (pushObject.getPushId() == null) {
			return;
		}
		try {
			JPUSH_CLIENT.sendPush(PushPayloadBuilder.buildNotify(pushObject, pushAlertEnum));
		} catch (APIConnectionException e) {
			LOG.error("连接错误，稍后尝试" + e);
		} catch (APIRequestException e) {
			LOG.error("极光推送内部错误", e);
			LOG.info("网络请求状态" + e.getStatus());
			LOG.info("错误状态码" + e.getErrorCode());
			LOG.info("错误信息" + e.getErrorMessage());
			LOG.info("信息ID" + e.getMsgId());
			LOG.info("极光推送错误信息:" + e.getErrorMessage());
		}
	}

	private PushObject pushObject(Integer sendUserId, Integer receiveUserId,
			PushAlertEnum pushAlertEnum) {
		PushObject pushObject = infoMapper.queryPushObject(sendUserId, receiveUserId);
		if (pushObject == null) {
			return new PushObject();
		}
		pushObject.setAlert(pushAlertEnum);
		return pushObject;
	}

	@Override
	public boolean sendBarPush(String[] pushChat, PushAlertEnum pushAlertEnum, String msg) {
		boolean index = false;
		try {
			PushResult result = JPUSH_CLIENT.sendPush(PushPayloadBuilder.build(pushChat,
					CONSTANT.JPUSH_NOTIFICATION_MSG_PUSH_KEY, pushAlertEnum.getKey(),
					pushAlertEnum, msg));
			index = result.getResponseCode() == 200 ? true : false;
		} catch (APIConnectionException | APIRequestException e) {
			LOG.info("极光推送错误" + e.getMessage());
		}
		return index;
	}

	@Override
	public int sendMessgePushCustomMsgToDB(UserNotification userNotification) {
		if (userNotification.getSendUserId().intValue() == userNotification.getReceiveUserId()
				.intValue()) {
			return 0;
		}
		int result = 0;
		PushObject pushObject = pushObject(userNotification.getSendUserId(),
				userNotification.getReceiveUserId(), userNotification.getPushAlertEnum());
		if (pushObject.getPushId() == null) {
			return 0;
		}
		// 推送一条自定义消息给用户
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.NOTIFICATION_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, userNotification
				.getPushAlertEnum().getKey());
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, pushObject.getAlert());
		PushPayload pushPayload = EntityUtil.instanceOfPushPayload(extras, pushObject.getAlert(),
				pushObject.getPushId());
		try {
			JPUSH_CLIENT.sendPush(pushPayload);
			result = notificationService.insertUserNotification(userNotification);
		} catch (Exception e) {
			// LOG.error(StringUtil.getTraceInfo() + "极光推送通知消息错误:[" +
			// JsonUtil.toJson(userNotification) + "]", e);
		}
		return result;
	}
}
