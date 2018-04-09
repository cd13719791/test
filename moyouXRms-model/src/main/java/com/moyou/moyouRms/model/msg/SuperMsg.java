package com.moyou.moyouRms.model.msg;

import java.util.Date;

import com.moyou.moyouRms.util.UUIDUtil;

public class SuperMsg {
	private String id = UUIDUtil.getUUID();
	private String sendUserId;
	private String receiveUserId;
	private String jpushMsgId;
	private String msgContent;
	private String msgSendType;
	private Integer state = 0;
	private Date updateTime = new Date();
	private Date createTime = this.updateTime;
	private String shortMsgContent;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getJpushMsgId() {
		return jpushMsgId;
	}

	public void setJpushMsgId(String jpushMsgId) {
		this.jpushMsgId = jpushMsgId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgSendType() {
		return msgSendType;
	}

	public void setMsgSendType(String msgSendType) {
		this.msgSendType = msgSendType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getShortMsgContent() {
		return shortMsgContent;
	}

	public void setShortMsgContent(String shortMsgContent) {
		this.shortMsgContent = shortMsgContent;
	}

}
