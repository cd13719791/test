package com.moyou.moyouRms.constants.enums;

public enum PushAlertEnum {
	JPUSH_FOLLOW("jpush_follow"), // 关注推送 alert
	JPUSH_RECOMMENT("jpush_recomment"), // 回复评论推送 alert
	JPUSH_PRAISE_TALK("jpush_praise_talk"), // 说说点赞推送 alert
	JPUSH_REWARD_TALK("jpush_reward_talk"), // 打赏说说推送 alert
	JPUSH_REPUBLISH_TALK("jpush_republish_talk"), // 转发说说推送 alert
	JPUSH_COMMENT_TALK("jpush_comment_talk"), // 评论说说推送 alert
	JPUSH_PRAISE_DIARY("jpush_praise_diary"), // 点赞故事推送 alert
	JPUSH_REWARD_DIARY("jpush_reward_diary"), // 打赏故事推送 alert
	JPUSH_COMMENT_DIARY("jpush_comment_diary"), // 评论故事推送 alert
	JPUSH_AUDIT_FALSE("jpush_audit_false"), // 审核反馈推送 alert
	JPUSH_OFFLINE_USER_5_DAY("jpush_offline_user_5_day"), // 用户长期不登录第5天的推送alert
	JPUSH_OFFLINE_USER_10_DAY("jpush_offline_user_10_day"), ; // 用户长期不登录第10天的推送alert
	private String key;

	private PushAlertEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
