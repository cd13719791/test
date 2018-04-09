package com.moyou.moyouRms.model.shareDetails;

/**
 * @author PzC.
 * @time 2017年1月20日 上午11:25:38
 * 
 */
public class FollowerOrInterest {
	private Integer userId;
	private String nickname;
	private String avatar;
	private String sig;
	private Integer state;// 0未关注  1关注

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
