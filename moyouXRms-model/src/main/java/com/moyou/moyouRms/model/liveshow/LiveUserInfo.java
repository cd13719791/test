package com.moyou.moyouRms.model.liveshow;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class LiveUserInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;// 用户id
	private Integer userState;// 状态 0不可用1可用
	private Integer liveGrade;// 直播等级
	private String operationUser;// 操作人
	private String operationRemark;// 操作备注
	private Date operationTime;// 操作备注
	private java.util.Date updateTime;// 修改时间
	private java.util.Date createTime;// 创建时间
	private Integer userGold;// 直播收益金币
	private String weixin;// 微信号
	private Date recommendTime;
	private Integer recommendState;
	private Integer recommendSort;
	public static final Integer RECOMMEND_STATE_YES = 1;
	public static final Integer RECOMMEND_STATE_NO = 0;
	public static final Integer USER_STATE_YES = 1;
	public static final Integer USER_STATE_NO = 0;

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Integer getRecommendState() {
		return recommendState;
	}

	public void setRecommendState(Integer recommendState) {
		this.recommendState = recommendState;
	}

	public Integer getRecommendSort() {
		return recommendSort;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public LiveUserInfo() {
		super();
	}

	public LiveUserInfo(Integer userId, Integer userState, Integer liveGrade, String operationUser,
			String operationRemark, Date updateTime, Date createTime, Integer userGold,
			String weixin) {
		super();
		this.userId = userId;
		this.userState = userState;
		this.liveGrade = liveGrade;
		this.operationUser = operationUser;
		this.operationRemark = operationRemark;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.userGold = userGold;
		this.weixin = weixin;
	}

	public LiveUserInfo(Integer userId) {
		super();
		this.userId = userId;
	}

	public LiveUserInfo(Integer state, Integer sort, Integer id) {
		// TODO Auto-generated constructor stub
		super();
		this.userId = id;
		this.recommendTime = new Date();
		this.recommendState = state;
		this.recommendSort = sort;

	}

	public LiveUserInfo(Integer userId2, String operationRemark2, Date setOperationTime,
			String operationUser2) {
		super();
		this.userId = userId2;
		this.operationRemark = operationRemark2;
		this.operationTime = setOperationTime;
		this.operationUser = operationUser2;

	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Integer getLiveGrade() {
		return this.liveGrade;
	}

	public void setLiveGrade(Integer liveGrade) {
		this.liveGrade = liveGrade;
	}

	public String getOperationUser() {
		return this.operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public String getOperationRemark() {
		return this.operationRemark;
	}

	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserGold() {
		return this.userGold;
	}

	public void setUserGold(Integer userGold) {
		this.userGold = userGold;
	}

}
