package com.moyou.moyouRms.constants.enums;
/**
 * @describe 红包类型
 * @author liuxinyi
 * @date 2017年4月1日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum RedPacketTypeEnum {
	SINGLE_TO_SINGLE(1,"单对单指定用户"),
	SINGLE_TO_CROWD(2,"单对群红包"),
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
    private RedPacketTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static RedPacketTypeEnum getByValue(int value) {  
        for (RedPacketTypeEnum e : values()) {  
            if (e.getValue() == value) {  
                return e;  
            }  
        }  
        return null;  
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
