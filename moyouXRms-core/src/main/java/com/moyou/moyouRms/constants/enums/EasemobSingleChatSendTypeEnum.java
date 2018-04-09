package com.moyou.moyouRms.constants.enums;
/**
 * @describe 环信服务器 发送消息的操作对象类型
 * @author liuxinyi
 * @date 2017年7月3日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum EasemobSingleChatSendTypeEnum {

	SNED_TO_USERS("users","给用户发消息"),
	SNED_TO_GROUPS("chatgroups","给群发消息"),
	SNED_TO_ROOMS("chatrooms","给聊天室发消息"),
	 ;
	 /**
    * 类型值
    */
   private String value;
   /**
    * 描述
    */
   private String desc;

   // 构造方法
   private EasemobSingleChatSendTypeEnum(String value, String desc) {
       this.value = value;
       this.desc = desc;
   }
   /**
    * 获取枚举
    * @param value
    * @return
    */
   public static EasemobSingleChatSendTypeEnum getByValue(int value) {  
       for (EasemobSingleChatSendTypeEnum e : values()) {  
           if (e.getValue().equals(value)) {  
               return e;  
           }  
       }  
       return null;  
   }  

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
