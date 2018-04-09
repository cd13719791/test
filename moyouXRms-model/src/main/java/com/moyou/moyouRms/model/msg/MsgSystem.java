package com.moyou.moyouRms.model.msg;

import com.moyou.moyouRms.util.PropertiesUtil;

public class MsgSystem extends SuperMsg {
	private Integer type;// 消息类型：1.系统消息 2.陌友客服推送消息
	private Integer ranges;// 发送范围：1.所有用户 2.单个用户
	private String title;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRanges() {
		return ranges;
	}

	public void setRanges(Integer ranges) {
		this.ranges = ranges;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}
	
	public static MsgSystem instance(long moyouId, String receiveUserId) {
		MsgSystem msgSystem = new MsgSystem();
		msgSystem.setMsgContent(PropertiesUtil.bundle("reg.push", (Object[])new String[]{String.valueOf(moyouId)}));
		msgSystem.setRanges(1);
		msgSystem.setReceiveUserId(receiveUserId);
		msgSystem.setSendUserId("xitong");
		msgSystem.setShortMsgContent("欢迎来到陌友");
		msgSystem.setState(1);
		msgSystem.setTitle("【欢迎来到陌友】");
		msgSystem.setType(1);
		msgSystem.setMsgSendType("user.reg");
		return msgSystem;
	}
}