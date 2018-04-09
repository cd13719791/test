package com.moyou.moyouRms.dao.sysBaseAccountRole;

import java.util.List;

import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;

public interface SysBaseAccountRoleMapper {
    int deleteByPrimaryKey(SysBaseAccountRole key);

    int insert(SysBaseAccountRole record);

    int insertSelective(SysBaseAccountRole record);

	int deleteByAccountId(String accountId);
//	int updateAccountRole(Account account);

	List<SysBaseAccountRole> selectSysBaseAccountRoleByAccountId(String id);
}