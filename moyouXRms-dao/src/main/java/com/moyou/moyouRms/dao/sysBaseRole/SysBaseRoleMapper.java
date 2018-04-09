package com.moyou.moyouRms.dao.sysBaseRole;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysBaseRole.SysBaseRole;

public interface SysBaseRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysBaseRole record);

    int insertSelective(SysBaseRole record);

    SysBaseRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBaseRole record);

    int updateByPrimaryKey(SysBaseRole record);
    /**
     * 角色列表初始化
     * @param pb
     * @return
     */
	List<SysBaseRole> querySysBaseRoleByPageBean(PageBean pb);
}