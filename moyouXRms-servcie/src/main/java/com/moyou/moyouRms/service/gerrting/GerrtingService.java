package com.moyou.moyouRms.service.gerrting;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.gerrting.GreetingAbstract;
import com.moyou.moyouRms.model.gerrting.GreetingUserSendLog;
import com.moyou.moyouRms.model.gerrting.PicList;
import com.moyou.moyouRms.model.user.Page;

public interface GerrtingService {
	/**
	 * 插入一条祝福语记录
	 * @param greetingUserSendLog
	 * @return
	 */
	Map<String, Object> insertBlessingLog(GreetingUserSendLog greetingUserSendLog);
	/**
	 * 插入一条祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	Integer insertBlessing(GreetingUserSendLog greetingUserSendLog);
	/**
	 * 祝福语集合
	 * @return
	 */
	List<GreetingAbstract> queryBlessing();
	
	/**
	 * 查询一条祝福语
	 * @return
	 */
	GreetingUserSendLog queryBlessingDetails(GreetingAbstract greetingAbstract);
	
	/**
	 * 卡片id查询图片集合
	 * @param blessingId
	 * @return
	 */
	List<PicList> queryBlessingPicList(Integer blessingId);
	/**
	 * 初始化祝福语管理
	 * @return
	 */
	List<GreetingAbstract> queryGreetingAbstractList(Page page);
	/**
	 * 添加祝福语
	 * @param gerrting
	 * @return
	 * @throws ParseException 
	 */
	int AddGreeting(GreetingUserSendLog greetingUserSendLog);
	 /**
     * 根据节日ID删除封面图片
     * @return
     */
    int deleteGreeting(GreetingAbstract greetingAbstract);
    /**
     * 根据内容ID删除内容
     * @param greetingAbstract
     * @return
     */
    int deleteGreetingContent(GreetingAbstract greetingAbstract);
    /**
     * 根据状态禁用或者启用
     * @param state
     * @return
     */
    int updateGreetingState(Integer state);
    /**
	 * 根据节日名查询节日封面图片和节日内容
	 * @param festivalTitle
	 * @return
	 */
    List<GreetingAbstract> queryGreetingAbstractInfo(Page page);
    /**
     * 根据节日ID查询节日内容
     * @param id
     * @return
     */
    List<GreetingAbstract> queryGreetingAbstract(Integer id);
    /*
     * 根据ID修改称呼和内容
     */
    int updateGreetingContent(GreetingAbstract greetingAbstract);
    /**
  	 *  根据祝福语ID查询祝福语详情用于举报
  	 * 
  	 * @return
  	 */
  	GreetingUserSendLog queryBlessingById(Integer BlessingId);
}
