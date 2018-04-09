package com.moyou.moyouRms.init;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;

public class InitServiceUpdateUserOnlineType implements InitializingBean {
	@Resource
	private SysSessionManagerService sysSessionManagerService;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		sysSessionManagerService.updateAllUserOnlineType();
	}

}
