/**
 */
package com.moyou.moyouRms.constants.enums;

/**
 * @describe 用户资金类型枚举
 * @author liuxinyi
 * @date 2017年1月17日
 * @email liuxinyi@mousns.com
 * @version 1.0.0 1说说打赏现金2日记打赏现金3充值现金4提现5签到金币6红包退还7领取红包 8发送红包 9发布说说获得金币10消费金币
 */
public enum UserFundModeEnum {
	TALK_REWARD((short) 1, "说说打赏现金"), // 支出
	DIARY_REWARD((short) 2, "故事打赏现金"), // 支出
	TOP_UP((short) 3, "充值现金"), 
	WITHDRAW_DEPOSIT((short) 4, "提现"), 
	SIGN_GOLD((short) 5, "签到获得金币"), 
	RED_REFUND((short) 6, "红包退还"), 
	RECEIVE_RED_ENVELOPE((short) 7, "领取红包"), 
	SEND_RED_ENVELOPE((short) 8, "发送单用户红包"), 
	RELEASE_TALK((short) 9, "发布说说获得金币"), 
	CONSUMER_GOLD((short) 10, "消费金币"), 
	SYSTEM_UPDATE((short) 11, "系统修改金币"), 
	TALK_REWARD_GAIN((short) 12, "说说打赏现金"), // 收入
	DIARY_REWARD_GAIN((short) 13, "故事打赏现金"), // 收入
	SEND_RED_ENVELOPE_CROWD((short) 14, "发送群用户红包"), 
	DIARY_GOLD_TYPE((short) 15, "发布故事奖励金币"), // 收入
	SECRET_GOLD_TYPE((short) 16, "发布秘密奖励金币"), // 收入
	COMMENT_GOLD_TYPE((short) 17, "评论奖励金币"), // 收入
	SHARE_GOLD_TYPE((short) 18, "分享奖励金币"), // 收入
	SYSTEM_DIARY_REWARD((short) 19, "系统打赏故事现金"), // 支出
	SYSTEM_TALK_REWARD((short) 20, "系统打赏说说现金"), // 支出
	SYSTEM_TALK_REWARD_GAIN((short) 21, "系统打赏说说现金"), // 收入
	SYSTEM_DIARY_REWARD_GAIN((short) 22, "系统打赏故事现金"), // 收入
	SYSTEM_RECHARGE((short) 23, "系统充值打赏用余额"),
	// 收入
	SYSTEM_GOLD_BACK((short) 24, "系统返还金币"), SUPPLY_PALAZA_TYPE((short) 25, "发布供求消耗金币"), // 支出
	SUPPLY_PALAZA_REFRESH_TYPE((short) 26, "刷新供求消耗金币"), // 支出
	SUPPLY_UPDATE_TYPE((short) 27, "编辑广告消耗金币"), // 支出;
	BUY_GOLD((short) 28, "购买金币支付金钱"), 
	BUY_GOLD_ADD_GOLD((short) 29, "购买金币成功添加金币"),
	ROB_DEL_GOLD((short) 30, "占位加价消耗金币"),//支出 
	ACTIVITY_EARN_TALK((short) 31, "活动赚取-说说"),
	ACTIVITY_EARN_DIARY((short) 32, "活动赚取-故事"),
	ACTIVITY_EARN_SECRET((short) 33, "活动赚取-秘密"),
	ACTIVITY_EARN_COMMENT((short) 34, "活动赚取-评论"),
	ACTIVITY_EARN_WHOLESHARE((short) 35, "活动赚取-全局分享"),
	ACTIVITY_EARN_SYSSHARE((short) 36, "活动赚取-系统分享"),
	ACTIVITY_EARN_TALK_GOLD((short) 37, "活动赚取-说说-金币"),
	ACTIVITY_EARN_DIARY_GOLD((short) 38, "活动赚取-故事-金币"),
	ACTIVITY_EARN_SECRET_GOLD((short) 39, "活动赚取-秘密-金币"),
	ACTIVITY_EARN_COMMENT_GOLD((short) 40, "活动赚取-评论-金币"),
	ACTIVITY_EARN_WHOLESHARE_GOLD((short) 41, "活动赚取-全局分享-金币"),
	ACTIVITY_EARN_SYSSHARE_GOLD((short) 42, "活动赚取-系统分享-金币"),
	
	
	ACTIVITY_PAY_TALK((short) 43, "活动支出-说说"),
	ACTIVITY_PAY_DIARY((short) 44, "活动支出-故事"),
	ACTIVITY_PAY_SECRET((short) 45, "活动支出-秘密"),
	ACTIVITY_PAY_COMMENT((short) 46, "活动支出-评论"),
	ACTIVITY_PAY_WHOLESHARE((short) 47, "活动支出-全局分享"),
	ACTIVITY_PAY_SYSSHARE((short) 48, "活动支出-系统分享"),
	ACTIVITY_PAY_TALK_GOLD((short) 49, "活动支出-说说-金币"),
	ACTIVITY_PAY_DIARY_GOLD((short) 50, "活动支出-故事-金币"),
	ACTIVITY_PAY_SECRET_GOLD((short) 51, "活动支出-秘密-金币"),
	ACTIVITY_PAY_COMMENT_GOLD((short) 52, "活动支出-评论-金币"),
	ACTIVITY_PAY_WHOLESHARE_GOLD((short) 53, "活动支出-全局分享-金币"),
	ACTIVITY_PAY_SYSSHARE_GOLD((short) 54, "活动支出-系统分享-金币"),

	//主播提现
	LIVESHOW_GOLD_CONVERT_MONEY((short) 55, "金币兑换金钱"),
	//主播收入
	LIVESHOW_GET_GOLD((short) 56, "直播获得金币（送礼）"),
	LIVESHOW_DANMU_GOLD((short) 57, "直播发弹幕支出金币"),
	LIVESHOW_DEL_GLOD((short) 58, "观看直播得到金币"),
	LIVESHOW_SAY_TALK_GLOD((short) 59, "连麦消耗金币"),
	LIVESHOW_SEE_GLOD((short) 60, "连视频消耗金币"),
	//观众支出
	LIVESHOW_PAY_GOLD((short) 61, "直播支出金币（送礼）"),
	WATCH_LIVESHOW_DANMU_GOLD((short) 62, "直播发弹幕支出金币"),
	WATCH_LIVESHOW_DEL_GLOD((short) 63, "观看直播消耗金币"),
	WATCH_LIVESHOW_SAY_TALK_GLOD((short) 64, "连麦消耗金币"),
	WATCH_LIVESHOW_SEE_GLOD((short) 65, "连视频消耗金币"),
	ACTIVITY_EARN_TALK_TWICE((short) 66, "活动赚取-说说"),
	ACTIVITY_EARN_DIARY_TWICE((short) 67, "活动赚取-故事"),
	ACTIVITY_PAY_TALK_TWICE((short) 78, "活动支出-说说"),
	ACTIVITY_PAY_DIARY_TWICE((short) 79, "活动支出-故事"),

	;
	/**
	 * 编码
	 */
	private short value;
	/**
	 * 描述
	 */
	private String desc;

	// 构造方法
	private UserFundModeEnum(short value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 获取枚举
	 * 
	 * @param value
	 * @return
	 */
	public static UserFundModeEnum getByValue(int value) {
		for (UserFundModeEnum e : values()) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}

	public short getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
