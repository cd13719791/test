package com.moyou.moyouRms.service.sysoperationlog.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysOperationLog.SysOperationLogDao;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.sysOperationLog.SysOperationLog;
import com.moyou.moyouRms.service.sysoperationlog.SysOperationLogService;
@Service("sysOperationLogService")
public class SysOperationLogServiceImpl implements SysOperationLogService{
	@Resource
	private SysOperationLogDao sysOperationLogDao;
	@Override
	public void saveSysOperationLog(SysOperationLog log) {
		// TODO Auto-generated method stub
		sysOperationLogDao.insert(log);
	}
	@Override
	public List<SysOperationLog> querySysOperationLog(PageBean pb) {
		// TODO Auto-generated method stub
		return sysOperationLogDao.querySysOperationLog(pb);
	}

}
