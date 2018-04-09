package com.moyou.moyouRms.model.po.system.resource;

import com.moyou.moyouRms.model.BaseModel;


public class OrgResources extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String orgId;
	
	private String resourcesId;
		
	public OrgResources() {}

	public OrgResources(String orgId, String resourcesId) {
		this.orgId = orgId;
		this.resourcesId = resourcesId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}
	
	
}
