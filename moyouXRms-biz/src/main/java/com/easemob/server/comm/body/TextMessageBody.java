package com.easemob.server.comm.body;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.constant.MsgType;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TextMessageBody extends MessageBody {
	private String msg;

	public TextMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String msg) {
		super(targetType, targets, from, ext);
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

    public ContainerNode<?> getBody() {
        if(!isInit()){
//            this.getMsgBody().put("type", MsgType.TEXT);
//            this.getMsgBody().put("msg", msg);
            ObjectNode extNode = this.getMsgBody().putObject("msg");
            extNode.put("type", MsgType.TEXT);
        	extNode.put("msg", msg);
            this.setInit(true);
        }

        return this.getMsgBody();
    }

    public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(msg);
	}
}
