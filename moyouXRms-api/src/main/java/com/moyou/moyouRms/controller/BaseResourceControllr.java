package com.moyou.moyouRms.controller;

import java.util.List;

import javax.annotation.Resource;


import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.resource.BaseResource;
import com.moyou.moyouRms.service.report.CommonResourceService;


@SuppressWarnings("rawtypes")
public class BaseResourceControllr<E extends BaseResource> extends BaseController{
	@Resource
	private CommonResourceService commonResourceService;
	/***
	 * 封装资源图片
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> setCommonResourceList(List<E> record){
		record.forEach( report -> {
			CommonResource cr=new CommonResource();
			cr.setObjectType(BaseResource.BASE_RESOURCE_TYPE);
			cr.setObjectId(report.getId());
			report.setResourceList(commonResourceService.selectCommonResourceByObjectId(cr));
		} );
		return record;
	}
}
