package com.moyou.moyouRms.dao.sysAdminRelateUser;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysAdminRelateUser.SysAdminRelateUser;

public interface SysAdminRelateUserMapper {
    int insert(SysAdminRelateUser record);

    int insertSelective(SysAdminRelateUser record);
    List<SysAdminRelateUser> selectSysAdminRelateUserByAccountId(String account);
    List<SysAdminRelateUser> c(PageBean pb);
    
    int delete(SysAdminRelateUser record);
    /**
     * 获取假用户数量
     * @param account
     * @return
     */
	Map<String,Object> selectSysAdminRelateUserCountByAccountId(String account);

	int updateAccountOfflineCount(String param);
	int updateAccountOfflineCountByUserId(SysAdminRelateUser sysAdminRelateUser);

	List<SysAdminRelateUser> selectSysAdminRelateUserByAccountIdPage(PageBean pb);
} 