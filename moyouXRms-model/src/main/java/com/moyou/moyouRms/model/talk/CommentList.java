package com.moyou.moyouRms.model.talk;


public class CommentList {
	
	private Integer talkCommentId;//评论的主键id
	private String content;//评论的内容
	private Integer userId;
	private String nickname;
	private String avatar;
	private Integer parentUserId;//@的那个人的id
	private String parentNickname;//@的那个人的昵称
	public Integer getTalkCommentId() {
		return talkCommentId;
	}
	public void setTalkCommentId(Integer talkCommentId) {
		this.talkCommentId = talkCommentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
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
	public Integer getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}
	public String getParentNickname() {
		return parentNickname;
	}
	public void setParentNickname(String parentNickname) {
		this.parentNickname = parentNickname;
	}
	
}
