package com.moyou.moyouRms.manager.pushmsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.jpush.api.push.model.PushPayload;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.jpush.MessagePushService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.util.EntityUtil;

/**
 * @Title: 消息推送
 * @Description: TODO
 * @author: liuxinyi
 * @date: 2017年7月20日 下午4:07:20
 * @email liuxinyi@mousns.com
 * @version V3.4
 */
@Component
public class PushMsgManager {
	@Resource
	MessagePushService messagePushService;

	/** 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(PushMsgManager.class);
	private static final int BATCH_PUSH_TO_DB = 500;// 批量入库

	@Autowired
	private UserInfoService userInfoService;
	@Resource
	private MsgSystemXService msgSystemXService;
	@Resource
	private JpushService jpushService;

	public Msg sendPushMsg(MsgSystemX record, String push_content) {
		Msg msg = Msg.makeSuccessMsg();

		List<UserInfo> users = userInfoService.queryReallyUserIdAndPushChatId();
		List<UserInfo> tmpUserList = new ArrayList<UserInfo>(BATCH_PUSH_TO_DB);
		int i = 0, batchNumber = 0;
		for (UserInfo userInfo : users) {
			if (i == BATCH_PUSH_TO_DB) {// 达到入库阈值 入库
				try {
					msgSystemXService.addSysMsg(tmpUserList, record, push_content);
					LOG.info("toDb   batchNumber:" + batchNumber);
					batchNumber = batchNumber + BATCH_PUSH_TO_DB;
				} catch (Exception e) {
					LOG.error("toDb is fail batchNumber:" + batchNumber, e);
				}
				tmpUserList = new ArrayList<UserInfo>(BATCH_PUSH_TO_DB);
				i = 0;
			}
			tmpUserList.add(userInfo);
			i++;
		}
		try {
			// 入库
			if (tmpUserList != null && tmpUserList.size() > 0) {
				batchNumber = batchNumber + tmpUserList.size();
				msgSystemXService.addSysMsg(tmpUserList, record, push_content);
				LOG.info("toDb   batchNumber:" + batchNumber);
			}
		} catch (Exception e) {
			LOG.error("toDb is fail batchNumber:" + batchNumber, e);
			msg = Msg.makeErrorMsg();
		}

		// 最后统一推送
		int i1 = 0, batchNumber1 = 0;
		for (UserInfo userInfo : users) {
			if (i1 == BATCH_PUSH_TO_DB) {// 达到推送阈值 推送
				try {
					batchSend(tmpUserList, record, push_content);
					LOG.info("sendPushMsg   batchNumber1:" + batchNumber1);
					batchNumber1 = batchNumber1 + BATCH_PUSH_TO_DB;
					Thread.sleep(5 * 1000);// 睡眠5秒，频率放慢
				} catch (Exception e) {
					LOG.error("sendPushMsg is fail batchNumber1:" + batchNumber1, e);
				}
				tmpUserList = new ArrayList<UserInfo>(BATCH_PUSH_TO_DB);
				i1 = 0;
			}
			tmpUserList.add(userInfo);
			i1++;
		}
		try {
			// 开始推送
			if (tmpUserList != null && tmpUserList.size() > 0) {
				batchNumber1 = batchNumber1 + tmpUserList.size();
				batchSend(tmpUserList, record, push_content);
				LOG.info("sendPushMsg   batchNumber1:" + batchNumber1);
			}
		} catch (Exception e) {
			LOG.error("sendPushMsg is fail batchNumber1:" + batchNumber1, e);
			msg = Msg.makeErrorMsg();
		}
		return msg;
	}

	/**
	 * 批量推送
	 * 
	 * @param userInfoList
	 * @param extras
	 * @param record
	 * @param push_content
	 */
	private void batchSend(List<UserInfo> userInfoList, MsgSystemX record, String push_content) {
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.SYS_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, record.getMsgSendType());
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, push_content);
		if (userInfoList != null && userInfoList.size() > 0) {
			List<String> receiveuserIdList = new ArrayList<String>(BATCH_PUSH_TO_DB);
			userInfoList.parallelStream().forEach(userInfo -> {
				String pushChatId = userInfo.getPushChatId();
				receiveuserIdList.add(pushChatId);
			});
			String[] receiveuserIdArr = new String[receiveuserIdList.size()];
			receiveuserIdArr = receiveuserIdList.toArray(receiveuserIdArr);
			/*// 推送消息给用户 通知
			PushPayload pushPayload = EntityUtil.instanceOfNotificationPushPayload(extras,
					push_content, receiveuserIdArr);
			messagePushService.sendPushTryCatch(pushPayload);// 批量推送
*/			
			// 推送消息给用户 自定义消息
			PushPayload pushPayload = EntityUtil.instanceOfPushPayload(extras, push_content, receiveuserIdArr);
			messagePushService.sendPushTryCatch(pushPayload);// 批量推送
		}
	}
}
