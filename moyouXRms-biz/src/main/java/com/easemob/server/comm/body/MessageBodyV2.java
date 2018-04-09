package com.easemob.server.comm.body;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class MessageBodyV2 implements BodyWrapper {
	private ObjectNode msgBody;

	private String targetType;

	private String[] targets;

	private Map<String, String> ext;

	private boolean init = false;

	public MessageBodyV2(String targetType, String[] targets, Map<String, String> ext) {
		this.targetType = targetType;
		this.targets = targets;
		this.ext = ext;
	}

	public String getTargetType() {
		return targetType;
	}

	public String[] getTargets() {
		return targets;
	}

	public Map<String, String> getExt() {
		return ext;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	protected ObjectNode getMsgBody() {
		if (null == this.msgBody) {
			this.msgBody = JsonNodeFactory.instance.objectNode();
			msgBody.put("target_type", targetType);
			ArrayNode targetsNode = msgBody.putArray("target");
			for (String target : targets) {
				targetsNode.add(target);
			}

			if (null != ext) {
				ObjectNode extNode = msgBody.putObject("ext");
				Iterator<String> iter = ext.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					extNode.put(key, ext.get(key));
				}
			}
		}
		return msgBody;
	}

	public Boolean validate() {
		return StringUtils.isNotBlank(targetType) && isValidTargetType()
				&& ArrayUtils.isNotEmpty(targets);
	}

	private boolean isValidTargetType() {
		return "users".equals(targetType) || "chatgroups".equals(targetType)
				|| "chatrooms".equals(targetType);
	}
}
