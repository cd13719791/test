package com.moyou.moyouRms.service.system.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysBaseAccountRole.SysBaseAccountRoleMapper;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;
import com.moyou.moyouRms.service.system.role.SysBaseAccountRoleService;
@Service
public class SysBaseAccountRoleServiceImpl implements SysBaseAccountRoleService {
@Resource
private SysBaseAccountRoleMapper sysBaseAccountRoleMapper;
	@Override
	public int deleteByPrimaryKey(SysBaseAccountRole key) {
		// TODO Auto-generated method stub
		return sysBaseAccountRoleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(SysBaseAccountRole record) {
		// TODO Auto-generated method stub
		return sysBaseAccountRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(SysBaseAccountRole record) {
		// TODO Auto-generated method stub
		return sysBaseAccountRoleMapper.insertSelective(record);
	}

	@Override
	public int updateAccountRoleList(List<SysBaseAccountRole> roleList) {
		try {
		String accountId=roleList.get(0).getAccountId();
		sysBaseAccountRoleMapper.deleteByAccountId( accountId);
		roleList.forEach(s->{
			SysBaseAccountRole sysBaseAccountRole=new SysBaseAccountRole();
			sysBaseAccountRole.setRoleId(s.getRoleId());
			sysBaseAccountRoleMapper.insertSelective(sysBaseAccountRole);
		});
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public int updateAccountRole(Account account) {
		sysBaseAccountRoleMapper.deleteByAccountId(account.getAccountId());
		SysBaseAccountRole sysBaseAccountRole=new SysBaseAccountRole();
		sysBaseAccountRole.setRoleId(account.getRoleId());
		sysBaseAccountRole.setAccountId(account.getAccountId());
		return sysBaseAccountRoleMapper.insertSelective(sysBaseAccountRole);
	}

	@Override
	public List<SysBaseAccountRole> selectSysBaseAccountRoleByAccountId(
			String id) {
		// TODO Auto-generated method stub
		return sysBaseAccountRoleMapper.selectSysBaseAccountRoleByAccountId(id);
	}

}
