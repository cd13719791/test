package com.moyou.moyouRms.service.system.resource;

import java.util.List;

import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;

public interface SysBaseRoleResourcesService {
	int deleteByPrimaryKey(SysBaseRoleResourcesKey key);

	int insert(SysBaseRoleResourcesKey record);

	int insertSelective(SysBaseRoleResourcesKey record);

	/**
	 * 根据角色id 获取所有的权限
	 * 
	 * @return
	 */
	List<SysBaseRoleResourcesKey> selectSysBaseRoleResourcesKeyByRoleId(
			String id);
	/**
	 * 删除这个角色所有权限
	 * @param id
	 * @return
	 */
	int deleteByRoleId(String id);
}
