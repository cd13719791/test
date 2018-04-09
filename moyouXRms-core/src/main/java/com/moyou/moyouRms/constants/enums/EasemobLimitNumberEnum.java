package com.moyou.moyouRms.constants.enums;
/**
 * @describe 环信服务器系统限制
 * @author liuxinyi
 * @date 2017年6月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum EasemobLimitNumberEnum {

	USER_CREATE_CROWD_NUMBER(500,"环信用户创建群上线"),
	CROWD_MEMBER__NUMBER_DEFAULT(500,"群成员默认个数"),
	CROWD_MEMBER_NUMBER_MAX(2000,"群最大成员数"),
	CHAT_MEMBER_NUMBER_MAX(5000,"聊天室最大成员数"),
	CROWD_ADMIN_NUMBER(5,"群管理员人数"),
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
   private EasemobLimitNumberEnum(int value, String desc) {
       this.value = value;
       this.desc = desc;
   }
   /**
    * 获取枚举
    * @param value
    * @return
    */
   public static EasemobLimitNumberEnum getByValue(int value) {  
       for (EasemobLimitNumberEnum e : values()) {  
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
