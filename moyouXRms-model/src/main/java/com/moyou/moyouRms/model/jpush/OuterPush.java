package com.moyou.moyouRms.model.jpush;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.model.jpush.abs.AbstractJpushCondition;

public class OuterPush extends AbstractJpushCondition{
	public OuterPush() {}

	public OuterPush(PushEntity pushEntity) {
		super(pushEntity);
	}

	@Override
	public PushPayload instance(String overrideMsgId) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(this.pushEntity.getAliases()))
				.setOptions(Options.newBuilder()
						.setOverrideMsgId(Long.valueOf(overrideMsgId))
						.setTimeToLive(CONSTANT.TIME_TO_LIVE)
						.setApnsProduction(true)
						.build())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setAlert(this.pushEntity.getMsgEntity().getShortMsgContent())
								.addExtras(this.pushEntity.getExtras())
								.build())
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(this.pushEntity.getMsgEntity().getShortMsgContent())
								.setBadge(CONSTANT.BADGE)
								.addExtras(this.pushEntity.getExtras())
								.setSound(CONSTANT.SOUND)
								.build())
						.build())
				.build();
	}

	@Override
	public PushPayload instance() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(this.pushEntity.getAliases()))
				.setOptions(Options.newBuilder()
						.setTimeToLive(CONSTANT.TIME_TO_LIVE)
							.setApnsProduction(true)
						.build())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setAlert(this.pushEntity.getMsgEntity().getShortMsgContent())
								.addExtras(this.pushEntity.getExtras())
								.build())
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(this.pushEntity.getMsgEntity().getShortMsgContent())
								.setBadge(CONSTANT.BADGE)
								.addExtras(this.pushEntity.getExtras())
								.setSound(CONSTANT.SOUND)
								.build())
						.build())
				.build();
	}
}
