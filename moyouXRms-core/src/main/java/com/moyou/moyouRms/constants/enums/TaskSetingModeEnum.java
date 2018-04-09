package com.moyou.moyouRms.constants.enums;
/**
 * @describe 机器人任务设置模块类型
 * @author liuxinyi
 * @date 2017年3月6日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum TaskSetingModeEnum {
	TALk_PRAISE(1,"自动说说点赞"),
	COMMENT(1,"自动评论"),
	PUBLISH_TALK(1,"自动发布说说"),
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
    private TaskSetingModeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static TaskSetingModeEnum getByValue(int value) {  
        for (TaskSetingModeEnum e : values()) {  
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
