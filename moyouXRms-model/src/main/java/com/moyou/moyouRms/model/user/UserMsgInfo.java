package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;

import com.moyou.moyouRms.util.DateTimeUtil;

public class UserMsgInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String avatar;
	private String pushChatId;
	private String nickname;
	private String city;
	private Integer gender;
	private Date birthday;
	private Integer userId;
	private String age;
	private Integer offlineMsgCount;
	public static UserMsgInfo instance(UserMsgInfo userMsgInfo,Integer count) {
		userMsgInfo.setAge(DateTimeUtil.getPersonAgeByBirthDate(userMsgInfo.getBirthday()));
		userMsgInfo.setOfflineMsgCount(count);
		return userMsgInfo;

	};

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOfflineMsgCount() {
		return offlineMsgCount;
	}

	public void setOfflineMsgCount(Integer offlineMsgCount) {
		this.offlineMsgCount = offlineMsgCount;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
