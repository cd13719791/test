package com.moyou.moyouRms.constants.enums;
/**
 * @describe 说说点赞状态
 * @author liuxinyi
 * @date 2017年3月8日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum TalkPraiseSateEnum {
	CANCEL_PRAISE(0,"点赞后取消点赞"),
	YES_PRAISE(1,"已经点赞"),
	NO_PRAISE(2,"没有点赞"),
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
    private TalkPraiseSateEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static TalkPraiseSateEnum getByValue(Integer value) { 
    	if (value == null) {
    		 return NO_PRAISE;
    	}
        for (TalkPraiseSateEnum e : values()) {  
            if (e.getValue() == value) {  
                return e;  
            }  
        }  
        return NO_PRAISE;  
    }  

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
