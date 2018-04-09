package com.moyou.moyouRms.service.system.loginlog.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.system.loginlog.LoginLogDao;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.loginlog.LoginLog;
import com.moyou.moyouRms.service.system.loginlog.LoginLogService;
import com.moyou.moyouRms.util.UUIDUtil;
@Service("LoginLogService")
public class LoginLogServiceImp implements LoginLogService{

	@Autowired
	private LoginLogDao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageBean findByPageBean(LoginLog o, PageBean PageBean) {
		PageBean.setResults(dao.findByPageBean(o, PageBean));
		return PageBean;
	}

	@Override
	public void saveLoginLog(LoginLog o) {
		o.setId(UUIDUtil.getUUID());
		o.setLoginTime(new Date());
		dao.insert(o);
	}

	@Override
	public void deleteBatch(List<LoginLog> os) {
		dao.deleteBatch(os);
	}

}
