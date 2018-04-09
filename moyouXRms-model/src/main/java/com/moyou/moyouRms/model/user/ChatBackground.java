package com.moyou.moyouRms.model.user;

public class ChatBackground {
	private Integer userIdInitiative;// 主动方
	private Integer userIdPassive;// 被动方
	private String backgroundUrl;// 背景图片
	private int urlType;// 图片类型 : 0.默认图片 1.上传图片
	private Double latitude;// 纬度
	private Double longitude;// 经度
	
	public Integer getUserIdInitiative() {
		return userIdInitiative;
	}

	public void setUserIdInitiative(Integer userIdInitiative) {
		this.userIdInitiative = userIdInitiative;
	}

	public Integer getUserIdPassive() {
		return userIdPassive;
	}

	public void setUserIdPassive(Integer userIdPassive) {
		this.userIdPassive = userIdPassive;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
