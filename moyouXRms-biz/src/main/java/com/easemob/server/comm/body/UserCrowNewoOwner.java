package com.easemob.server.comm.body;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年6月16日 下午3:53:07 
 * 类说明 
 */
public class UserCrowNewoOwner implements BodyWrapper {

	private String newowner;//新群主
	
	public UserCrowNewoOwner(String newowner) {
		super();
		this.newowner = newowner;
	}

	public String getNewowner() {
		return newowner;
	}

	public void setNewowner(String newowner) {
		this.newowner = newowner;
	}

	@Override
	public ContainerNode<?> getBody() {
		// TODO Auto-generated method stub
		ObjectNode body = JsonNodeFactory.instance.objectNode();
		 body.put("newowner", newowner);
		return body;
	}

	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		return StringUtils.isNotBlank(newowner);
	}

}
