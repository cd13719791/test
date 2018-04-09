package com.moyou.moyouRms.dao.system.loginlog;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.loginlog.LoginLog;

public interface LoginLogDao {

	public List<LoginLog> findByPageBean(@Param("param")LoginLog o,PageBean PageBean);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
