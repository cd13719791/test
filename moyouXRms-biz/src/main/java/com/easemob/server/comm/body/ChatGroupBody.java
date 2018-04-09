package com.easemob.server.comm.body;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @Title: 修改群主使用
 * @Description: TODO
 * @author: chenxu
 * @date: 2017年8月24日 下午4:07:20
 * @email chenxu@mousns.com
 * @version V3.5
 */
public class ChatGroupBody implements BodyWrapper {
	private String groupName;// 群名
	private String description;// 群组描述
	private Boolean isPublic;// 群组类型：true：公开群，false：私有群。
	private Long maxusers;// 群成员上限，创建群组的时候设置，可修改。
	private Boolean approval;// 加入公开群是否需要批准，默认值是false（加入公开群不需要群主批准），此属性为必选的，私有群必须为true
	private String owner;// 群主
	private String[] members;// 群成员的环信 ID。例如：{“member”:“xc6xrnbzci”}。
	private String newowner;// 新群主

	public ChatGroupBody(String groupName, String description, Boolean isPublic, Long maxusers,
			Boolean approval, String owner, String[] members) {
		this.groupName = groupName;
		this.description = description;
		this.isPublic = isPublic;
		this.maxusers = maxusers;
		this.approval = approval;
		this.owner = owner;
		this.members = members;
	}

	public String getNewowner() {
		return newowner;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMaxusers(Long maxusers) {
		this.maxusers = maxusers;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}

	public void setNewowner(String newowner) {
		this.newowner = newowner;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public Long getMaxusers() {
		return maxusers;
	}

	public Boolean getApproval() {
		return approval;
	}

	public String getOwner() {
		return owner;
	}

	public String[] getMembers() {
		return members;
	}

	public ContainerNode<?> getBody() {
		ObjectNode body = JsonNodeFactory.instance.objectNode();
		body.put("groupname", groupName).put("description", description).put("public", isPublic)
				.put("approval", approval).put("owner", owner);
		if (null != maxusers) {
			body.put("maxusers", maxusers);
		}
		if (ArrayUtils.isNotEmpty(members)) {
			ArrayNode membersNode = body.putArray("members");
			for (String member : members) {
				membersNode.add(member);
			}
		}

		return body;
	}

	public Boolean validate() {
		return StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(description)
				&& null != isPublic && null != approval && StringUtils.isNotBlank(owner);
	}
}
