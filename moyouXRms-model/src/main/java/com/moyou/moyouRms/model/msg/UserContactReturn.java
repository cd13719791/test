package com.moyou.moyouRms.model.msg;

/**
 * UserContactReturn
 * 
 * @author PzC.
 * @time 2016年12月7日 上午11:30:31
 * 
 */
public class UserContactReturn {
	private String relationId;
	private String userId;
	private String nickname;
	private String avatar;
	private String constellation;
	private String sig;
	private String age;
	private String fromNow;
	private String distance;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFromNow() {
		return fromNow;
	}

	public void setFromNow(String fromNow) {
		this.fromNow = fromNow;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
}
