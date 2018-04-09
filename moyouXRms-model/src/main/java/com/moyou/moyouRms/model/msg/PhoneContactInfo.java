package com.moyou.moyouRms.model.msg;

import java.util.List;

/**
 * PhoneContactInfo
 * 
 * @author PzC.
 * @time 2016年12月8日 下午4:05:46
 * 
 */
public class PhoneContactInfo {
	private String userId;
	private List<String> phoneList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

}
