package com.moyou.moyouRms.model.everyactivity;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class EveryActivity extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer everyDayMoneyTotal;

	private Integer everyDayLimit;

	private String activityDesc;

	private Integer everyMoneyMax;

	private Date updateTime;

	private Date createTime;

	private Short dataStatus;

	private Date everyDay;
	private Integer doingCount;// 执行次数

	public Integer getDoingCount() {
		return doingCount;
	}

	public void setDoingCount(Integer doingCount) {
		this.doingCount = doingCount;
	}

	/**
	 * '0未开始1进行中2执行完成',
	 */
	private int ingState;
	public static final Integer DO_OK = 2;
	public static final Integer DO_ING = 1;

	public int getIngState() {
		return ingState;
	}

	public void setIngState(int ingState) {
		this.ingState = ingState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEveryDayMoneyTotal() {
		return everyDayMoneyTotal;
	}

	public void setEveryDayMoneyTotal(Integer everyDayMoneyTotal) {
		this.everyDayMoneyTotal = everyDayMoneyTotal;
	}

	public Integer getEveryDayLimit() {
		return everyDayLimit;
	}

	public void setEveryDayLimit(Integer everyDayLimit) {
		this.everyDayLimit = everyDayLimit;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc == null ? null : activityDesc.trim();
	}

	public Integer getEveryMoneyMax() {
		return everyMoneyMax;
	}

	public void setEveryMoneyMax(Integer everyMoneyMax) {
		this.everyMoneyMax = everyMoneyMax;
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

	public Short getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Short dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Date getEveryDay() {
		return everyDay;
	}

	public void setEveryDay(Date everyDay) {
		this.everyDay = everyDay;
	}
}