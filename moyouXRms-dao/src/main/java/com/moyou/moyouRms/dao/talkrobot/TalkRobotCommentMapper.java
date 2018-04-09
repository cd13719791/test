package com.moyou.moyouRms.dao.talkrobot;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.statistics.Robot;
import com.moyou.moyouRms.model.talkrobot.TalkRobotComment;

public interface TalkRobotCommentMapper {
	/**
	 * 添加一条评论模板
	 * 
	 * @param record
	 * @return
	 */
	int insert(TalkRobotComment record);

	int insertSelective(TalkRobotComment record);

	/**
	 * 查询评论模板集合
	 * 
	 * @return
	 */
	List<TalkRobotComment> queryTalkRobotCommentList(PageBean pageBean);
	List<TalkRobotComment> queryAllTalkRobotCommentList();
	void updateTalkRobotCommentUseCount(int talkRobotCommentId);
/**
 * 查询评论总数
 * @return
 */
	int queryCountComment();
	/**
	 * 根据评论ID修改评论状态
	 * @param record
	 * @return
	 */
	int updateTalkRobotCommentState(TalkRobotComment record);
	Robot queryTalkComment();
}