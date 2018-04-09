package com.easemob.server.comm.body;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.constant.MsgType;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CommandMessageBody extends MessageBody {
	private String action;

	public CommandMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String action) {
		super(targetType, targets, from, ext);
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	  public ContainerNode<?> getBody() {
	        if(!isInit()){
	            ObjectNode extNode = this.getMsgBody().putObject("msg");
	            extNode.put("type", MsgType.CMD);
	            extNode.put("action", action);
	            this.setInit(true);
	        }
	        return this.getMsgBody();
	    }
	  
	@Override
	public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(action);
	}
}
