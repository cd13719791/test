package com.moyou.moyouRms.service.system.role.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysBaseRole.SysBaseRoleMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysBaseRole.SysBaseRole;
import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;
import com.moyou.moyouRms.service.system.resource.SysBaseRoleResourcesService;
import com.moyou.moyouRms.service.system.role.SysBaseRoleService;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
@Service
public class SysBaseRoleServiceImpl implements SysBaseRoleService {
@Resource
private SysBaseRoleMapper sysBaseRoleMapper;
@Resource
private SysBaseRoleResourcesService sysBaseRoleResourcesService;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysBaseRole record) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(SysBaseRole record) {
		// TODO Auto-generated method stub
		record.setId(UUIDUtil.getUUID());
		record.setCreatetime(new Date());
		return sysBaseRoleMapper.insertSelective(record);
	}

	@Override
	public SysBaseRole selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysBaseRole record) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysBaseRole record) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysBaseRole> querySysBaseRoleByPageBean(PageBean pb) {
		// TODO Auto-generated method stub
		return sysBaseRoleMapper.querySysBaseRoleByPageBean(pb);
	}

	@Override
	public int updateResourcesForRole(String roleId,  List<String> list) {
		// TODO Auto-generated method stub
		int index =0;
		SysBaseRole sysBaseRole=this.selectByPrimaryKey(roleId);
		if(StringUtil.isNotEmpty(roleId)&&sysBaseRole!=null){
			sysBaseRoleResourcesService.deleteByRoleId(roleId);
			for (int i = 0; i < list.size(); i++) {
			SysBaseRoleResourcesKey sysBaseRoleResourcesKey =new SysBaseRoleResourcesKey();
			sysBaseRoleResourcesKey.setResourcesId(list.get(i).toString());
			sysBaseRoleResourcesKey.setRoleId(roleId);
			index+=sysBaseRoleResourcesService.insertSelective(sysBaseRoleResourcesKey);
			}
		}
		return index;
	}
	
}
