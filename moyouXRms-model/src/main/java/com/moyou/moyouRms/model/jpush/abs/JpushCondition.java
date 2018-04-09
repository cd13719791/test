package com.moyou.moyouRms.model.jpush.abs;

import cn.jpush.api.push.model.PushPayload;

public interface JpushCondition {
	
	PushPayload instance(String overrideMsgId);
	
	PushPayload instance();
}
