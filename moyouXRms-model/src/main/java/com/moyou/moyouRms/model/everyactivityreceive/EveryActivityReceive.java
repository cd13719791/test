package com.moyou.moyouRms.model.everyactivityreceive;

import java.util.Date;

public class EveryActivityReceive {
	private Integer id;

	private Integer activityId;

	private Integer receiveUserid;

	private Integer receiveFund;

	private Date createTime;

	private Date updateTime;

	private Integer state;// '状态0未领取1领取'
	public static final Integer DO_NOT_GET = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getReceiveUserid() {
		return receiveUserid;
	}

	public void setReceiveUserid(Integer receiveUserid) {
		this.receiveUserid = receiveUserid;
	}

	public Integer getReceiveFund() {
		return receiveFund;
	}

	public void setReceiveFund(Integer receiveFund) {
		this.receiveFund = receiveFund;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}