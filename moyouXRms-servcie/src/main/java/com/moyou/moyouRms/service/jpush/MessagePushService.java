package com.moyou.moyouRms.service.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.report.ReceivedsResult;

import com.moyou.moyouRms.model.jpush.InnerPush;

public interface MessagePushService {

	/**
	 * 推送
	 * 
	 * @param payload
	 * @return
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	PushResult sendPush(PushPayload payload) throws APIConnectionException, APIRequestException;

	/**
	 * try catch 推送
	 * 
	 * @param condition
	 * @return
	 */
	int sendPushTryCatch(InnerPush condition);
	
	/**
	 * 只推送不持久化
	 * 
	 * @param payload
	 * @return
	 */
	String sendPushTryCatch(PushPayload pushPayload);

	/**
	 * 注册推送
	 * 
	 * @param innerPush
	 * @return
	 */
	int regSendPushTryCatch(InnerPush innerPush);
	
	ReceivedsResult result(String msgId);
}
