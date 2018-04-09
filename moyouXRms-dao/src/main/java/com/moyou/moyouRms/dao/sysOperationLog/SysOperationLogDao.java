package com.moyou.moyouRms.dao.sysOperationLog;
import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysOperationLog.SysOperationLog;
public interface SysOperationLogDao {
    int deleteByPrimaryKey(Integer logid);
    /**
     * 插入系统日志
     * @param SysOperationLog
     * @return
     */
    int insert(SysOperationLog record);

    int insertSelective(SysOperationLog record);

    SysOperationLog selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(SysOperationLog record);

    int updateByPrimaryKey(SysOperationLog record);
    
    /**
     * 查询日志
     * @param pb
     * @return
     */
	List<SysOperationLog> querySysOperationLog(PageBean pb);
}
