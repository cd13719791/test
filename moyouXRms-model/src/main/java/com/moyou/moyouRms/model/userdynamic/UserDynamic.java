package com.moyou.moyouRms.model.userdynamic;

import java.util.Date;

public class UserDynamic {
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 动态数据id
	 */
	private Integer dataId;
	/**
	 * 动态类型1说说2故事
	 */
	private Short dataType;
	public static final Short TALK = 1;
	public static final Short DIARY = 2;

	private Date createTime;

	public Integer getId() {
		return id;
	}

	/**
	 * 删除用
	 * 
	 * @param dataId
	 * @param dataType
	 */
	public UserDynamic(Integer dataId, Short dataType) {
		super();
		this.dataId = dataId;
		this.dataType = dataType;
	}

	public UserDynamic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDynamic(Integer userId, Integer dataId, Short dataType, Date createTime) {
		super();
		this.userId = userId;
		this.dataId = dataId;
		this.dataType = dataType;
		this.createTime = createTime;
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

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Short getDataType() {
		return dataType;
	}

	public void setDataType(Short dataType) {
		this.dataType = dataType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}