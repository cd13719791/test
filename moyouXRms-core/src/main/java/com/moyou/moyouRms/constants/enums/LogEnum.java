package com.moyou.moyouRms.constants.enums;

public enum LogEnum {

	DEFAULT_VALUE(0,"默认值"),
	UPDATE_GOLD(1,"修改金币"),
	SYSTEM_LOGIN(2,"登录"),
	SYSTEM_INSERT_ACCOUNT(3,"添加新的管理员 "),
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
   private LogEnum(int value, String desc) {
       this.value = value;
       this.desc = desc;
   }
   /**
    * 获取枚举
    * @param value
    * @return
    */
   public static LogEnum getByValue(int value) {  
       for (LogEnum e : values()) {  
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
