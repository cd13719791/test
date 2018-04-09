package com.moyou.moyouRms.model.everyActivityRule;

import java.util.Date;

public class EveryActivityRule {
	private Integer id;
	// 模块类型，1说说2文章3秘密4评论5系统分享6全局分享
	private Short modeType;
	public static final short TALK = 1;
	public static final short DIARY = 2;
	public static final short SECRET = 3;
	public static final short COMMENT = 4;
	public static final short SYSTEM = 5;
	public static final short BIZ = 6;
	private Short rewardType;
	public static final short NORMAL = 1;
	public static final short DELETE = 0;
	private Integer rewardNum;

	private String ruleDesc;

	private Integer everyDayUserLimit;

	private Date updateTime;

	private Date createTime;

	private Short dataStatus;

	private Integer activityId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getModeType() {
		return modeType;
	}

	public void setModeType(Short modeType) {
		this.modeType = modeType;
	}

	public Short getRewardType() {
		return rewardType;
	}

	public void setRewardType(Short rewardType) {
		this.rewardType = rewardType;
	}

	public Integer getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
	}

	public Integer getEveryDayUserLimit() {
		return everyDayUserLimit;
	}

	public void setEveryDayUserLimit(Integer everyDayUserLimit) {
		this.everyDayUserLimit = everyDayUserLimit;
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

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
}