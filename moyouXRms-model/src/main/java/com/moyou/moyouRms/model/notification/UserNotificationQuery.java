package com.moyou.moyouRms.model.notification;

public class UserNotificationQuery {
	private Integer userId;
	private String nickname;
	private String avatar;
	private Integer businessId;
	private String commentTextContent;
	private String businessTextContent;
	private String pic;
	private Integer commentRelateId;
	private Integer businessType;
	private Integer actionType;

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getCommentTextContent() {
		return commentTextContent;
	}

	public void setCommentTextContent(String commentTextContent) {
		this.commentTextContent = commentTextContent;
	}

	public String getBusinessTextContent() {
		return businessTextContent;
	}

	public void setBusinessTextContent(String businessTextContent) {
		this.businessTextContent = businessTextContent;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getCommentRelateId() {
		return commentRelateId;
	}

	public void setCommentRelateId(Integer commentRelateId) {
		this.commentRelateId = commentRelateId;
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

}
