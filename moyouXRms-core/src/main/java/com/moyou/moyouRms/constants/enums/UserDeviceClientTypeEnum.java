/**  
 * @Title:  用户设备客户端类型
 * @Package com.immouo.moyou.core.constants.enums   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月17日 下午2:01:30   
 * @email liuxinyi@mousns.com
 * @version V3.4
 */

package com.moyou.moyouRms.constants.enums;
public enum UserDeviceClientTypeEnum {
// 注册类型0旧账号状态1安卓2ios3h5端4pc端
	ANDROID(1, "安卓"),
	IOS(2, "苹果"),
	H5(3, "html5页面"),
	PC(4, "台式电脑"),
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
    private UserDeviceClientTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 获取枚举
     * @param value
     * @return
     */
    public static UserDeviceClientTypeEnum getByValue(int value) {  
        for (UserDeviceClientTypeEnum e : values()) {  
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
