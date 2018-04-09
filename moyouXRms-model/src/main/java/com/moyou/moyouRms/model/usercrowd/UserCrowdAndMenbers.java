package com.moyou.moyouRms.model.usercrowd;

import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class UserCrowdAndMenbers extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 群
	private UserCrowd userCrowd;

	public UserCrowd getUserCrowd() {
		return userCrowd;
	}

	public void setUserCrowd(UserCrowd userCrowd) {
		this.userCrowd = userCrowd;
	}

	// 群成员
	private List<UserCrowdMembers> userCrowdMembers;

	public List<UserCrowdMembers> getUserCrowdMembers() {
		return userCrowdMembers;
	}

	public void setUserCrowdMembers(List<UserCrowdMembers> userCrowdMembers) {
		this.userCrowdMembers = userCrowdMembers;
	}
}
