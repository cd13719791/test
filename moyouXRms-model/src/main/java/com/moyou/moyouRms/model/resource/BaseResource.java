package com.moyou.moyouRms.model.resource;

import java.io.Serializable;
import java.util.List;

import com.moyou.moyouRms.model.report.CommonResource;


public abstract class BaseResource<E> implements Serializable {
	/**
	 * 基础数据类型  1=举报
	 */
	public static short BASE_RESOURCE_TYPE=1;
	private static final long serialVersionUID = 9118830413385185359L;
	/**
	 * 公共资源获取方法
	 */
	public List<CommonResource> resourceList;
	public BaseResource(List<CommonResource> resourceList, int id) {
		super();
		this.resourceList = resourceList;
		this.id = id;
	}
	public static  short OBJECT_REPORT=1; 
	public BaseResource() {
		super();
	}
//	public short objectType;
//	public short getObjectType() {
//		return objectType;
//	}
//	public void setObjectType(short objectType) {
//		this.objectType = objectType;
//	}
	public int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<CommonResource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<CommonResource> resourceList) {
		this.resourceList = resourceList;
	} 
}
