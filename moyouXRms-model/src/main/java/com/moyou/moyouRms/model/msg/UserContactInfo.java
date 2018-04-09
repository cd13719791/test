package com.moyou.moyouRms.model.msg;

import java.util.List;

/**
 * UserChatInfo
 * 
 * @author PzC.
 * @time 2016年12月7日 下午6:34:18
 * 
 */
public class UserContactInfo {
	private String userId;
	private List<String> queryUserIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getQueryUserIds() {
		return queryUserIds;
	}

	public void setQueryUserIds(List<String> queryUserIds) {
		this.queryUserIds = queryUserIds;
	}

}
