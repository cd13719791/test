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
public enum UserFundLogModePlusType {
	ACTIVITY(1, "活动"),
	LIVE_SHOW(2, "直播"),

	
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
    private UserFundLogModePlusType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static UserFundLogModePlusType getByValue(int value) {  
        for (UserFundLogModePlusType e : values()) {  
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
