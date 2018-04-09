package com.moyou.moyouRms.model.jpush;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.msg.SuperMsg;

/**
 * 推送实体
 * 
 * @author PzC.
 * @time 2016年11月30日 下午3:25:49
 * 
 */
public class PushEntity {
	// 推送目标集合
	private List<String> aliases;
	// 附加参数
	private Map<String, String> extras = new HashMap<>();
	private SuperMsg msgEntity;

	public PushEntity() {}

	private <T extends SuperMsg>PushEntity(List<String> aliases, T msgEntity) {
		this.aliases = aliases;
		this.msgEntity = msgEntity;
		this.extras.put("key", msgEntity.getMsgSendType());
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	public static <T extends SuperMsg> PushEntity instance(List<String> aliases, T msgEntity) {
		return new PushEntity(aliases, msgEntity);
	}

	public SuperMsg getMsgEntity() {
		return msgEntity;
	}

	public void setMsgEntity(SuperMsg msgEntity) {
		this.msgEntity = msgEntity;
	}

}
