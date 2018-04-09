package com.moyou.moyouRms.dao.talk;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.talk.ComentTwo;
import com.moyou.moyouRms.model.talk.CommentList;
import com.moyou.moyouRms.model.talk.Rank;
import com.moyou.moyouRms.model.talk.ResourcePic;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkComment;
import com.moyou.moyouRms.model.talk.TalkResource;

public interface TalkMapper {

	/**
	 * 发表说说
	 * 
	 * @param talk
	 * @return
	 */
	Integer insertTalk(Talk talk);

	/**
	 * 插入说说图片
	 * 
	 * @param list
	 * @return
	 */
	Integer insertTalkResouce(List<TalkResource> list);

	/**
	 * 用户说说统计+1
	 * 
	 * @param id
	 * @return
	 */
	Integer updateTalkAddOne(Integer userId);

	Integer updateTalkAddWithCount(@Param("count") Integer count, @Param("userId") Integer userId);

	/**
	 * 说说管理模块——初始化4个总量——今日新增总数
	 * 
	 * @return
	 */
	Integer queryNewCountTalk();

	/**
	 * 说说管理模块——初始化4个总量——说说总数
	 * 
	 * @return
	 */
	Integer queryCountTalk();

	/**
	 * 说说管理模块——初始化4个总量——说说图片总数
	 * 
	 * @return
	 */
	Integer queryCountPicTalk();

	Integer queryCountTalkPic();

	/**
	 * 说说管理模块——初始化4个总量——说说视频总数
	 * 
	 * @return
	 */
	Integer queryCountVideoTalk();

	/**
	 * 说说模块初始化数据
	 * 
	 * @param pagebean
	 * @return
	 */
	List<Talk> queryTalkList(PageBean pagebean);

	/**
	 * 根据说说ID查询说说详情
	 * 
	 * @param talkId
	 * @return
	 */
	Talk queryTalkInfo(Integer talkId);

	/**
	 * 根据说说ID查询说说的评论
	 * 
	 * @param talkId
	 * @return
	 */
	List<ComentTwo> comentList(Integer talkId);

	/**
	 * 根据说说ID，说说评论量在删除的时候减一
	 * 
	 * @param talkId
	 * @return
	 */
	Integer TalkCommentJian1(Integer talkId);

	/**
	 * 根据说说ID，说说评论量在删除的时候加一
	 * 
	 * @param talkId
	 * @return
	 */
	Integer TalkCommentjiayi1(Integer talkId);

	/**
	 * 根据说说ID删除一条说说（假删除）
	 * 
	 * @param talkId
	 * @return
	 */
	Integer deleteTalk(Integer talkId);

	/**
	 * 根据说说评论Id删除一条评论(假删除)
	 * 
	 * @param talkCommentId
	 * @return
	 */
	Integer deleteTalkComment(Integer talkCommentId);

	/**
	 * 根据用户ID 查询说说
	 * 
	 * @param talkCommentId
	 * @return
	 */
	List<Talk> queryTalkByUserId(PageBean userId);

	/**
	 * 评论回复
	 * 
	 * @param talkComment
	 * @return
	 */
	Integer insertComment(TalkComment talkComment);

	Integer insertTalkDay(Integer userId);

	Integer updateTalkDay(Integer userId);

	Integer queryTalkDay(Integer userId);

	List<Talk> queryDelTalkByUserId(PageBean i, Integer state);

	List<Talk> queryRealUserTalkByDateLimit(Map<String, Object> params);

	Integer updateTalkRewardTotalIncrease(Integer talkId);

	/**
	 * 根据说说id查询图片
	 * 
	 * @param talkId
	 * @return
	 */
	List<ResourcePic> queryPicbyTalkId(Integer talkId);

	/**
	 * 查询关注的状态
	 * 
	 * @param params
	 * @return
	 */
	String queryFriendState(Map<String, Object> params);

	/**
	 * 点赞的状态
	 * 
	 * @param params
	 * @return
	 */
	Boolean queryPraiseSate(Map<String, Object> params);

	/**
	 * 返回的两条评论
	 * 
	 * @param talkId
	 * @return
	 */
	List<ComentTwo> queryComentTwo(Integer talkId);

	/**
	 * 说说详情
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryTalkDetails(Integer talkId);

	/**
	 * 评论的列表
	 * 
	 * @param PageBean
	 * @return
	 */
	List<CommentList> queryCommenList(PageBean PageBean);

	Map<String, Object> queryTalkDateInfo(Integer talkId);

	Integer updateTalkState(Integer talkId);

	Integer updateCommentAddOne(Integer talkId);

	Integer updateTalkDelOne(Integer userId);

	Integer queryUserIdByTalkId(Integer talkId);

	Integer updateDelGold(Rank rank);

	List<Rank> queryRank(List<String> ids);

	Integer insertRankUser(Rank rank);

	/**
	 * 10 个主键
	 * 
	 * @return
	 */
	List<String> queryRankIds();

	Integer queryFollowTalk(List<Integer> followersUserId);

	List<Map<String, Object>> queryFollowAvatar(Integer userId);

	Integer queryUserGold(Integer userId);

	Integer querySign(Integer userId);

	/**
	 * 改变转发说说的状态
	 * 
	 * @return
	 */
	Integer updateRelateTalkState(Integer talkId);

	Integer queryTalkRelateState(Integer talkId);

	Integer insertUserFundLog(Rank rank);

	/**
	 * 批量删除说说
	 * 
	 * @param talk
	 * @return
	 */
	int updateTalkList(List<Talk> talk);

	int updateTalkReadCount(Talk talk);

	List<Talk> queryTalkReallyUser(Integer time);

	/**
	 * 推荐首页故事
	 * 
	 * @param talk
	 * @return
	 */
	public int insertReferenceDiary(Talk talk);

	int deleteReferenceDiary(Talk talk);

	/**
	 * 查询统计所有数据
	 * 
	 * @return
	 */
	Map<String, Object> queryStatistAllCount();

	/**
	 * 查询详细统计数据
	 * 
	 * @return
	 */
	Map<String, Object> queryStatistCount();

	Integer selectTalkIsExistByTalkId(int talkId);

	int queryCountTalkByUserId(@Param("userId") Integer userId);

	Integer queryTalkUserCount(@Param("time") Integer time);

	/**
	 * 查询说说 groupBy user
	 * 
	 * @param time
	 * @return
	 */
	List<Talk> queryTalkGroupByUser(@Param("time") Integer time);

	int deleteTalkByReferenceIdAndReferenceType(Talk talk);

	Talk selectTalkByTalkId(@Param("talkId")Integer talkId);

}
