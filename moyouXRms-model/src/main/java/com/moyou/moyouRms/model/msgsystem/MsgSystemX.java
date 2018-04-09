package com.moyou.moyouRms.model.msgsystem;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class MsgSystemX extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6217186133495678181L;

	public static final Short CUSTOMMSG = 6;
	/**
	 * modetype=5 反馈消息
	 */
	public static final Short FEED_BACK = 5;
	private Integer id;
	private String h5Url;
	private Integer sendUserId;

	private Integer receiveUserId;
	private Short warnType;
	public final static Short DEFAULT = 0;// 默认
	public final static Short CUSTOM = 1;// 自定义

	public Short getWarnType() {
		return warnType;
	}

	public void setWarnType(Short warnType) {
		this.warnType = warnType;
	}

	private String jpushMsgId;

	private String msgSendType;

	private String shortMsgContent;

	private String msgContent;

	private Integer msgSendCount = 0;

	private Short state;

	private Short modeType;

	private Integer modeId;

	private Date updateTime;

	private Date createTime;

	private String msgTitle;
	private int userId;

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getJpushMsgId() {
		return jpushMsgId;
	}

	public void setJpushMsgId(String jpushMsgId) {
		this.jpushMsgId = jpushMsgId == null ? null : jpushMsgId.trim();
	}

	public String getMsgSendType() {
		return msgSendType;
	}

	public void setMsgSendType(String msgSendType) {
		this.msgSendType = msgSendType == null ? null : msgSendType.trim();
	}

	public String getShortMsgContent() {
		return shortMsgContent;
	}

	public void setShortMsgContent(String shortMsgContent) {
		this.shortMsgContent = shortMsgContent == null ? null : shortMsgContent
				.trim();
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent == null ? null : msgContent.trim();
	}

	public Integer getMsgSendCount() {
		return msgSendCount;
	}

	public void setMsgSendCount(Integer msgSendCount) {
		this.msgSendCount = msgSendCount;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getModeType() {
		return modeType;
	}

	public void setModeType(Short modeType) {
		this.modeType = modeType;
	}

	public Integer getModeId() {
		return modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
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

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle == null ? null : msgTitle.trim();
	}
}
