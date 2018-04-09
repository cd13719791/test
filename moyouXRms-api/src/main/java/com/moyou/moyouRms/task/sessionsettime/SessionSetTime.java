package com.moyou.moyouRms.task.sessionsettime;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;
import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;

@Component
public class SessionSetTime {

	@Resource
	private SysSessionManagerService sysSessionManageerService;

	@Scheduled(cron="0 0/10 * * * ?")
	    public void initSessionSetTime() {
//		  System.out.println("=========【更新最后操作时间】=========");
	      List<SysSessionManager> list=  sysSessionManageerService.selectSessionNeedBalance();
	      for (int i = 0; i < list.size(); i++) {
//	    	  System.out.println("=========【更新最后操作时间】=========>>>>>"+list.get(i).getLoginName());
//	    	 sysSessionManageerService.updateOnlineTypeByLoginName(list.get(i));
	    	 int onlineTime=Integer.valueOf((list.get(i).getSetTime().getTime()-list.get(i).getCreateTime().getTime())/60/1000+"");
	    	 list.get(i).setOnlineTime(list.get(i).getOnlineTime()+onlineTime);
	    	 list.get(i).setOnlineType(SysSessionManager.OFFLINE);
//	    	 sysSessionManageerService.updateByPrimaryKeySelective(list.get(i));
		}
//	      System.out.println("=========【更新最后操作时间End】=========");
	    }
}
