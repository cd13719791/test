package com.moyou.moyouRms.model.po.system.account;

import java.io.Serializable;

public class Statistical implements Serializable {
	/**
	 * 管理员信息统计
	 */
	private static final long serialVersionUID = -7944098302824165006L;
	// 假账号数
	private String countAccount;
	// 动态总数
	private String countDynamic;
	// 圈子总数
	private String countCircle;
	// 话题总数
	private String countCircleTopic;
	// 话题评论总数
	private String countCircleTopicComment;
	// 动态评论总数
	private String countDynamicComment;
	// 关注总数
	private String countFocus;
 //管理员权限信息
	private Account account;
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getCountAccount() {
		return countAccount;
	}

	public void setCountAccount(String countAccount) {
		this.countAccount = countAccount;
	}

	public String getCountDynamic() {
		return countDynamic;
	}

	public void setCountDynamic(String countDynamic) {
		this.countDynamic = countDynamic;
	}

	public String getCountCircle() {
		return countCircle;
	}

	public void setCountCircle(String countCircle) {
		this.countCircle = countCircle;
	}

	public String getCountCircleTopic() {
		return countCircleTopic;
	}

	public void setCountCircleTopic(String countCircleTopic) {
		this.countCircleTopic = countCircleTopic;
	}

	public String getCountCircleTopicComment() {
		return countCircleTopicComment;
	}

	public void setCountCircleTopicComment(String countCircleTopicComment) {
		this.countCircleTopicComment = countCircleTopicComment;
	}

	public String getCountDynamicComment() {
		return countDynamicComment;
	}

	public void setCountDynamicComment(String countDynamicComment) {
		this.countDynamicComment = countDynamicComment;
	}

	public String getCountFocus() {
		return countFocus;
	}

	public void setCountFocus(String countFocus) {
		this.countFocus = countFocus;
	}

	public String getCountFeriend() {
		return countFeriend;
	}

	public void setCountFeriend(String countFeriend) {
		this.countFeriend = countFeriend;
	}

	// 好友总数
	private String countFeriend;

}
