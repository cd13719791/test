package com.moyou.moyouRms.service.sysoperationlog;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysOperationLog.SysOperationLog;


public interface SysOperationLogService {
	/**
     * 插入系统日志
     * @param SysOperationLog
     * @return
     */
 public void saveSysOperationLog(SysOperationLog log);
 
/**
 * 查询日志
 * @param pb
 * @return
 */
 public List<SysOperationLog> querySysOperationLog(PageBean pb);
}
