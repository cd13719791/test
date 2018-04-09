package com.moyou.moyouRms.service.talk;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.constants.enums.TalkPraiseSateEnum;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.talk.ComentTwo;
import com.moyou.moyouRms.model.talk.Rank;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkAndUserInfo;
import com.moyou.moyouRms.model.talk.TalkComment;
import com.moyou.moyouRms.model.talk.TalkInfoList;

public interface TalkService {
	/**
	 * 机器人发表说说
	 * 
	 * @param talk
	 * @return
	 */
	Integer insertTalk(Talk talk);

	/**
	 * 发表说说
	 * 
	 * @param talk
	 * @return
	 */
	Integer addTalk(Talk talk);

	/**
	 * 说说管理模块——初始化4个总量
	 * 
	 * @return
	 */
	TalkInfoList queryNewCountTalk();

	/**
	 * 说说模块初始化数据
	 * 
	 * @param pagebean
	 * @return
	 */
	List<Talk> queryTalkList(TalkAndUserInfo talkAndUserInfo);

	/**
	 * 根据说说ID查询说说详情以及说说的评论
	 * 
	 * @param talkId
	 * @return
	 */
	Talk queryTalkInfo(Integer talkId);

	/**
	 * 根据说说ID删除一条说说（假删除）
	 * 
	 * @param talkId
	 * @return
	 */
	Integer deleteTalk(Talk talk);

	/**
	 * 根据说说评论Id删除一条评论(假删除)
	 * 
	 * @param talkCommentId
	 * @return
	 */
	Integer deleteTalkComment(ComentTwo comentTwo);

	/**
	 * 根据用户Id 查询所有说说
	 * 
	 * @return
	 */
	List<Talk> queryTalkByUserId(PageBean pageBean);

	/**
	 * 根据用户Id 查询所有限制说说
	 * 
	 * @return
	 */
	List<Talk> queryTalkByUserId(PageBean pageBean, Integer state);

	/**
	 * 评论回复
	 * 
	 * @param talkComment
	 * @return
	 */
	Integer insertComment(TalkComment talkComment);

	/**
	 * 说说打赏后，打赏次数增加+1
	 * 
	 * @param talkId
	 */
	void updateTalkRewardTotalIncrease(Integer talkId);

	/**
	 * 查询真实用户发表的说说, 有日期和条数限制
	 * 
	 * @param startTime
	 * @param endTime
	 * @param limitCount
	 *            默认50
	 * @return
	 */
	List<Talk> queryRealUserTalkByDateLimit(String startTime, String endTime, int limitCount);

	/**
	 * 查询点赞状态
	 * 
	 * @param params
	 * @return
	 */
	TalkPraiseSateEnum queryPraiseState(Map<String, Object> params);

	/**
	 * 插入点赞
	 * 
	 * @param params
	 * @return
	 */
	boolean insertPraise(Map<String, Object> params);

	/**
	 * 改变点赞状态
	 * 
	 * @param params
	 * @return
	 */
	boolean updatePraise(Map<String, Object> params);

	Talk queryTalkDetails(Map<String, Object> params);

	/**
	 * 创建占位
	 * 
	 * @param userId
	 *            占位用户
	 * @param gold
	 *            占位金币
	 * @param city
	 *            占位城市
	 * @return
	 */
	List<Rank> createPlaceholder(int userId, int gold, String city);

	/**
	 * 批量删除说说
	 * 
	 * @param talk
	 * @return
	 */
	int updateTalkList(List<Talk> talk);

	/**
	 * 修改说说阅读量
	 * 
	 * @param talk
	 * @return
	 */
	int updateTalkReadCount(Talk talk);

	/**
	 * 说说模块初始化数据
	 * 
	 * @param pagebean
	 * @return
	 */
	List<Talk> queryTalkList(PageBean pb);

	/**
	 * 查询所有真实用户发布的说说
	 * 
	 * @param talk
	 * @return
	 */
	List<Talk> queryTalkReallyUser(Integer time);

	int insertReferenceDiaty(Talk talk);

	/**
	 * 查询发布说说数量 group by userId
	 * 
	 * @param i
	 * @return
	 */
	Integer queryTalkUserCount(int i);

	/**
	 * 删除首页推荐数据
	 * 
	 * @param talk
	 * @return
	 */
	int deleteTalkByReferenceIdAndReferenceType(Talk talk);
}
