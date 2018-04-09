package com.moyou.moyouRms.model.user;

import java.io.Serializable;

public class FakeUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473632497875884786L;
	private Integer userId;
	private String pushChatId;
	private String nickname;
	private String avatar;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getUserId() {
		return userId;
	}

	public FakeUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPushChatId() {
		return pushChatId;
	}

	public void setPushChatId(String pushChatId) {
		this.pushChatId = pushChatId;
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
}
