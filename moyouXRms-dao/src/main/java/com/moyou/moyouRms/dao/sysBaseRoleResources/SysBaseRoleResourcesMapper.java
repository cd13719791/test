package com.moyou.moyouRms.dao.sysBaseRoleResources;

import java.util.List;

import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;

public interface SysBaseRoleResourcesMapper {
    int deleteByPrimaryKey(SysBaseRoleResourcesKey key);

    int insert(SysBaseRoleResourcesKey record);

    int insertSelective(SysBaseRoleResourcesKey record);
    /**
     * 根据角色id 获取全部权限
     * @param id
     * @return
     */
	List<SysBaseRoleResourcesKey> selectSysBaseRoleResourcesKeyByRoleId(
			String id);
	int deleteByRoleId(String roleId);
}