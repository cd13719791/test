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
public enum UserFundSrarchCategoryEnum {
	WITHDRAW_DEPOSIT(1, "提现"),
	REWARD(2, "消费"),
	RED_PACKET(3, "红包"),
	GOLD(4, "金币"),
	ACTIVITY_EARN(5, "活动赚取"),
	;
    /**
     * 编码
     */
    private int value;
    /**
     * 描述
     */
    private String desc;

    // 构造方法
    private UserFundSrarchCategoryEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static UserFundSrarchCategoryEnum getByValue(int value) {  
        for (UserFundSrarchCategoryEnum e : values()) {  
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
