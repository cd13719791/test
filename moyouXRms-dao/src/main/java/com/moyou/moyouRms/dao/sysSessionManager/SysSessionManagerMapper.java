package com.moyou.moyouRms.dao.sysSessionManager;

import java.util.List;

import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;

public interface SysSessionManagerMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(SysSessionManager record);

    int insertSelective(SysSessionManager record);

    SysSessionManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSessionManager record);

    int updateByPrimaryKey(SysSessionManager record);

    SysSessionManager selectByLoginName(String id);

    int updateByLoginName(SysSessionManager sysSessionManager);

    List<SysSessionManager> selectToDayDataByLoginName(String id);

    int updateOnlineTypeByLoginName(SysSessionManager record);

    int updateAllUserOnlineType();

    int updateSessionSetTime(String longinName);

    /**
     * 查询需要结算操作时间的sessionManager
     * 
     * @return
     */
    List<SysSessionManager> selectSessionNeedBalance();
}
