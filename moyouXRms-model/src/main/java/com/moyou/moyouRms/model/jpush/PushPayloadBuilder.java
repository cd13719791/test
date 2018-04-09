package com.moyou.moyouRms.model.jpush;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.model.notification.PushObject;
import com.moyou.moyouRms.util.PropertiesUtil;

public final class PushPayloadBuilder {
	/**
	 * 自定义消息体构建
	 * 
	 * @param extras
	 * @param content
	 * @param receivePushId
	 *            接收者极光服务器注册的id
	 * @return
	 */
	public static PushPayload buildCustomMsg(Map<String, String> extras, String content,
			String receivePushId) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(Arrays.asList(receivePushId)))
				.setMessage(Message.newBuilder().addExtras(extras).setMsgContent(content).build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true)
								.setTimeToLive(CONSTANT.TIME_TO_LIVE).build()).build();
	}

	public PushPayload buildPushObject_all_alias_alert(String content) {
		return PushPayload.newBuilder().setPlatform(Platform.all())// 设置接受的平台
				.setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
				.setNotification(Notification.alert(content)).build();
	}

	/**
	 * 广播消息体构建
	 * 
	 * @param pushObject
	 * @param pushAlertEnum
	 * @return
	 */
	public static PushPayload buildNotify(PushObject pushObject, PushAlertEnum pushAlertEnum) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(pushObject.getPushId()))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(pushObject.getAlert())
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.addExtra("action", pushAlertEnum.getKey()).build())
								.addPlatformNotification(
										IosNotification.newBuilder().setContentAvailable(true)
												.addExtra("action", pushAlertEnum.getKey()).build())
								.build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true)
								.setTimeToLive(CONSTANT.TIME_TO_LIVE).build()).build();
	}

	/**
	 * 通知所有设备
	 * 
	 * @param pushObject
	 * @param pushAlertEnum
	 * @param alert
	 * @return
	 */
	public static PushPayload build(String[] pushChatId, String key, String value,
			PushAlertEnum pushAlertEnum, String alert) {
		Map<String, String> ext = new HashMap<String, String>();
		ext.put(key, value);
		boolean app = false;
		String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
		if (appName.indexOf("test") < 0) {
			app = true;
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(pushChatId))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(alert)
								.addPlatformNotification(
										AndroidNotification.newBuilder().addExtras(ext).build())
								.addPlatformNotification(
										IosNotification.newBuilder().setContentAvailable(true)
												.addExtras(ext).setSound("default").build())
								.build())
				.setOptions(
						Options.newBuilder().setTimeToLive(CONSTANT.TIME_TO_LIVE)
								.setApnsProduction(app).build()).build();

	}
}
