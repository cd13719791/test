package com.moyou.moyouRms.service.systalkmanagerinfo;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;

public interface SysTalkManagerinfoService {
	int deleteByPrimaryKey(Integer id);

	int insert(SysTalkManagerInfo record);

	int insertSelective(SysTalkManagerInfo record);

	SysTalkManagerInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysTalkManagerInfo record);

	int updateByPrimaryKey(SysTalkManagerInfo record);

	List<SysTalkManagerInfo> selecTalkAccountInfoByAccount(PageBean pb);
	/**
	 * 添加聊天数据  没有就添加 有就修改
	 * @return
	 */
	int insertSysTalkManagerInfo(Integer userId,String account);
	/**
	 * 查询里聊天人数 
	 * @param account
	 * @return
	 */
	Map<String,Object> selectAccountTalkInfo(Account account);
}
