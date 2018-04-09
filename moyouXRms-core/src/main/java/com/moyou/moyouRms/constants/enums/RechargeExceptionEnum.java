package com.moyou.moyouRms.constants.enums;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月3日 下午1:57:01 类说明 充值错误码
 */
public enum RechargeExceptionEnum
{
    HAVE_NOT_ENOUGH_FUND(1, "用户余额不足"), REQUEST_IS_ILLEGAL(2, "用户请求非法"), CHARGEBACK_FAILURE(3, "扣款失败"),

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
    private RechargeExceptionEnum(int value, String desc)
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
