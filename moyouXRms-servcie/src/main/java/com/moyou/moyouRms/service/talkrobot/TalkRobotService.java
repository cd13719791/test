package com.moyou.moyouRms.service.talkrobot;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.model.talkrobot.TalkRobotComment;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.response.ApiResult;

public interface TalkRobotService {
	/**
	 * 添加一条评论模板
	 * @param record
	 * @return
	 */
	ApiResult insert(TalkRobotComment record);
	/**
	 * 查询评论模板集合
	 * 
	 * @return
	 */
	List<TalkRobotComment> queryTalkRobotCommentList(Page page);
	/**
	 * 查询所有可用的评论，由于目前不会太多的机器人评论，查询所有，后期看情况优化 并去除所有在冷却时间的说说
	 * @return
	 */
	List<TalkRobotComment> queryAllTalkRobotCommentList();
	/**
	 * 累加评论的使用次数
	 * @param talkRobotCommentId
	 */
	void updateTalkRobotCommentUseCount(int talkRobotCommentId);
	/**
	 * 根据评论ID修改评论状态
	 * @param record
	 * @return
	 */
	ApiResult updateTalkRobotCommentState(TalkRobotComment record);
	
	/**
	 * 查询说说集合带图片资源
	 * @return
	 */
	List<TalkRobot> queryTalkRobotPageList(PageBean page);
	/**
	 * 查询说说集合不带图片资源
	 * @return
	 */
	List<TalkRobot> queryTalkRobotList(PageBean page);
	/**
	 * 查询说说详情
	 * 
	 * @return
	 */
	TalkRobot queryTalkRobotId(int page);
	/**
	 * 添加一条说说模板
	 * @param record
	 * @return
	 */
	int insertTalkRobot(TalkRobot record);
	/**
	 * 根据说说ID修改状态
	 * @param record
	 * @return
	 */
	int updateTalkRobotState(TalkRobot record);
	/**
	 * 查询未发布，发布总数统计
	 * @return
	 */
	  Map<String,Long> queryNewTalkRobotAndAllTalkRobotCount();
	  /**
	   * 修改数据的发布状态以及发布时间
	   * @param record
	   */
	  void updateTalkPublishStatus(TalkRobot record);
	  TalkRobot selectByPrimaryKey(int id);
	  /**
	   * 从数据库中 随机拿说说来发布
	   * @param pb
	   * @return
	   */
	  List<TalkRobot> queryTalkRobotListForAutoPublish(PageBean pb);
 }
