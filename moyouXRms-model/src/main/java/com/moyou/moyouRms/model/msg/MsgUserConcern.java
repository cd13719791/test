package com.moyou.moyouRms.model.msg;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.util.PropertiesUtil;

public class MsgUserConcern extends SuperMsg {
	private Integer msgSendCount = 0;

	public Integer getMsgSendCount() {
		return msgSendCount;
	}

	public void setMsgSendCount(Integer msgSendCount) {
		this.msgSendCount = msgSendCount;
	}

	/**
	 * @param nickname
	 *            昵称
	 * @param sendUserId
	 *            推送者用户 id
	 * @return
	 */
	public static MsgUserConcern instance(String nickname, String sendUserId) {
		MsgUserConcern msgUserConcern = new MsgUserConcern();
		String content = PropertiesUtil.bundle(CONSTANT.CONCERN_KEY, (Object[]) new String[] { nickname });
		msgUserConcern.setMsgContent(content);
		msgUserConcern.setShortMsgContent(content);
		msgUserConcern.setMsgSendType(CONSTANT.CONCERN_KEY);
		msgUserConcern.setSendUserId(sendUserId);
		return msgUserConcern;
	}
}