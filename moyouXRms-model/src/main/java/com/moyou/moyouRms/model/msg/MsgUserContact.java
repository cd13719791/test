package com.moyou.moyouRms.model.msg;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.util.PropertiesUtil;

/**
 * 消息联系人好友
 * 
 * @author PzC.
 * @time 2016年12月6日 下午3:03:28
 * 
 */
public class MsgUserContact extends SuperMsg {
	private Integer msgSendCount = 0;

	public Integer getMsgSendCount() {
		return msgSendCount;
	}

	public void setMsgSendCount(Integer msgSendCount) {
		this.msgSendCount = msgSendCount;
	}

	public static MsgUserContact instance(String sendUserId, String nickname, String addContactContent) {
		MsgUserContact msgUserContact = new MsgUserContact();
		msgUserContact.setMsgContent(
				PropertiesUtil.bundle(CONSTANT.CONTACT_KEY, (Object[]) new String[] { nickname, addContactContent }));
		msgUserContact.setMsgSendType(CONSTANT.CONTACT_KEY);
		msgUserContact.setSendUserId(sendUserId);
		return msgUserContact;
	}
}