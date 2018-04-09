package com.moyou.moyouRms.init;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.moyou.moyouRms.service.msgbar.MsgBarService;

/**
 * spring-bean 注解执行初始化
 * 
 * @author Administrator
 *
 */
public class InitReflashMsgBar implements InitializingBean {
	@Resource
	private MsgBarService msgBarService;

	public void init() {
		Logger log = LoggerFactory.getLogger(this.getClass());
		log.info("*****************开始推送初始化*******************");
		log.info("*****************开始推送初始化*******************");
		log.info("*****************开始推送初始化*******************");
		log.info("*****************开始推送初始化*******************");
		log.info("*****************开始推送初始化*******************");
		msgBarService.selectUnPushMsgBar().forEach(msgBarService::replayMsgBarList);
		log.info("*******************END**********************");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		init();
	}
}
