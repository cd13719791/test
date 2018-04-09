/**
 */
package com.moyou.moyouRms.constants.enums;

/**
 * @describe 用户资金搜索分类枚举
 * @author liuxinyi
 * @date 2017年4月13日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum SysEveryActivityModeTypeEnum {
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_TALK(1, "sys_every_activity_rule_mode_type_talk"),
	/**
	 * 文章活动规则key
	 */
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_DIARY(2, "sys_every_activity_rule_mode_type_diary"),
	/**
	 * 秘密活动规则key
	 */
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_SECRET(3, "sys_every_activity_rule_mode_type_secret"),
	/**
	 * 评论活动规则key
	 */
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_COMMENT(4, "sys_every_activity_rule_mode_type_comment"),
	/**
	 * 系统分享活动规则key
	 */
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_SYSTEM(5, "sys_every_activity_rule_mode_type_system"),
	/**
	 * 第三方分享活动规则key
	 */
	SYS_EVERY_ACTIVITY_RULE_MODE_TYPE_BIZ(6, "sys_every_activity_rule_mode_type_biz"), ;
	/**
	 * 编码
	 */
	private int value;
	/**
	 * 描述
	 */
	private String desc;

	// 构造方法
	private SysEveryActivityModeTypeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 获取枚举
	 * 
	 * @param value
	 * @return
	 */
	public static SysEveryActivityModeTypeEnum getByValue(int value) {
		for (SysEveryActivityModeTypeEnum e : values()) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
