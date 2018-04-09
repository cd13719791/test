package com.moyou.moyouRms.constants.enums;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月8日 下午3:56:53 类说明
 */
public enum MsgBarEnum
{
    JPUSH_BAR_MSG(1, "jpush_bar_msg"),
    JPUSH_BAR_TALK(2, "jpush_bar_talk"),
    JPUSH_BAR_NEERBY(3, "jpush_bar_neerby"),
    JPUSH_BAR_MAP(4, "jpush_bar_map"), 
    JPUSH_BAR_FIND(5, "jpush_bar_find"), 
    JPUSH_BAR_SUPPLY(6,"jpush_bar_supply"),
    JPUSH_BAR_SECRET(7, "jpush_bar_secret"),
    JPUSH_BAR_LIVE_ROOM(8, "jpush_bar_live_room"),
    JPUSH_BAR_LIVE_USER(9, "jpush_bar_live_user"),
    JPUSH_BAR_EARTH(10, "jpush_bar_earth"),
    
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
    private MsgBarEnum(int value, String desc)
    {
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(Integer value)
    {
        for (MsgBarEnum e : values())
        {
            if (e.getValue() == value)
            {
                return e.getDesc();
            }
        }
        return null;
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
