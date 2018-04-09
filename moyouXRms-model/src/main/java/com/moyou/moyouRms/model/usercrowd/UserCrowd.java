package com.moyou.moyouRms.model.usercrowd;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moyou.moyouRms.model.BaseModel;

public class UserCrowd extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8168202035936071807L;
	public static final int FRONT_CREATE_TYPE = 1;// 前台创建
	public static final int ADMIN_CREATE_TYPE = 2;// 后台创建

	private Integer crowdCreateType;// // 1前台创建，2后台创建默认
	private Integer recommedStatus;// 推荐状态
	private Integer recommendSort;// 推荐排序字段
	private Integer recommedCount;// 推荐数量
	private Integer autoApplyAgree;// '1加群自动通过2加群需要管理员同意'

	public Integer getAutoApplyAgree() {
		return autoApplyAgree;
	}

	public void setAutoApplyAgree(Integer autoApplyAgree) {
		this.autoApplyAgree = autoApplyAgree;
	}

	public Integer getRecommedCount() {
		return recommedCount;
	}

	public void setRecommedCount(Integer recommedCount) {
		this.recommedCount = recommedCount;
	}

	public Integer getRecommendSort() {
		return recommendSort;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}

	public Integer getRecommedStatus() {
		return recommedStatus;
	}

	public void setRecommedStatus(Integer recommedStatus) {
		this.recommedStatus = recommedStatus;
	}

	public Integer getCrowdCreateType() {
		return crowdCreateType;
	}

	public void setCrowdCreateType(Integer crowdCreateType) {
		this.crowdCreateType = crowdCreateType;
	}

	private Integer id;
	private Integer userId;
	private Integer maxCount;// 成员上线

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	private Integer crowdId;// 群号

	private String crowdName;// 群名

	private String crowdBackground;// 群背景

	private Integer memberCount;

	private String crowdCity;

	private Double crowdLatitude;

	private Double crowdLongitude;

	private Date createTime;

	private Date updateTime;

	private int crowdStatus;

	private String easemodCroweId;// 环信群ID

	private int crowdLevel;

	private String categoryId;
	private Integer crowdMemberId;// 群用户总数真用户

	public Integer getCrowdMemberId() {
		return crowdMemberId;
	}

	public void setCrowdMemberId(Integer crowdMemberId) {
		this.crowdMemberId = crowdMemberId;
	}

	private String crowdDesc;// 群描述
	private Integer countCrowd;
	private Integer newCountCrowd;
	private String crowdUserNickName;// 群主昵称
	private String avatar;// 群主头像

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCrowdUserNickName() {
		return crowdUserNickName;
	}

	public void setCrowdUserNickName(String crowdUserNickName) {
		this.crowdUserNickName = crowdUserNickName;
	}

	public Integer getCountCrowd() {
		return countCrowd;
	}

	public void setCountCrowd(Integer countCrowd) {
		this.countCrowd = countCrowd;
	}

	public Integer getNewCountCrowd() {
		return newCountCrowd;
	}

	public void setNewCountCrowd(Integer newCountCrowd) {
		this.newCountCrowd = newCountCrowd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(Integer crowdId) {
		this.crowdId = crowdId;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName == null ? null : crowdName.trim();
	}

	public String getCrowdBackground() {
		return crowdBackground;
	}

	public void setCrowdBackground(String crowdBackground) {
		this.crowdBackground = crowdBackground == null ? null : crowdBackground.trim();
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public String getCrowdCity() {
		return crowdCity;
	}

	public void setCrowdCity(String crowdCity) {
		this.crowdCity = crowdCity == null ? null : crowdCity.trim();
	}

	public Double getCrowdLatitude() {
		return crowdLatitude;
	}

	public void setCrowdLatitude(Double crowdLatitude) {
		this.crowdLatitude = crowdLatitude;
	}

	public Double getCrowdLongitude() {
		return crowdLongitude;
	}

	public void setCrowdLongitude(Double crowdLongitude) {
		this.crowdLongitude = crowdLongitude;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCrowdStatus() {
		return crowdStatus;
	}

	public void setCrowdStatus(int crowdStatus) {
		this.crowdStatus = crowdStatus;
	}

	public String getEasemodCroweId() {
		return easemodCroweId;
	}

	public void setEasemodCroweId(String easemodCroweId) {
		this.easemodCroweId = easemodCroweId == null ? null : easemodCroweId.trim();
	}

	public int getCrowdLevel() {
		return crowdLevel;
	}

	public void setCrowdLevel(int crowdLevel) {
		this.crowdLevel = crowdLevel;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}

	public String getCrowdDesc() {
		return crowdDesc;
	}

	public void setCrowdDesc(String crowdDesc) {
		this.crowdDesc = crowdDesc == null ? null : crowdDesc.trim();
	}
}
