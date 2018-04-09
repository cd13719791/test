package com.moyou.moyouRms.service.system.resource.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysBaseResources.SysBaseResourcesMapper;
import com.moyou.moyouRms.model.sysBaseResources.SysBaseResources;
import com.moyou.moyouRms.model.sysBaseRoleResources.SysBaseRoleResourcesKey;
import com.moyou.moyouRms.service.system.resource.SysBaseResourcesService;
import com.moyou.moyouRms.service.system.resource.SysBaseRoleResourcesService;
@Service
public class SysBaseResourcesServiceImpl implements SysBaseResourcesService {
@Resource
private SysBaseResourcesMapper sysBaseResourcesMapper;
@Resource
private SysBaseRoleResourcesService sysBaseRoleResourcesService;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysBaseResources record) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.insert(record);
	}

	@Override
	public int insertSelective(SysBaseResources record) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.insertSelective(record);
	}

	@Override
	public SysBaseResources selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysBaseResources record) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysBaseResources record) {
		// TODO Auto-generated method stub
		return sysBaseResourcesMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysBaseResources> queryResourcesByParentId(String jueseId,String parentId) {
		// TODO Auto-generated method stub
		List<SysBaseResources> list=sysBaseResourcesMapper.queryResourcesByParentId(parentId);
		list.forEach( s ->{
			s.setChildSysBaseResources(sysBaseResourcesMapper.queryResourcesByParentId(s.getId()));
		});
	List<SysBaseRoleResourcesKey> roleResounceList=	sysBaseRoleResourcesService.selectSysBaseRoleResourcesKeyByRoleId(jueseId);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).getChildSysBaseResources().size(); j++) {
				for (int j2 = 0; j2 < roleResounceList.size(); j2++) {
					if(list.get(i).getChildSysBaseResources().get(j).getId().equals(roleResounceList.get(j2).getResourcesId())){
						list.get(i).getChildSysBaseResources().get(j).setIsCheck(1);
						list.get(i).setIsCheck(1);
					}
				}
			}
		}
		return list;
	}

}
