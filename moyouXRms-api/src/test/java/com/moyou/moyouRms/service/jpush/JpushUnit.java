package com.moyou.moyouRms.service.jpush;

import javax.annotation.Resource;

import org.junit.Test;

import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.service.BaseJunit4;

/**
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class JpushUnit extends BaseJunit4{
	@Resource
	private MessagePushService messagePushService;
	@Resource
	private UserNotificationMapper userNotificationMapper;
	@Resource
	JpushService jpushService;
	@Test
	public void insert() {
		try {
			UserNotification userNotification = userNotificationMapper
					.queryUserNotificationByDiaryId(979);
			userNotification.setActionType(2);
			userNotification.setBusinessId(979);
			userNotification.setBusinessType(2);
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_PRAISE_DIARY);
			userNotification.setSendUserId(10892);
			jpushService.sendNotificationPushCustomMsgToDB(userNotification);
//			Map<String, String> extras = new HashMap<>();
//			extras.put("key", CONSTANT.REG_PUSH);
//			PushPayload pushPayload = PushPayloadBuilder.buildCustomMsg(extras,
//					PropertiesUtil.bundle(CONSTANT.REG_PUSH, (Object[]) new String[] { "33333" }),
//					"8240bef4a65544e8b5c7364442270942");
//			messagePushService.sendPushTryCatch(pushPayload);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
