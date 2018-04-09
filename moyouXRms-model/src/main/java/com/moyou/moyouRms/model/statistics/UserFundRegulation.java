package com.moyou.moyouRms.model.statistics;

import java.util.Date;

public class UserFundRegulation {
	private Integer id;
	private Integer modeType;// 消费类型
	private Integer modeId;// user_fund_log日志id
	private Integer number;// 消费数量 正负数
	private java.util.Date createTime;

	public UserFundRegulation() {
		super();
	}

	public UserFundRegulation(Integer id, Integer modeType, Integer modeId, Integer number,
			java.util.Date createTime) {
		super();
		this.id = id;
		this.modeType = modeType;
		this.modeId = modeId;
		this.number = number;
		this.createTime = createTime;
	}

	public UserFundRegulation(Integer number, Date createTime) {
		super();
		this.number = number;
		this.createTime = createTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModeType() {
		return this.modeType;
	}

	public void setModeType(Integer modeType) {
		this.modeType = modeType;
	}

	public Integer getModeId() {
		return this.modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

}
