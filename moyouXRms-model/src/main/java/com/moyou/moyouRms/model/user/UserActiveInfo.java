package com.moyou.moyouRms.model.user;

import java.util.Date;

public class UserActiveInfo {
	private Integer userId;// 用户 id
	private Double latitude;// 纬度
	private Double longitude;// 经度
	private Integer onlineState = 1;// 0离线 1在线
	private Integer mapFunction = 1;// 1开启 0关闭
	private Date updateTime = new Date();
	private Date createTime = new Date();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Integer getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(Integer onlineState) {
		this.onlineState = onlineState;
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

	/**
	 * @return the mapFunction
	 */
	public Integer getMapFunction() {
		return mapFunction;
	}

	/**
	 * @param mapFunction the mapFunction to set
	 */
	public void setMapFunction(Integer mapFunction) {
		this.mapFunction = mapFunction;
	}
	
}