/**  
 * @Title:  单聊扩展字段实体
 * @Package com.moyou.moyouRms.easemob.entity   
 * @Description:    TODO
 * @author: liuxinyi
 * @date:   2017年7月3日 下午2:10:41   
 * @email liuxinyi@mousns.com
 * @version V1.0 
 */
package com.moyou.moyouRms.easemob.entity;

import com.moyou.moyouRms.constants.enums.EasemobSingleChatMsgTypeEnum;

public class SingleChatMsg {
	
	public SingleChatMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param type
	 * @param msg
	 * @param action
	 */
	public SingleChatMsg(EasemobSingleChatMsgTypeEnum type, String msg, String action) {
		super();
		this.type = type;
		this.msg = msg;
		this.action = action;
	}
	public SingleChatMsg(EasemobSingleChatMsgTypeEnum type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}

	/**
	 * 消息类型
	 * @see EasemobSingleChatMsgTypeEnum
	 */
	private EasemobSingleChatMsgTypeEnum type;
	/**
	 * 消息内容
	 */
	private String msg;
	/**
	 * 消息action类型
	 */
	private String action;
	/**
	 * @return the type
	 */
	public EasemobSingleChatMsgTypeEnum getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(EasemobSingleChatMsgTypeEnum type) {
		this.type = type;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
}
