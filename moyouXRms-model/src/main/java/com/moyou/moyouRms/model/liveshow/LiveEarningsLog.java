package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;

public class LiveEarningsLog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键自增
	private Integer payUserId;// 支付人id支付来源
	private Integer receiveUserId;// 接收人id 收入、支出都存储这个
	private Integer earnings;// 收支
	private Integer earningsId;// 收益数据id以后关联礼物表
	private Integer modeId;// 模块id关联房间id
	private Integer modeType;// 0直播结束时消耗金币1直播中送金币
	private Integer watchLiveSeconds;// 观看直播时长单位秒
	private String clientIp;// 客户端ip
	private java.util.Date createTime;// 创建时间
	private String extnd;// 扩展字段，存json数据
	private String date;
	private String time;
	private String onlineTime;

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LiveEarningsLog() {
		super();
	}

	public LiveEarningsLog(Integer id, Integer payUserId, Integer receiveUserId, Integer earnings,
			Integer earningsId, Integer modeId, Integer modeType, Integer watchLiveSeconds,
			String clientIp, java.util.Date createTime, String extnd) {
		super();
		this.id = id;
		this.payUserId = payUserId;
		this.receiveUserId = receiveUserId;
		this.earnings = earnings;
		this.earningsId = earningsId;
		this.modeId = modeId;
		this.modeType = modeType;
		this.watchLiveSeconds = watchLiveSeconds;
		this.clientIp = clientIp;
		this.createTime = createTime;
		this.extnd = extnd;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayUserId() {
		return this.payUserId;
	}

	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}

	public Integer getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Integer getEarnings() {
		return this.earnings;
	}

	public void setEarnings(Integer earnings) {
		this.earnings = earnings;
	}

	public Integer getEarningsId() {
		return this.earningsId;
	}

	public void setEarningsId(Integer earningsId) {
		this.earningsId = earningsId;
	}

	public Integer getModeId() {
		return this.modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	public Integer getModeType() {
		return this.modeType;
	}

	public void setModeType(Integer modeType) {
		this.modeType = modeType;
	}

	public Integer getWatchLiveSeconds() {
		return this.watchLiveSeconds;
	}

	public void setWatchLiveSeconds(Integer watchLiveSeconds) {
		this.watchLiveSeconds = watchLiveSeconds;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getExtnd() {
		return this.extnd;
	}

	public void setExtnd(String extnd) {
		this.extnd = extnd;
	}

}
