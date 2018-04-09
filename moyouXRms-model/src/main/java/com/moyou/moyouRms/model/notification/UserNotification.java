package com.moyou.moyouRms.model.notification;

import java.util.Date;

import com.moyou.moyouRms.constants.enums.PushAlertEnum;

public class UserNotification {
	private Integer receiveUserId;// 接受通知的用户 id
	private Integer sendUserId;// 发送通知的用户 id
	private Integer businessId;// 说说或者专辑故事 id
	private Integer businessType;// 业务分类 1说说相关 2专辑故事相关3审核反馈
	private Integer actionType;// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发 5回复评论6审核失败回馈
	private String commentTextContent;// 评论文字内容
	private String businessTextContent;// 业务相关文字内容 （说说或专辑故事的文本内容、父评论文本内容）
	private String pic;// 图片地址
	private Integer commentRelateId;// 评论文字内容对应的评论 id
	private Date createTime;
	public static Integer AUDIT_FALSE = 6;
	private PushAlertEnum pushAlertEnum;// 推送 alert 枚举
	public static Integer BUSINESS_TYPE_AUDIT_FALSE = 3;

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PushAlertEnum getPushAlertEnum() {
		return pushAlertEnum;
	}

	public void setPushAlertEnum(PushAlertEnum pushAlertEnum) {
		this.pushAlertEnum = pushAlertEnum;
	}
}
