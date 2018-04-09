package com.moyou.moyouRms.model.notification;

/**
 * 转发说说数据
 * 
 * @author PzC.
 * @time 2017年3月21日 下午5:41:04
 * 
 */
public class RepublishTalkData implements DataModel {
	private Integer userId;// 用户 id
	private String nickname;// 昵称
	private String avatar;// 头像
	private Integer talkId;// 说说 id
	private String content;// 说说内容
	private String pic;// 图片地址
	private Integer relateTalkId;// 转发说说 id
	private Integer relateState;// 转发说说的状态 0正常 1删除
	private Integer relateUserId;// 转发说说的说说用户 id
	private String relateNickname;// 转发说说的说说用户昵称

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

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getRelateTalkId() {
		return relateTalkId;
	}

	public void setRelateTalkId(Integer relateTalkId) {
		this.relateTalkId = relateTalkId;
	}

	public Integer getRelateState() {
		return relateState;
	}

	public void setRelateState(Integer relateState) {
		this.relateState = relateState;
	}

	public Integer getRelateUserId() {
		return relateUserId;
	}

	public void setRelateUserId(Integer relateUserId) {
		this.relateUserId = relateUserId;
	}

	public String getRelateNickname() {
		return relateNickname;
	}

	public void setRelateNickname(String relateNickname) {
		this.relateNickname = relateNickname;
	}

	public RepublishTalkData setFields(RepublishTalkData republishTalkData, UserNotificationQuery query) {
		republishTalkData.setAvatar(query.getAvatar());
		republishTalkData.setContent(query.getBusinessTextContent());
		republishTalkData.setNickname(query.getNickname());
		republishTalkData.setPic(query.getPic());
		republishTalkData.setTalkId(query.getBusinessId());
		republishTalkData.setUserId(query.getUserId());
		return republishTalkData;
	}
}
