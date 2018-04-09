package com.moyou.moyouRms.util;

import java.util.Arrays;
import java.util.Collection;
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
import com.moyou.moyouRms.interceptor.PageBean;

/**
 * 实体
 * 
 * @author PzC.
 * @date 2016年11月16日 下午5:07:47
 * 
 */
public class EntityUtil {
	/**
	 * 单参数处理
	 * 
	 * @param param
	 * @return
	 */
	public static String processSingleParam(String param) {
		Collection<Object> collection = JsonUtil.toMap(param).values();
		return StreamUtils.findFirst(collection).toString();
	}

	/**
	 * 分页 PageBean
	 * 
	 * @param params
	 * @return
	 */
	public static PageBean instanceOfPageBean(Map<String, Object> params) {
		PageBean pageBean = JsonUtil.toObject(params, PageBean.class);
		pageBean.setConditions(params);
		return pageBean;
	}

	/**
	 * instance of PushPayload
	 * 
	 * @param extras
	 * @param content
	 * @param receiveUserId
	 * @return
	 */
	public static PushPayload instanceOfPushPayload(Map<String, String> extras, String content,
			String receiveUserId) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(Arrays.asList(receiveUserId)))
				.setMessage(Message.newBuilder().addExtras(extras).setMsgContent(content).build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true)
								.setTimeToLive(CONSTANT.TIME_TO_LIVE).build()).build();
	}

	/**
	 * instance of PushPayload
	 * 
	 * @param extras
	 * @param content
	 * @param receiveUserId
	 * @return
	 */
	public static PushPayload instanceOfPushPayloadNotification(Map<String, String> extras,
			String content, String receiveUserId) {
		boolean app = false;
		String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
		if (appName.indexOf("test") < 0) {
			app = true;
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(Arrays.asList(receiveUserId)))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(content)
								.addPlatformNotification(
										AndroidNotification.newBuilder().addExtras(extras).build())
								.addPlatformNotification(
										IosNotification.newBuilder().setContentAvailable(true)
												.addExtras(extras).setSound("default").build())
								.build())
				.setOptions(
						Options.newBuilder().setTimeToLive(CONSTANT.TIME_TO_LIVE)
								.setApnsProduction(app).build()).build();
	}

	/**
	 * 自定义推送 一分钟600次调用，一次最多1000
	 * https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push/
	 * 
	 * @param extras
	 * @param content
	 * @param receiveUserId
	 * @return
	 */
	public static PushPayload instanceOfPushPayload(Map<String, String> extras, String content,
			String[] receiveUserId) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias((receiveUserId)))
				.setMessage(Message.newBuilder().addExtras(extras).setMsgContent(content).build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true)
								.setTimeToLive(CONSTANT.TIME_TO_LIVE).build()).build();
	}

	public static PushPayload instanceOfNotificationPushPayload(Map<String, String> extras,
			String content, String[] receiveUserId) {
		boolean app = false;
		String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
		if (appName.indexOf("test") < 0) {
			app = true;
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias((receiveUserId)))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(content)
								.addPlatformNotification(
										AndroidNotification.newBuilder().addExtras(extras).build())
								.addPlatformNotification(
										IosNotification.newBuilder().setContentAvailable(true)
												.addExtras(extras).setSound("default").build())
								.build())
				.setOptions(
						Options.newBuilder().setTimeToLive(CONSTANT.TIME_TO_LIVE)
								.setApnsProduction(app).build()).build();
	}
}
