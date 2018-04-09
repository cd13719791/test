package com.moyou.moyouRms.constants.enums;

/**
 * @title 七牛文件类型枚举类
 * @author liuxinyi
 * @date 2016年6月4日
 * @version 1.0.0
 */
public enum QiNiuFileEnum
{
    IMAGE(3, "图片"), VIDEO(2, "视频"), AUDIO(1, "音频"),

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
    private QiNiuFileEnum(int value, String desc)
    {
        this.value = value;
        this.desc = desc;
    }

    public int getValue()
    {
        return value;
    }

    public String getDesc()
    {
        return desc;
    }

}
