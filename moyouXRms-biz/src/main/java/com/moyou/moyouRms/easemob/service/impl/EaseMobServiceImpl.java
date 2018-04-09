package com.moyou.moyouRms.easemob.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.easemob.server.api.ChatGroupAPI;
import com.easemob.server.api.IMUserAPI;
import com.easemob.server.api.SendMessageAPI;
import com.easemob.server.comm.ClientContext;
import com.easemob.server.comm.EasemobRestAPIFactory;
import com.easemob.server.comm.body.ChatGroupBody;
import com.easemob.server.comm.body.ChatGroupBodyV2;
import com.easemob.server.comm.body.CommandMessageBody;
import com.easemob.server.comm.body.IMUserBody;
import com.easemob.server.comm.body.TextMessageBody;
import com.easemob.server.comm.body.UserNamesBody;
import com.easemob.server.comm.wrapper.ResponseWrapper;
import com.moyou.moyouRms.constants.enums.EasemobSingleChatSendTypeEnum;
import com.moyou.moyouRms.easemob.entity.EaseMobUserInfo;
import com.moyou.moyouRms.easemob.entity.EasemobRestAPI;
import com.moyou.moyouRms.easemob.entity.SingleChatMsg;
import com.moyou.moyouRms.easemob.entity.SingleChatMsgExtend;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.Md5Util;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class EaseMobServiceImpl extends EasemobRestAPI implements EaseMobService {
	private static Logger logger = Logger.getLogger(EaseMobServiceImpl.class);

	private static final String ROOT_URI = "/users";

	@Override
	public String getEaseMobToken(int mobileType, String ip) {
		ClientContext context = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES);
		Object ob = context.getAuthToken();
		return StringUtil.getStr(ob);
	}

	@Override
	public boolean addUser(String easemobUserName, String easemobPassword, String nickname) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);

		boolean isAdd = true;
		ResponseWrapper ob;
		try {
			IMUserBody ib = new IMUserBody(easemobUserName, easemobPassword, nickname);
			ob = (ResponseWrapper) user.createNewIMUserSingle(ib);
			Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
			if (CollectionUtils.isEmpty((List<?>) msgMap.get("entities"))) {
				isAdd = false;
			}
		} catch (Exception e) {
			isAdd = false;
			logger.error(e.getMessage());
		}
		return isAdd;
	}

	@Override
	public EaseMobUserInfo getUsernamePassword(String username, String password) {

		String easemobPassword = Md5Util.getMd5(username);
		EaseMobUserInfo em = new EaseMobUserInfo();
		em.setPassword(easemobPassword);
		em.setUsername(username);
		return em;
	}

	@Override
	public Map<String, Object> getUser(String easemobUserName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.getIMUsersByUserName(easemobUserName);
		return JsonUtil.toMap(ob.getResponseBody().toString());
	}

	@Override
	public boolean isExistUser(String easemobUserName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.getIMUsersByUserName(easemobUserName);
		if (404 == ob.getResponseStatus()) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> removeUser(String username) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.deleteIMUserByUserName(username);
		return JsonUtil.toMap(ob.toString());
	}

	@Override
	public String createChatGroup(ChatGroupBody cc) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatGroupAPI chatgroup = (ChatGroupAPI) factory
				.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ResponseWrapper ob = (ResponseWrapper) chatgroup.createChatGroup(cc);
		Map<String, Object> resultMap = JsonUtil.toMap(ob.getResponseBody().toString());
		Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(resultMap.get("data")));
		if (dataMap == null) {
			return "";
		}
		String id = StringUtil.getStr(dataMap.get("groupid"));
		return id;
	}

	@Override
	public boolean addUserToChatGroup(String groupId, String easemobUserName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatGroupAPI chatgroup = (ChatGroupAPI) factory
				.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ResponseWrapper ob = (ResponseWrapper) chatgroup.addSingleUserToChatGroup(groupId,
				easemobUserName);
		Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
		Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(msgMap.get("data")));
		if (dataMap == null) {
			return false;
		}
		if ("false".equals(StringUtil.getStr(dataMap.get("result")))) {
			return false;
		}
		return true;
	}

	@Override
	public boolean bacthUserToChatGroup(String groupId, List<String> usernames) {
		if (usernames != null && usernames.size() > 0) {
			EasemobRestAPIFactory factory = ClientContext.getInstance()
					.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
			ChatGroupAPI chatgroup = (ChatGroupAPI) factory
					.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
			String[] usernameArr = new String[usernames.size()];
			usernames.toArray(usernameArr);
			UserNamesBody unb = new UserNamesBody(usernameArr);
			ResponseWrapper ob = (ResponseWrapper) chatgroup.addBatchUsersToChatGroup(groupId, unb);
			Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
			Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(msgMap.get("data")));
			if (dataMap == null) {
				return false;
			}
			if ("false".equals(StringUtil.getStr(dataMap.get("result")))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean removeUserFromChatGroup(String groupId, String easemobUserName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatGroupAPI chatgroup = (ChatGroupAPI) factory
				.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ResponseWrapper ob = (ResponseWrapper) chatgroup.removeSingleUserFromChatGroup(groupId,
				easemobUserName);
		Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
		Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(msgMap.get("data")));
		if (dataMap == null) {
			return false;
		}
		if ("false".equals(StringUtil.getStr(dataMap.get("result")))) {
			return false;
		}
		return true;
	}

	/**
	 * 群组减人[批量]
	 * 
	 * @param roomId
	 * @param username
	 * @return
	 */
	@Override
	public boolean removeBatchUsersFromChatGroup(String roomId, List<String> usernames) {
		if (usernames != null && usernames.size() > 0) {
			EasemobRestAPIFactory factory = ClientContext.getInstance()
					.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
			ChatGroupAPI chatgroup = (ChatGroupAPI) factory
					.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
			String[] usernameArr = new String[usernames.size()];
			usernames.toArray(usernameArr);
			ResponseWrapper ob = (ResponseWrapper) chatgroup.removeBatchUsersFromChatGroup(roomId,
					usernameArr);
			Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
			Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(msgMap.get("data")));
			if (dataMap == null) {
				return false;
			}
			if ("false".equals(StringUtil.getStr(dataMap.get("result")))) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public Object deactivateIMUser(String userName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.deactivateIMUser(userName);
		return JsonUtil.toMap(ob.toString());
	}

	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	@Override
	public Object activateIMUser(String userName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.activateIMUser(userName);
		return ob;
	}

	@Override
	public Object disconnectIMUser(String userName) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper ob = (ResponseWrapper) user.disconnectIMUser(userName);
		return ob;
	}

	@Override
	public boolean sendMessage(TextMessageBody textMessageBody) {
		try {
			EasemobRestAPIFactory factory = ClientContext.getInstance()
					.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
			SendMessageAPI sm = (SendMessageAPI) factory
					.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
			System.out.println(sm.sendMessage(textMessageBody));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateGroup(String owerId, String groupId, String groupName, String desc,
			Integer maxusers) {
		try {
			EasemobRestAPIFactory factory = ClientContext.getInstance()
					.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

			ChatGroupAPI chatgroup = (ChatGroupAPI) factory
					.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);

			ChatGroupBodyV2 chatGroupBody = new ChatGroupBodyV2(groupName, desc, true,
					(long) maxusers, false, null);
			ResponseWrapper obj = (ResponseWrapper) chatgroup.modifyChatGroup(groupId,
					chatGroupBody);
			if (obj.hasError() || obj.getResponseStatus() != 200) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean transferChatGroupOwner(String groupId, String onewer) {
		// TODO Auto-generated method stub
		try {
			EasemobRestAPIFactory factory = ClientContext.getInstance()
					.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
			ChatGroupAPI chatgroup = (ChatGroupAPI) factory
					.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
			chatgroup.transferChatGroupOwner(groupId, onewer);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public boolean sendSingleChatMsg(String userId, SingleChatMsg singleChatMsg,
			SingleChatMsgExtend singleChatMsgExtend, String... targetUserId) {
		EasemobRestAPIFactory factory = ClientContext.getInstance()
				.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		SendMessageAPI sendMsgApi = (SendMessageAPI) factory
				.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
		Map<String, String> extMap = new HashMap<>();
		if (singleChatMsgExtend == null)
			singleChatMsgExtend = new SingleChatMsgExtend();
		if (singleChatMsgExtend != null) {// 扩展属性字段转换为map
			Map<String, Object> singleChatMsgExtendMap = JsonUtil.toMap(singleChatMsgExtend);
			for (String key : singleChatMsgExtendMap.keySet()) {
				extMap.put(key, StringUtil.getStr(singleChatMsgExtendMap.get(key)));
			}
		}
		switch (singleChatMsg.getType()) {// 消息类型， 详情查看枚举类
		case TXT:
			Map<String, String> dataMap1 = new HashMap<>();
			dataMap1.put("type", singleChatMsg.getType().getValue());
			dataMap1.put("msg", singleChatMsg.getMsg());
			TextMessageBody msgBody = new TextMessageBody(
					EasemobSingleChatSendTypeEnum.SNED_TO_USERS.getValue(), targetUserId, userId,
					extMap, singleChatMsg.getMsg());
			ResponseWrapper ob = (ResponseWrapper) sendMsgApi.sendMessage(msgBody);
			Map<String, Object> msgMap = JsonUtil.toMap(ob.getResponseBody().toString());
			Map<String, Object> dataMap = JsonUtil.toMap(JsonUtil.toJson(msgMap.get("data")));
			if (dataMap == null) {
				return false;
			}
			boolean flag = true;// 标识是否所有用户发送的消息 是否发送成功
			for (String key : dataMap.keySet()) {
				if (!"success".equals(dataMap.get(key))) {
					logger.error("sendSingleChatMsg fail:" + key + ";" + JsonUtil.toJson(userId)
							+ ";" + JsonUtil.toJson(singleChatMsg) + ";"
							+ JsonUtil.toJson(singleChatMsgExtend) + ";"
							+ JsonUtil.toJson(targetUserId));
					flag = false;
				}
			}
			return flag;
		case CMD:// 限制主播透传
			extMap.put("action", singleChatMsg.getAction());
			CommandMessageBody commandMessageBody = new CommandMessageBody(
					EasemobSingleChatSendTypeEnum.SNED_TO_USERS.getValue(), targetUserId, userId,
					extMap, singleChatMsg.getAction());
			ResponseWrapper ob2 = (ResponseWrapper) sendMsgApi.sendMessage(commandMessageBody);
			Map<String, Object> msgMap2 = JsonUtil.toMap(ob2.getResponseBody().toString());
			Map<String, Object> dataMap3 = JsonUtil.toMap(JsonUtil.toJson(msgMap2.get("data")));
			if (dataMap3 == null) {
				logger.error("sendSingleChatMsg fail:" + JsonUtil.toJson(ob2));
				return false;
			}
			boolean flag1 = true;// 标识是否所有用户发送的消息 是否发送成功
			for (String key : dataMap3.keySet()) {
				if (!"success".equals(dataMap3.get(key))) {
					logger.error("sendSingleChatMsg fail:" + key + ";" + JsonUtil.toJson(userId)
							+ ";" + JsonUtil.toJson(singleChatMsg) + ";"
							+ JsonUtil.toJson(singleChatMsgExtend) + ";"
							+ JsonUtil.toJson(targetUserId));
					flag1 = false;
				}
			}
			return flag1;
		default:
			break;
		}
		return false;
	}

}
