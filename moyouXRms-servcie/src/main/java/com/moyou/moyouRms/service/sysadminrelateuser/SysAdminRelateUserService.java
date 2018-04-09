package com.moyou.moyouRms.service.sysadminrelateuser;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysAdminRelateUser.SysAdminRelateUser;
import com.moyou.moyouRms.response.ApiResult;

public interface SysAdminRelateUserService {
    int insert(SysAdminRelateUser record);

    int insertSelective(SysAdminRelateUser record);
    /**
     * 查询单聊账号绑定的假用户账号
     * @param account
     * @return
     */
    List<SysAdminRelateUser> selectSysAdminRelateUserByAccountId(String account);
    List<SysAdminRelateUser> selectSysAdminRelateUserByAccountIdPage(PageBean pb);
    /**
     * 修改账户匹配的假账户数量
     * @param number
     * @param userNumberEnd 
     * @param accountId
     * @return
     */
    ApiResult updateAccountUser(Integer userNumberStart, Integer userNumberEnd, String accountId);
	/**
	 * 获取假用户数量
	 * @param account
	 * @return
	 */
	int selectSysAdminRelateUserCountByAccountId(String account);
	/**
	 * 清空账号未读消息数
	 * @param valueOf
	 * @return
	 */
	int updateAccountOfflineCount(String valueOf);
	int updateAccountOfflineCountByUserId(SysAdminRelateUser sysAdminRelateUser);

} 