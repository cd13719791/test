package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class UserCount extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3986042589291012771L;

	private Integer userId;

	private Integer userTalkCount;// 说说数量

	private Integer userStoryFolderCount;

	private Integer userInterestCount;

	private Integer userFollowersCount;

	private Integer userVisitCount;

	private Integer userSerialCheckInCount;
	private Integer userFans;

	public Integer getUserFans() {
		return userFans;
	}

	public void setUserFans(Integer userFans) {
		this.userFans = userFans;
	}

	public UserCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCount(Integer userId, Integer userTalkCount, Integer userStoryFolderCount,
			Integer userInterestCount, Integer userFollowersCount, Integer userVisitCount,
			Integer userSerialCheckInCount, Integer userFriendsCount, Integer userSecretCount) {
		super();
		this.userId = userId;
		this.userTalkCount = userTalkCount;
		this.userStoryFolderCount = userStoryFolderCount;
		this.userInterestCount = userInterestCount;
		this.userFollowersCount = userFollowersCount;
		this.userVisitCount = userVisitCount;
		this.userSerialCheckInCount = userSerialCheckInCount;
		this.userFriendsCount = userFriendsCount;
		this.userSecretCount = userSecretCount;
	}

	public UserCount(Integer userId, Integer userInterestCount, Integer userFollowersCount) {
		super();
		this.userId = userId;
		this.userInterestCount = userInterestCount;
		this.userFollowersCount = userFollowersCount;
	}

	public UserCount(Integer userId2) {
		super();
		this.userId = userId2;
	}

	private Integer userFriendsCount;
	private Integer userSecretCount;

	public Integer getUserSecretCount() {
		return userSecretCount;
	}

	public void setUserSecretCount(Integer userSecretCount) {
		this.userSecretCount = userSecretCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserTalkCount() {
		return userTalkCount;
	}

	public void setUserTalkCount(Integer userTalkCount) {
		this.userTalkCount = userTalkCount;
	}

	public Integer getUserStoryFolderCount() {
		return userStoryFolderCount;
	}

	public void setUserStoryFolderCount(Integer userStoryFolderCount) {
		this.userStoryFolderCount = userStoryFolderCount;
	}

	public Integer getUserInterestCount() {
		return userInterestCount;
	}

	public void setUserInterestCount(Integer userInterestCount) {
		this.userInterestCount = userInterestCount;
	}

	public Integer getUserFollowersCount() {
		return userFollowersCount;
	}

	public void setUserFollowersCount(Integer userFollowersCount) {
		this.userFollowersCount = userFollowersCount;
	}

	public Integer getUserVisitCount() {
		return userVisitCount;
	}

	public void setUserVisitCount(Integer userVisitCount) {
		this.userVisitCount = userVisitCount;
	}

	public Integer getUserSerialCheckInCount() {
		return userSerialCheckInCount;
	}

	public void setUserSerialCheckInCount(Integer userSerialCheckInCount) {
		this.userSerialCheckInCount = userSerialCheckInCount;
	}

	public Integer getUserFriendsCount() {
		return userFriendsCount;
	}

	public void setUserFriendsCount(Integer userFriendsCount) {
		this.userFriendsCount = userFriendsCount;
	}

	/**
	 * UserCount 对象
	 * 
	 * @param userId
	 * @param now
	 * @return
	 */
	public static UserCount instanceOfUserCount(String userId, Date now) {
		UserCount userCount = new UserCount();
		userCount.setUserId(Integer.valueOf(userId));
		// userCount.setUpdateTime(now);
		// userCount.setCreateTime(now);
		return userCount;
	}
}
