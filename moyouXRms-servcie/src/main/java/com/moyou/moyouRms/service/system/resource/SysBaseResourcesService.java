package com.moyou.moyouRms.service.system.resource;

import java.util.List;

import com.moyou.moyouRms.model.sysBaseResources.SysBaseResources;

public interface SysBaseResourcesService {
    int deleteByPrimaryKey(String id);

    int insert(SysBaseResources record);

    int insertSelective(SysBaseResources record);

    SysBaseResources selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBaseResources record);

    int updateByPrimaryKey(SysBaseResources record);
    /**
     * 加载所有资源 根据父级id
     * @return
     */
    List<SysBaseResources> queryResourcesByParentId(String jueseId,String parentId);
    
}