package com.moyou.moyouRms.constants.enums;
/**
 * @describe 环信服务器 发送消息类型
 * @author liuxinyi
 * @date 2017年7月3日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public enum EasemobSingleChatMsgTypeEnum {

	TXT("txt","文本消息"),
	IMG("chatgroups","图片消息"),
	AUDIO("audio","音频消息"),
	VIDEO("video","视频消息"),
	CMD("cmd","命令消息"),
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
   private EasemobSingleChatMsgTypeEnum(String value, String desc) {
       this.value = value;
       this.desc = desc;
   }
   /**
    * 获取枚举
    * @param value
    * @return
    */
   public static EasemobSingleChatMsgTypeEnum getByValue(String value) {  
       for (EasemobSingleChatMsgTypeEnum e : values()) {  
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
