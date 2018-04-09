package com.moyou.moyouRms.service.system.loginlog;
import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.loginlog.LoginLog;

public interface LoginLogService {

	public PageBean findByPageBean(LoginLog o,PageBean page);	
	
	public void saveLoginLog(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
