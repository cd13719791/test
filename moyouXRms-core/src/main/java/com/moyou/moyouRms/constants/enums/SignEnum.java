package com.moyou.moyouRms.constants.enums;
/**
 * 用户签到规则枚举 
 * @author Administrator
 *
 */
public enum SignEnum {
	FIRST_DAY(1,"签到第一天适用规则ID"),
	SECCEND_DAY(2,"签到第一天适用规则ID"),
	THIRD_DAY(3,"签到第一天适用规则ID"),
	FOUR_DAY(4,"签到第一天适用规则ID"),
	FIVE_DAY(5,"签到第一天适用规则ID"),
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
	private SignEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static SignEnum getByValue(int value) {  
        for (SignEnum e : values()) {  
            if (e.getValue() == value) {  
                return e;  
            } 
       
        }  
        return FIVE_DAY;  
    } 
}
