package com.moyou.moyouRms.constants.enums;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.util.PropertiesUtil;

/**
 * @describe 请求状态码
 * @author liuxinyi
 * @date 2017年1月15日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum ResponseEnum {
	SUCCESS(RESPONSE.SUCCESS), // 请求成功
	ERROR(RESPONSE.ERROR), // 请求失败
	/**
	 * 红包相关
	 */
	RED_PACKET_PAST(1000), // 红包已过期
	RED_PACKET_ROB_0(1001), // 红包已抢完
	RED_PACKET_NOT_EXIST(1002), // 红包不存在
	RED_PACKET_IS_GET(1003), // 红包已领取
	RED_PACKET_REFUND(1004), // 红包已退还
	SCHEDULE_ERRO(3000), // =任务调度异常
	SCHEDULE_DOES_NOT_DO(3001), // =任务调度未执行
	IDENTIFYING_CODE(4000), // 后台充值系统 验证码 正常
	IDENTIFYING_CODE_ERRO(4001), // 后台充值系统 验证码 异常

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
	private ResponseEnum(int value) {
		this.value = value;
		this.desc = PropertiesUtil.getApiCodeMsgByKey(CONSTANT.API_CODE_MSG_PREFIX_KEY + value);
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * 获取枚举
	 * 
	 * @param value
	 * @return
	 */
	public static ResponseEnum getByValue(int value) {
		for (ResponseEnum e : values()) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}
}
