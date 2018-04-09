package com.moyou.moyouRms.constants.enums;
/**
 * 后台金币获取模型枚举
 * @author Administrator
 *
 */
public enum ModeRuleEnum {
	SIGN_MODE((short)1,"签到第一天适用规则ID"),
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
    private ModeRuleEnum(short value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static ModeRuleEnum getByValue(int value) {  
        for (ModeRuleEnum e : values()) {  
            if (e.getValue() == value) {  
                return e;  
            }  
        }  
        return null;  
    }  

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
