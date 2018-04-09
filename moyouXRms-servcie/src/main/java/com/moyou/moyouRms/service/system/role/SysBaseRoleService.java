package com.moyou.moyouRms.service.system.role;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysBaseRole.SysBaseRole;

public interface SysBaseRoleService {
    int deleteByPrimaryKey(String id);

    int insert(SysBaseRole record);

    int insertSelective(SysBaseRole record);

    SysBaseRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBaseRole record);

    int updateByPrimaryKey(SysBaseRole record);
    /**
     * 根据参数查询所有的角色列表
     * @param pb
     * @return
     */
    List<SysBaseRole> 	querySysBaseRoleByPageBean(PageBean pb);
    /**
     * 修改角色权限
     * @param roleId
     * @param list
     * @return
     */
	int updateResourcesForRole(String roleId, List<String> list);
	
}