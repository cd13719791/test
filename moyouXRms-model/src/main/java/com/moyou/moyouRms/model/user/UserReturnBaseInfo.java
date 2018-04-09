package com.moyou.moyouRms.model.user;

import java.io.Serializable;

public class UserReturnBaseInfo implements Serializable {
	private static final long serialVersionUID = -1494115934342234105L;
	// UserBaseInfo
	private UserCacheInfo userCacheInfo;
	// 首页动态统计数
	private Integer dynamicCount;
	// 用户关注统计
	private Integer userConcernCount;
	// 用户粉丝统计
	private Integer userFansCount;
	// 登录令牌
	private String token;

	public UserCacheInfo getUserCacheInfo() {
		return userCacheInfo;
	}

	public void setUserCacheInfo(UserCacheInfo userCacheInfo) {
		this.userCacheInfo = userCacheInfo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getDynamicCount() {
		return dynamicCount;
	}

	public void setDynamicCount(Integer dynamicCount) {
		this.dynamicCount = dynamicCount;
	}

	public Integer getUserConcernCount() {
		return userConcernCount;
	}

	public void setUserConcernCount(Integer userConcernCount) {
		this.userConcernCount = userConcernCount;
	}

	public Integer getUserFansCount() {
		return userFansCount;
	}

	public void setUserFansCount(Integer userFansCount) {
		this.userFansCount = userFansCount;
	}
	
	/**
	 * UserReturnBaseInfo 对象初始化
	 * 
	 * @param userCacheInfo
	 * @param token
	 * @param userCount
	 * @return
	 */
	public static UserReturnBaseInfo instanceOfUserReturnBaseInfo(UserCacheInfo userCacheInfo, String token,
			UserCount userCount) {
		UserReturnBaseInfo returnBaseInfo = new UserReturnBaseInfo();
		returnBaseInfo.setUserCacheInfo(userCacheInfo);
		returnBaseInfo.setToken(token);
//
//		returnBaseInfo.setDynamicCount(userCount.getDynamicCount());
//		returnBaseInfo.setUserConcernCount(userCount.getUserConcernCount());
//		returnBaseInfo.setUserFansCount(userCount.getUserFansCount());
		return returnBaseInfo;
	}
}
