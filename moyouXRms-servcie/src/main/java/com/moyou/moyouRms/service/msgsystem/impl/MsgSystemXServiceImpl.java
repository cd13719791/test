package com.moyou.moyouRms.service.msgsystem.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.push.model.PushPayload;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.MsgBarEnum;
import com.moyou.moyouRms.dao.msgsystem.MsgSystemXMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.user.UserMapper;
import com.moyou.moyouRms.model.msgBar.MsgBar;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.jpush.MessagePushService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.util.EntityUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class MsgSystemXServiceImpl implements MsgSystemXService {
	private static final int BATCH_PUSH_TO_DB = 1000;// 批量入库
	@Resource
	MsgSystemXMapper msgSystemXMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	MessagePushService messagePushService;
	@Autowired
	private UserInfoService userInfoService;
	private Integer USER_COUNT_500 = 500;

	/** 日志对象 */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(MsgSystemXServiceImpl.class);

	/**
	 * 添加一条警告
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int addSysMsgAndPushCustomMsg(MsgSystemX record) {
		Integer userId = record.getReceiveUserId();
		UserInfo userInfo = userInfoMapper.queryUserNickName(userId);// 获取用户昵称
		String nickname = userInfo.getNickname();
		String pushChatId = userInfo.getPushChatId();
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setState((short) 0);
		// 推送一条消息给用户
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.SYS_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, record.getMsgSendType());
		String push_content = record.getMsgContent();
		if (StringUtil.isEmpty(push_content)) {
			push_content = PropertiesUtil.bundle(record.getMsgSendType(),
					(Object[]) new String[] { nickname });
		}
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, push_content);
		PushPayload pushPayload = EntityUtil
				.instanceOfPushPayload(extras, push_content, pushChatId);
		String sendNo = messagePushService.sendPushTryCatch(pushPayload);
		record.setJpushMsgId(String.valueOf(sendNo));// 极光推送结果id
		record.setMsgContent(push_content);
		record.setShortMsgContent(push_content);
		return msgSystemXMapper.insert(record);
	}

	@Override
	public int addSysMsgAndPushCustomMsg(MsgSystemX record, String push_content) {
		Integer userId = record.getReceiveUserId();
		UserInfo userInfo = userInfoMapper.queryUserNickName(userId);// 获取用户昵称
		String pushChatId = userInfo.getPushChatId();
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setState((short) 0);
		// 推送一条消息给用户
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.SYS_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, record.getMsgSendType());
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, push_content);
		PushPayload pushPayload = EntityUtil
				.instanceOfPushPayload(extras, push_content, pushChatId);
		String sendNo = messagePushService.sendPushTryCatch(pushPayload);
		record.setJpushMsgId(String.valueOf(sendNo));// 极光推送结果id
		return msgSystemXMapper.insert(record);
	}

	@Override
	public int insertWarn(MsgSystemX record) {
		Integer userId = record.getReceiveUserId();
		UserInfo userInfo = userInfoMapper.queryUserNickName(userId);// 获取用户昵称
		String nickname = userInfo.getNickname();
		String pushChatId = userInfo.getPushChatId();
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setState((short) 0);
		record.setModeType((short) record.getModeType());
		record.setMsgSendType(CONSTANT.REG_LIMIT);
		// 推送一条消息给用户
		Map<String, String> extras = new HashMap<>();
		String push_content = PropertiesUtil.bundle(CONSTANT.REG_LIMIT,
				(Object[]) new String[] { nickname });
		extras.put("sys_msg_push_key", push_content);
		PushPayload pushPayload = EntityUtil
				.instanceOfPushPayload(extras, push_content, pushChatId);
		int sendNo = pushPayload.getSendno();
		messagePushService.sendPushTryCatch(pushPayload);
		record.setJpushMsgId(String.valueOf(sendNo));// 极光推送结果id
		record.setMsgTitle("陌友—系统限制");
		record.setMsgContent(push_content);
		record.setShortMsgContent(push_content);
		record.setSendUserId(0);
		record.setModeId(userId);
		return msgSystemXMapper.insert(record);
	}

	@Override
	public boolean addSysMsg(List<UserInfo> tmpUserList, MsgSystemX record, String push_content) {
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.SYS_MSG_PUSH_KEY);
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY_BUSINESS_TYPE, record.getMsgSendType());
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, push_content);
		insertBatch(tmpUserList, extras, record, push_content);
		return true;
	}

	@Override
	public boolean addSysMsgAndPushCustomMsgToAllUser(MsgBar msgBar) {
		try {
			List<String> tmpUSerList = userInfoService.queryReallyUserPushChatId();
			List<String> user500 = new ArrayList<String>(USER_COUNT_500);
			int j = 1;
			int i = j - 1;
			while (i < USER_COUNT_500 * j) {
				user500.add(tmpUSerList.get(i));
				if ((i + 1) % USER_COUNT_500 == 0) {
					pushMsg500(msgBar, user500);
					i = j * USER_COUNT_500;
					j++;
					user500 = new ArrayList<String>(USER_COUNT_500);
					continue;
				} else if ((i + 1) >= tmpUSerList.size() && user500.size() > 0) {
					pushMsg500(msgBar, user500);
					break;
				}
				i++;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void pushMsg500(MsgBar msgBar, List<String> user500) {
		Map<String, String> extras = new HashMap<>();
		extras.put(CONSTANT.JPUSH_CUSTOM_MSG_PUSH_KEY, CONSTANT.NOTIFICATION_BAR_PUSH);
		extras.put(CONSTANT.JPUSH_BAR_MSG_PUSH_KEY_BUSINESS_TYPE,
				MsgBarEnum.getDescByValue((int) msgBar.getModeType()));
		extras.put(CONSTANT.SYS_MSG_PUSH_CMENT_KEY, msgBar.getContent());
		String[] receiveuserIdArr = new String[user500.size()];
		receiveuserIdArr = user500.toArray(receiveuserIdArr);
		PushPayload pushPayload = EntityUtil.instanceOfNotificationPushPayload(extras,
				msgBar.getContent(), receiveuserIdArr);
		messagePushService.sendPushTryCatch(pushPayload);// 批量推送
	}

	/**
	 * 批量推送， 批量入库
	 * 
	 * @param userInfoList
	 * @param extras
	 * @param record
	 * @param push_content
	 */
	private void insertBatch(List<UserInfo> userInfoList, Map<String, String> extras,
			MsgSystemX record, String push_content) {
		if (userInfoList != null && userInfoList.size() > 0) {
			List<MsgSystemX> msgList = new ArrayList<MsgSystemX>(BATCH_PUSH_TO_DB);
			List<String> receiveuserIdList = new ArrayList<String>(BATCH_PUSH_TO_DB);
			userInfoList.stream().forEach(userInfo -> {
				MsgSystemX tmpRecord = new MsgSystemX();// 将旧实体内容添加到新的实体中
					tmpRecord.setCreateTime(new Date());
					tmpRecord.setUpdateTime(new Date());
					tmpRecord.setState((short) 0);
					tmpRecord.setModeType(MsgSystemX.CUSTOMMSG);
					String pushChatId = userInfo.getPushChatId();
					tmpRecord.setJpushMsgId("0");// 推送id
					tmpRecord.setReceiveUserId(userInfo.getUserId());
					tmpRecord.setMsgContent(record.getMsgContent());
					tmpRecord.setShortMsgContent(record.getShortMsgContent());
					tmpRecord.setMsgSendType(record.getMsgSendType());
					tmpRecord.setMsgTitle(record.getMsgTitle());
					tmpRecord.setSendUserId(0);
					tmpRecord.setH5Url(record.getH5Url());
					receiveuserIdList.add(pushChatId);
					tmpRecord.setReceiveUserId(userInfo.getUserId());
					msgList.add(tmpRecord);
				});
			// 推送消息给用户
			msgSystemXMapper.insertList(msgList);
		}
	}

	@Override
	public List<MsgSystemX> queryMsgSystemByModeTypeAndModeId(Integer type, Integer id) {
		// TODO Auto-generated method stub
		return msgSystemXMapper.queryMsgSystemByModeTypeAndModeId(type, id);
	}

}
