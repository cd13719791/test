package com.moyou.moyouRms.service.syssessionmanager;

import java.util.List;

import org.apache.shiro.session.Session;

import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;

public interface SysSessionManagerService {
	int deleteByPrimaryKey(Integer id);

	int insert(SysSessionManager record);

	int insertSelective(SysSessionManager record);

	SysSessionManager selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysSessionManager record);

	int updateByPrimaryKey(SysSessionManager record);

	/**
	 * 修改账号登录时间
	 * 
	 * @param session
	 * @return
	 */
	int updateByLoginName(Session session);

	/**
	 * 修改账号登录时间
	 * 
	 * @param session
	 * @return
	 */
	int updateByLoginName(SysSessionManager session);

	/**
	 * 修改在线状态
	 * 
	 * @param session
	 * @return
	 */
	int updateOnlineTypeByLoginName(SysSessionManager record);

	/**
	 * 根据账号选择session信息
	 * 
	 * @param loginName
	 * @return
	 */
	SysSessionManager selectByLoginName(String loginName);

	int updateAllUserOnlineType();

	SysSessionManager selectToDayDataByLoginName(String id);

	/**
	 * 更新最后操作时间
	 * 
	 * @param longinName
	 * @return
	 */
	int updateSessionSetTime(String longinName);
	/**
	 * 查看需要结算的
	 * @return
	 */
	List<SysSessionManager> selectSessionNeedBalance();
}