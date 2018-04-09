package com.moyou.moyouRms.model.jpush.abs;

import cn.jpush.api.push.model.PushPayload;

import com.moyou.moyouRms.model.jpush.PushEntity;

public abstract class AbstractJpushCondition {
	protected PushEntity pushEntity;

	public AbstractJpushCondition() {
	}

	public AbstractJpushCondition(PushEntity pushEntity) {
		this.pushEntity = pushEntity;
	}

	public PushEntity getPushEntity() {
		return pushEntity;
	}

	public void setPushEntity(PushEntity pushEntity) {
		this.pushEntity = pushEntity;
	}

	public abstract PushPayload instance(String overrideMsgId);

	public abstract PushPayload instance();
}
