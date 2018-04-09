package com.moyou.moyouRms.model.talk;

import com.moyou.moyouRms.model.BaseModel;

public class ComentTwo extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String commentContent;
	private String commentAvatar;
	private String commentNickName;
	private Integer talkCommentId;
	private Integer talkId;//说说ID
	public Integer getTalkId() {
		return talkId;
	}
	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentAvatar() {
		return commentAvatar;
	}
	public void setCommentAvatar(String commentAvatar) {
		this.commentAvatar = commentAvatar;
	}
	public String getCommentNickName() {
		return commentNickName;
	}
	public void setCommentNickName(String commentNickName) {
		this.commentNickName = commentNickName;
	}
	public Integer getTalkCommentId() {
		return talkCommentId;
	}
	public void setTalkCommentId(Integer talkCommentId) {
		this.talkCommentId = talkCommentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
