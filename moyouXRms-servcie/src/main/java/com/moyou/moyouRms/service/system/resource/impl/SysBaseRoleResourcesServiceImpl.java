package com.moyou.moyouRms.service.system.resource.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysBaseRoleResources.SysBaseRoleResourcesMapper;
import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;
import com.moyou.moyouRms.service.system.resource.SysBaseRoleResourcesService;
@Service
public class SysBaseRoleResourcesServiceImpl implements
		SysBaseRoleResourcesService {
@Resource
private SysBaseRoleResourcesMapper sysBaseRoleResourcesMapper;
	@Override
	public int deleteByPrimaryKey(SysBaseRoleResourcesKey key) {
		// TODO Auto-generated method stub
		return sysBaseRoleResourcesMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(SysBaseRoleResourcesKey record) {
		// TODO Auto-generated method stub
		return sysBaseRoleResourcesMapper.insert(record);
	}

	@Override
	public int insertSelective(SysBaseRoleResourcesKey record) {
		// TODO Auto-generated method stub
		return sysBaseRoleResourcesMapper.insertSelective(record);
	}

	@Override
	public List<SysBaseRoleResourcesKey> selectSysBaseRoleResourcesKeyByRoleId(String id) {
		// TODO Auto-generated method stub
		return sysBaseRoleResourcesMapper.selectSysBaseRoleResourcesKeyByRoleId(id);
	}

	@Override
	public int deleteByRoleId(String id) {
		// TODO Auto-generated method stub
		return sysBaseRoleResourcesMapper.deleteByRoleId(id);
	}

}
