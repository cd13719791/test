package com.moyou.moyouRms.service.system.role;

import java.util.List;

import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;

public interface SysBaseAccountRoleService {
	int deleteByPrimaryKey(SysBaseAccountRole key);

	int insert(SysBaseAccountRole record);

	int insertSelective(SysBaseAccountRole record);
	/**
	 * 修改用户角色
	 * @param roleList   角色集合
	 * @return
	 */
	int updateAccountRoleList(List<SysBaseAccountRole> roleList);
	/**
	 * 修改用户角色
	 * @param roleList   角色
	 * @return
	 */
	int updateAccountRole(Account account);
	List<SysBaseAccountRole>  selectSysBaseAccountRoleByAccountId(String id);
}
