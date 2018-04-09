package com.moyou.moyouRms.model.jpush;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.model.jpush.abs.AbstractJpushCondition;

public class InnerPush extends AbstractJpushCondition {
	public InnerPush() {}

	public InnerPush(PushEntity pushEntity) {
		super(pushEntity);
	}

	@Override
	public PushPayload instance(String overrideMsgId) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(this.pushEntity.getAliases()))
				.setMessage(Message.newBuilder()
						.addExtras(this.pushEntity.getExtras())
						.setMsgContent(this.pushEntity.getMsgEntity().getShortMsgContent())
						.build())
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.setTimeToLive(CONSTANT.TIME_TO_LIVE)
						.setOverrideMsgId(Long.valueOf(overrideMsgId)).setSendno(1)
						.build())
				.build();
	}
	
	@Override
	public PushPayload instance() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(this.pushEntity.getAliases()))
				.setMessage(Message.newBuilder()
						.addExtras(this.pushEntity.getExtras())
						.setMsgContent(this.pushEntity.getMsgEntity().getShortMsgContent())
						.build())
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.setTimeToLive(CONSTANT.TIME_TO_LIVE)
						.build())
				.build();
	}
}
