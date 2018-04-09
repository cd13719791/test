/**  
 * @Title:  单聊扩展字段实体
 * @Package com.moyou.moyouRms.easemob.entity   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月3日 下午2:10:41   
 * @email liuxinyi@mousns.com
 * @version V1.0 
 */
package com.moyou.moyouRms.easemob.entity;

public class SingleChatMsgExtend {
	/**
	 * 发送用户的昵称
	 */
	private String nickname;
	private boolean reward;
	private String bizRoomId;

	public String getBizRoomId() {
		return bizRoomId;
	}

	public void setBizRoomId(String bizRoomId) {
		this.bizRoomId = bizRoomId;
	}

	public boolean isReward() {
		return reward;
	}

	public void setReward(boolean reward) {
		this.reward = reward;
	}

	/**
	 * 发送用户的头像url
	 */
	private String avatarUrl;
	/**
	 * 发送陌友业务系统用户的id
	 */
	private int userId;

	/**
	 * 接收消息陌友业务系统用户id
	 */
	private int otherUserId;
	/**
	 * 接收消息用户的头像url
	 */
	private String otheravatarUrl;
	/**
	 * 接收消息用户的昵称
	 */
	private String othernickname;

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	public SingleChatMsgExtend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingleChatMsgExtend(String nickname, String avatarUrl, int userId, int otherUserId,
			String otheravatarUrl, String othernickname, boolean reward) {
		super();
		this.nickname = nickname;
		this.avatarUrl = avatarUrl;
		this.userId = userId;
		this.otherUserId = otherUserId;
		this.otheravatarUrl = otheravatarUrl;
		this.othernickname = othernickname;
		this.reward = reward;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 *            the avatarUrl to set
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the otherUserId
	 */
	public int getOtherUserId() {
		return otherUserId;
	}

	/**
	 * @param otherUserId
	 *            the otherUserId to set
	 */
	public void setOtherUserId(int otherUserId) {
		this.otherUserId = otherUserId;
	}

	/**
	 * @return the otheravatarUrl
	 */
	public String getOtheravatarUrl() {
		return otheravatarUrl;
	}

	/**
	 * @param otheravatarUrl
	 *            the otheravatarUrl to set
	 */
	public void setOtheravatarUrl(String otheravatarUrl) {
		this.otheravatarUrl = otheravatarUrl;
	}

	/**
	 * @return the othernickname
	 */
	public String getOthernickname() {
		return othernickname;
	}

	/**
	 * @param othernickname
	 *            the othernickname to set
	 */
	public void setOthernickname(String othernickname) {
		this.othernickname = othernickname;
	}

}
