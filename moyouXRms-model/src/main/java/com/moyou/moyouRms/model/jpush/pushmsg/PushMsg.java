package com.moyou.moyouRms.model.jpush.pushmsg;

import java.io.Serializable;
import java.util.Date;

public class PushMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6137301007843120108L;
	private int id;
	private String msgTitle;
	private String msgContent;
	private String msgImage;
	private String pushTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgImage() {
		return msgImage;
	}

	public void setMsgImage(String msgImage) {
		this.msgImage = msgImage;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int state;
	private Date createTime;
	private Date updateTime;
}
