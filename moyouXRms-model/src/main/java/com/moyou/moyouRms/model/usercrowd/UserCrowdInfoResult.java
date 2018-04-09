package com.moyou.moyouRms.model.usercrowd;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.BaseModel;

public class UserCrowdInfoResult extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String crowdId;
	private String crowdName;
	private Integer memberCount;
	private Date createTime;
	private List<Map<String,Integer>> sortList;
	public List<Map<String,Integer>> getSortList() {
		return sortList;
	}
	public void setSortList(List<Map<String,Integer>> sortList) {
		this.sortList = sortList;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCrowdId() {
		return crowdId;
	}
	public void setCrowdId(String crowdId) {
		this.crowdId = crowdId;
	}
	public String getCrowdName() {
		return crowdName;
	}
	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
