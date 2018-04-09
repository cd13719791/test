package com.moyou.moyouRms.dao.gerrting;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.gerrting.GreetingAbstract;
import com.moyou.moyouRms.model.gerrting.GreetingUserSendLog;
import com.moyou.moyouRms.model.gerrting.PicList;
import com.moyou.moyouRms.model.statistics.MiniApps;

public interface GerrtingMapper {
	/**
	 * 祝福语记录
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	Integer insertBlessingLog(GreetingUserSendLog greetingUserSendLog);

	/**
	 * 插入一条祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	Integer insertBlessing(GreetingUserSendLog greetingUserSendLog);

	/**
	 * 查询最近的祝福语
	 * 
	 * @return
	 */
	List<GreetingAbstract> queryBlessing();

	/**
	 * 查询一条祝福语
	 * 
	 * @return
	 */
	GreetingUserSendLog queryBlessingDetails(Integer BlessingId);

	/**
	 * 卡片id查询图片集合
	 * 
	 * @param blessingId
	 * @return
	 */
	List<PicList> queryBlessingPicList(Integer blessingId);

	/**
	 * 初始化祝福语管理
	 * 
	 * @return
	 */
	List<GreetingAbstract> queryGreetingAbstractList(PageBean pageBean);
	/**
	 * 根据节日名查询节日封面图片和节日内容
	 * @param festivalTitle
	 * @return
	 */
	List<GreetingAbstract> queryGreetingAbstractInfo(PageBean pageBean);
	/**
	 * 添加祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	Integer insertGerrting(GreetingAbstract greetingAbstract);

	/**
	 * 添加封面图片集合
	 * 
	 * @param greetingAbstract
	 * @return
	 */
	Integer insertGerrtingGreetingAbstractList(List<PicList> greetingAbstract);
	/**
	 * 根据传入的封面图查询新增加封面图的ID
	 * @return
	 */
    List<Integer>  queryGreetingAbstracIdList(String festivalPicture);
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
    
   /*
    * 根据ID修改称呼和内容
    */
   int updateGreetingContent(GreetingAbstract greetingAbstract);
    /**
     * 根据节日ID查询节日内容
     * @param id
     * @return
     */
    List<GreetingAbstract> queryGreetingAbstract(Integer id);
    /**
	 *  根据祝福语ID查询祝福语详情用于举报
	 * 
	 * @return
	 */
	GreetingUserSendLog queryBlessingById(Integer BlessingId);

	/**
	 * 祝福卡昨日使用数
	 */
	MiniApps queryYesterdayGerrting();
	/**
	 * 祝福语总使用次数
	 * @return
	 */
	MiniApps queryCountGerrting();
	
}
