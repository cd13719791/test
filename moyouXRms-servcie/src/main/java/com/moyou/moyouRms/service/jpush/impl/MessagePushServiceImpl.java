package com.moyou.moyouRms.service.jpush.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.report.ReceivedsResult;

import com.moyou.moyouRms.model.jpush.InnerPush;
import com.moyou.moyouRms.model.jpush.PushEntity;
import com.moyou.moyouRms.model.msg.MsgSystem;
import com.moyou.moyouRms.service.jpush.MessagePushService;
import com.moyou.moyouRms.service.msg.MsgService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 
 * @ClassName: MessagePushServiceImpl
 * @Description: 消息推送
 * @author YB
 * @date 2016年11月25日 上午11:10:50
 *
 */
@Service
public class MessagePushServiceImpl implements MessagePushService {
	protected static Logger LOG = LoggerFactory.getLogger(MessagePushServiceImpl.class);
	private static final String MASTER_SECRET = "7a41651994aa8e85ddbc0917";
	private static final String APP_KEY = "9f79017003d9a6d705efa9d7";
	private static final JPushClient JPUSH_CLIENT = new JPushClient(MASTER_SECRET, APP_KEY);
	@Resource
	private MsgService msgService;

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
	 * @param condition
	 * @Title: sendPushTryCatch
	 * @Description: try catch 推送
	 * @param payload
	 * @param logger
	 */
	@Override
	public int sendPushTryCatch(InnerPush condition) {
		PushEntity pushEntity = condition.getPushEntity();
		String msgIdInDataBase = msgService.queryJpushMsgId(pushEntity);

		PushResult pushResult = null;
		int result = 0;
		try {
			if (StringUtils.isEmpty(msgIdInDataBase)) {
				pushResult = JPUSH_CLIENT.sendPush(condition.instance());
				pushEntity.getMsgEntity().setJpushMsgId(String.valueOf(pushResult.msg_id));
				result = msgService.insertMsgOperation(pushEntity);
			} else {
				pushResult = JPUSH_CLIENT.sendPush(condition.instance(msgIdInDataBase));
				pushEntity.getMsgEntity().setJpushMsgId(msgIdInDataBase);
				result = msgService.updateMsgOperation(pushEntity);
			}
			LOG.info("返回结果" + pushResult);
		} catch (APIConnectionException e) {
			LOG.error("连接错误，稍后尝试" + e);
		} catch (APIRequestException e) {
			LOG.error(JsonUtil.toJson(condition));
			LOG.error("极光推送内部错误", e);
			LOG.info("网络请求状态" + e.getStatus());
			LOG.info("错误状态码" + e.getErrorCode());
			LOG.info("错误信息" + e.getErrorMessage());
			LOG.info("信息ID" + e.getMsgId());
			LOG.info("极光推送错误信息:" + e.getErrorMessage());
		}
		return result;
	}

	/**
	 * 注册推送
	 * 
	 * @param innerPush
	 * @return
	 */
	@Override
	public int regSendPushTryCatch(InnerPush innerPush) {
		int result = 0;
		try {
			PushResult pushResult = JPUSH_CLIENT.sendPush(innerPush.instance());
			result = msgService.insertMsgSystem((MsgSystem) innerPush.getPushEntity()
					.getMsgEntity());
			LOG.info("返回结果" + pushResult);
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
		return result;
	}

	/**
	 * 只推送不持久化
	 * 
	 * @param payload
	 * @return
	 */
	@Override
	public String sendPushTryCatch(PushPayload pushPayload) {
		String sendno = "";
		try {
			PushResult pushResult = JPUSH_CLIENT.sendPush(pushPayload);
			// LOG.info("极光推送返回结果" + JsonUtil.toJson(pushResult));
			return pushResult.sendno + "";
		} catch (Exception e) {
			LOG.error("极光推送错误", e);
		}
		return sendno;
	}

	@Override
	public ReceivedsResult result(String msgId) {
		ReceivedsResult result = null;
		try {
			result = JPUSH_CLIENT.getReportReceiveds(msgId);
			LOG.debug("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
		}
		return result;
	}

}
