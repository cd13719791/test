package com.moyou.moyouRms.service.talk.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.constants.enums.TalkPraiseSateEnum;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.dao.talk.TalkCommentMapper;
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.dao.talk.TalkPraiseMapper;
import com.moyou.moyouRms.dao.talk.TalkResourceMapper;
import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.talk.ComentTwo;
import com.moyou.moyouRms.model.talk.Rank;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkAndUserInfo;
import com.moyou.moyouRms.model.talk.TalkComment;
import com.moyou.moyouRms.model.talk.TalkInfoList;
import com.moyou.moyouRms.model.talk.TalkPraise;
import com.moyou.moyouRms.model.talk.TalkResource;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.userdynamic.UserDynamic;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.userdynamic.UserDynamicService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.DistanceUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StreamUtils;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

@Service
public class TalkServiceImpl implements TalkService {

	@Resource
	private TalkMapper talkMapper;
	@Resource
	private TalkResourceMapper talkResourceMapper;
	@Resource
	private UserCountMapper userCountMapper;
	@Resource
	private TalkPraiseMapper praiseMapper;
	@Resource
	private TalkCommentMapper talkCommentMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private UserNotificationMapper userNotificationMapper;
	@Resource
	JpushService jpushService;
	@Resource
	UserDynamicService userDynamicService;
	private static final Logger LOG = LoggerFactory.getLogger(TalkServiceImpl.class);

	/**
	 * 发表说说
	 */
	@Override
	public Integer insertTalk(Talk talk) {

		talk.setUpdateTime(new Date());
		// talk.setCreateTime(now);
		talk.setState(0);
		talk.setSupportTotal(0);
		talk.setRewardTotal(0);
		talk.setCommentTotal(0);
		// talk.setMediaType(mediaType);
		talkMapper.insertTalk(talk);
		userDynamicService.insertSelective(new UserDynamic(talk.getCreatorId(), talk.getId(),
				UserDynamic.TALK, talk.getCreateTime()));
		List<TalkResource> lisPics = talk.getTalkResource();
		if (!CollectionUtils.isEmpty(lisPics)) {
			for (TalkResource talkResource : lisPics) {
				talkResource.setUserId(Integer.valueOf(talk.getCreatorId()));
				talkResource.setTalkId(talk.getId());
				talkResource.setMediaTypes(Integer.valueOf(talkResource.getMediaType() + ""));
				talkResource.setCreateTime(talk.getCreateTime());
				talkResource.setUrl(talkResource.getUrl());
			}
			talkMapper.insertTalkResouce(talk.getTalkResource());// 插入图片
		}
		int count = talkMapper.queryCountTalkByUserId(Integer.valueOf(talk.getCreatorId()));
		talkMapper.updateTalkAddWithCount(count, Integer.valueOf(talk.getCreatorId()));// 用户说说统计+1
		Integer map = talkMapper.queryTalkDay(talk.getCreatorId());
		if (map == null) {
			talkMapper.insertTalkDay(talk.getCreatorId());
		} else {
			talkMapper.updateTalkDay(talk.getCreatorId());
		}

		return null;
	}

	/**
	 * 评论回复
	 */
	@Override
	public Integer insertComment(TalkComment talkComment) {
		Integer talkId = talkComment.getTalkId();
		List<TalkComment> list = talkCommentMapper.queryTalkCommentListByTalkId(talkId);
		if ((int) list.stream().filter(s -> s.getContent().equals(talkComment.getContent()))
				.count() > 0) {// 有相同评论
			return 0;
		}
		;
		talkMapper.TalkCommentjiayi1(talkId);
		talkMapper.insertComment(talkComment);
		Talk talk = talkMapper.queryTalkInfo(talkId);
		int result = 0;
		try {
			UserNotification userNotification = userNotificationMapper
					.queryUserNotificationByTalkCommentId(talkComment.getId());
			userNotification.setActionType(1);
			userNotification.setBusinessId(talkId);
			userNotification.setBusinessType(1);
			userNotification.setCommentRelateId(talkComment.getId());
			userNotification.setCommentTextContent(talkComment.getContent());
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_COMMENT_TALK);
			userNotification.setSendUserId(talkComment.getUserId());
			userNotification.setBusinessTextContent(talk.getContent());
			userNotification.setPic(talkResourceMapper.queryTalkResource(talkId).get(0).getUrl());
			jpushService.sendMessgePushCustomMsgToDB(userNotification);
			result = 1;
		} catch (Exception e) {
			// System.out.println("说说评论推送失败！！！！评论======>"+talkComment.getId());
		}

		return result;
	}

	/**
	 * 说说管理模块——初始化4个总量
	 * 
	 * @return
	 */
	@Override
	public TalkInfoList queryNewCountTalk() {
		TalkInfoList talkInfoList = new TalkInfoList();
		talkInfoList.setNewTalkCount(talkMapper.queryNewCountTalk());
		talkInfoList.setCountTalk(talkMapper.queryCountTalk());
		talkInfoList.setCountPicTalk(talkMapper.queryCountPicTalk());
		talkInfoList.setCountVideoTalk(talkMapper.queryCountVideoTalk());
		return talkInfoList;
	}

	/**
	 * 说说模块初始化数据
	 * 
	 * @param pagebean
	 * @return
	 */
	@Override
	public List<Talk> queryTalkList(TalkAndUserInfo talkAndUserInfo) {
		PageBean pagebean = new PageBean();
		pagebean.setPageNumber(talkAndUserInfo.getPageNumber());
		pagebean.setPageSize(talkAndUserInfo.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("city", talkAndUserInfo.getCity());
		map.put("mediaType", talkAndUserInfo.getMediaType());
		map.put("startSupport", talkAndUserInfo.getStartSupport());
		map.put("endSupport", talkAndUserInfo.getEndSupport());
		map.put("startcomment", talkAndUserInfo.getStartcomment());
		map.put("endcomment", talkAndUserInfo.getEndcomment());
		map.put("starttime", talkAndUserInfo.getStarttime());
		map.put("endtime", talkAndUserInfo.getEndtime());
		map.put("nickname", talkAndUserInfo.getNickname());
		map.put("content", talkAndUserInfo.getContent());
		map.put("type", talkAndUserInfo.getType());
		map.put("relateTalkId", talkAndUserInfo.getRelateTalkId());
		pagebean.setConditions(map);
		List<Talk> results = new ArrayList<Talk>();
		results = talkMapper.queryTalkList(pagebean);
		talkAndUserInfo.setTotal(pagebean.getTotalRecord());
		talkAndUserInfo.setResults(results);
		for (Talk talk : results) {
			Integer talkId = talk.getTalkId();
			List<TalkResource> resource = talkResourceMapper.queryTalkResource(talkId);
			talk.setCountCommentTotal(DistanceUtil.handleDataInfo(talk.getCommentTotal()));
			talk.setCountsupportTotal(DistanceUtil.handleDataInfo(talk.getSupportTotal()));
			talk.setCountRewardTotal(DistanceUtil.handleDataInfo(talk.getRewardTotal()));
			talk.setTalkResource(resource);
		}
		return results;
	}

	/**
	 * 根据说说ID查询说说详情以及说说的评论
	 * 
	 * @param talkId
	 * @return
	 */
	@Override
	public Talk queryTalkInfo(Integer talkId) {
		Talk talk = talkMapper.queryTalkInfo(talkId);
		List<TalkResource> resource = talkResourceMapper.queryTalkResource(talkId);
		List<ComentTwo> comentList = talkMapper.comentList(talkId);
		talk.setTalkResource(resource);
		talk.setComentList(comentList);
		talk.setCountsupportTotal(DistanceUtil.handleDataInfo(talk.getSupportTotal()));
		talk.setCountRewardTotal(DistanceUtil.handleDataInfo(talk.getRewardTotal()));
		talk.setCountReadTotal(DistanceUtil.handleDataInfo(talk.getReadTotal()));
		// 计算用户的年龄
		talk.setAge(DateTimeUtil.getPersonAgeByBirthDate(talk.getBirthday()));
		return talk;
	}

	/**
	 * 根据说说ID删除一条说说（假删除）
	 * 
	 * @param talkId
	 * @return
	 */
	@Override
	public Integer deleteTalk(Talk talk) {
		Integer talkId = talk.getTalkId();
		Integer creatorId = talk.getCreatorId();

		if (!StringUtils.isEmpty(talkMapper.selectTalkByTalkId(talkId).getId())) {
			// 用户说说总数减一
			userCountMapper.updateUserCountTalkJian1(creatorId);
			talkMapper.deleteTalk(talkId);
			/**
			 * 删除动态数据
			 */
			userDynamicService.deleteByDataIdAnDType(new UserDynamic(talkId, UserDynamic.TALK));
		}
		return RESPONSE.SUCCESS;
	}

	/**
	 * 根据说说评论Id删除一条评论(假删除)
	 * 
	 * @param talkCommentId
	 * @return
	 */
	@Override
	public Integer deleteTalkComment(ComentTwo comentTwo) {
		Integer talkCommentId = comentTwo.getTalkCommentId();
		Integer talkId = comentTwo.getTalkId();
		talkMapper.TalkCommentJian1(talkId);
		return talkMapper.deleteTalkComment(talkCommentId);
	}

	@Override
	public List<Talk> queryTalkByUserId(PageBean userId) {
		List<Talk> list = talkMapper.queryTalkByUserId(userId);
		list.forEach(talk -> {
			List<TalkResource> resources = talkResourceMapper.queryTalkResource(talk.getId());
			talk.setTalkResource(resources);

		});
		return list;
	}

	@Override
	public List<Talk> queryTalkByUserId(PageBean pageBean, Integer state) {
		// TODO Auto-generated method stub
		return talkMapper.queryDelTalkByUserId(pageBean, state);
	}

	@Override
	public void updateTalkRewardTotalIncrease(Integer talkId) {
		talkMapper.updateTalkRewardTotalIncrease(talkId);
	}

	@Override
	public List<Talk> queryRealUserTalkByDateLimit(String startTime, String endTime, int limitCount) {
		if (limitCount > 1000) {
			limitCount = 50;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("limitCount", limitCount);
		List<Talk> talkList = talkMapper.queryRealUserTalkByDateLimit(params);
		for (Talk talk : talkList) {
			List<String> userIdList = new ArrayList<String>();
			List<String> commentTextList = new ArrayList<String>();
			Integer talkId = talk.getId();
			talkCommentMapper.queryTalkCommentListByTalkId(talkId).forEach(commentList -> {
				commentTextList.add(commentList.getContent());
				userIdList.add(commentList.getUserId() + "");
			});
			talk.setUserIdList(userIdList);
			talk.setCommentText(commentTextList);
		}
		return talkList;
	}

	@Override
	public TalkPraiseSateEnum queryPraiseState(Map<String, Object> params) {
		Integer state = praiseMapper.queryPraiseState(params);
		return TalkPraiseSateEnum.getByValue(state);
	}

	/**
	 * 插入点赞
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public boolean insertPraise(Map<String, Object> params) {
		TalkPraise talkPraise = new TalkPraise();
		talkPraise.setTalkId((Integer) params.get("talkId"));
		talkPraise.setUserId((Integer) params.get("userId"));
		praiseMapper.insert(talkPraise);
		praiseMapper.updatePraiseAddOne(talkPraise.getTalkId());// 点赞统计+1
		try {
			UserNotification userNotification = userNotificationMapper
					.queryUserNotificationByTalkId(talkPraise.getTalkId());
			userNotification.setActionType(2);
			userNotification.setBusinessId(talkPraise.getTalkId());
			userNotification.setBusinessType(1);
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_PRAISE_TALK);
			userNotification.setSendUserId(talkPraise.getUserId());
			jpushService.sendMessgePushCustomMsgToDB(userNotification);
		} catch (Exception e) {
			// System.out.println("说说点赞推送失败！！！！说说Id======>"+talkPraise.getTalkId());
			LOG.error(e.getMessage());
		}
		return true;
	}

	/**
	 * 改变点赞状态
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public boolean updatePraise(Map<String, Object> params) {
		TalkPraise talkPraise = JsonUtil.toObject(params, TalkPraise.class);
		boolean state = talkPraise.getState();
		talkPraise.setState(!state);
		praiseMapper.updatePraise(talkPraise);
		if (state == true) {
			praiseMapper.updatePraiseDeleOne(talkPraise.getTalkId());// 点赞统计-1
		} else {
			praiseMapper.updatePraiseAddOne(talkPraise.getTalkId());// 点赞统计+1
		}
		return !state;
	}

	/**
	 * H5 查看分享后的说说
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public Talk queryTalkDetails(Map<String, Object> params) {
		int talkId = Integer.valueOf(params.get("talkId") + "");
		// 查询说说基本信息
		Talk talk = talkMapper.queryTalkInfo(talkId);
		List<TalkPraise> talkPraiseList = praiseMapper.queryPraiseByTalkId(talkId);
		talk.setTalkPraiseList(talkPraiseList == null ? Collections.emptyList() : talkPraiseList);
		// 添加评论
		List<TalkComment> talkCommentList = talkCommentMapper.queryTalkCommentListByTalkId(talkId);
		talkCommentList.forEach(talkCommentChild -> {
			if (talkCommentChild.getParentId() != null) {
				talkCommentList.forEach(talkCommentParent -> {
					if (talkCommentParent.getId() == talkCommentChild.getParentId()) {
						List<TalkComment> list = talkCommentParent.getTalkCommentChildList();
						list.add(talkCommentChild);
						talkCommentParent.setTalkCommentChildList(list);
						list.clear();
					}
				});
			}
		});
		talk.setTalkCommentList(talkCommentList);
		// 添加图片
		List<TalkResource> talkResource = talkResourceMapper.queryTalkResource(talkId);
		talk.setTalkResource(talkResource);
		// 添加用户信息
		UserInfo talkUserInfo = new UserInfo();
		talkUserInfo.setUserId(talk.getUserId());
		talk.setUserInfo(userInfoMapper.selectUserInfoByUserId(talkUserInfo));
		return talk;
	}

	@Override
	public List<Rank> createPlaceholder(int userId, int gold, String city) {
		Rank rank = new Rank();
		rank.setUserId(userId);
		rank.setGold(gold);
		rank.setCity(city);
		List<String> rankCitys = fill(rank.getCity());
		String rankCity = CONSTANT.RANK_PREFIX.concat(rank.getCity());
		Set<String> primaryKeyInRedis = new HashSet<>(redisTemplate.opsForValue().multiGet(
				rankCitys));
		List<Rank> ranks = queryRank(rank.getCity());
		if (ranks.stream().filter(ran -> ran.getUserId().equals(rank.getUserId())).count() == 1) {// 用户已经占位
			return Collections.emptyList();
		}

		if (primaryKeyInRedis.contains(null)) {
			/*
			 * keyInRedis -> rank 拼接具体的城市信息和 1 到 10 的标识来表示存储在 Redis 中的键
			 * valueInRedis -> keyInRedis 对应的 value，是生成的 UUID，同时当做 primaryKey
			 * 存储数据库
			 */
			Stream<String> rankCitysStream = rankCitys.stream();
			String valueInRedis = UUIDUtil.getUUID(), keyInRedis = primaryKeyInRedis.size() == 1 ? StreamUtils
					.findFirst(rankCitysStream) : StreamUtils.findFirst(rankCitysStream
					.filter(key -> !redisTemplate.keys(rankCity + "*").contains(key)));

			rank.setId(valueInRedis);
			placeholderOperationData(keyInRedis, valueInRedis, rank);

			ranks.add(rank);
			ranks.sort(Comparator.comparingInt(Rank::getGold).reversed());
			return ranks;
		}
		return null;
	}

	private void placeholderOperationData(String keyInRedis, String valueInRedis, Rank rank) {
		// redisTemplate.opsForValue().set(keyInRedis, valueInRedis, 5,
		// TimeUnit.SECONDS);// 存在5秒
		redisTemplate.opsForValue().set(keyInRedis, valueInRedis, 24, TimeUnit.HOURS);// 存在24小时
		rank.setSearchUserid(rank.getUserId());
		rank.setTradeNumber(TradeNumberUtil.getTradeNumber());
		rank.setIp("127.0.0.1");
		talkMapper.insertRankUser(rank);
		// talkMapper.updateDelGold(rank);
		// talkMapper.insertUserFundLog(rank);
	}

	/**
	 * 占位的10个人
	 */
	public List<Rank> queryRank(String city) {
		List<String> rankIds = redisTemplate.opsForValue().multiGet(fill(city));
		return talkMapper.queryRank(rankIds);
	}

	/**
	 * 生成10个占位空间
	 * 
	 * @param city
	 * @return
	 */
	private List<String> fill(String city) {
		String rankCity = CONSTANT.RANK_PREFIX.concat(city);
		List<String> strings = Lists.newArrayListWithCapacity(10);
		for (int i = 1; i < 11; i++) {
			strings.add(rankCity + i);
		}
		return strings;
	}

	@Override
	public Integer addTalk(Talk talk) {
		Integer userId = null;
		String city = "";
		Double latitude = 0.0;
		Double longitude = 0.0;
		Integer gender = talk.getGender();
		if (gender == 1) {
			List<UserInfo> userInfo = userInfoMapper.querySexWoman();
			if (userInfo == null) {
				userInfo = new ArrayList<UserInfo>();
			}
			Collections.shuffle(userInfo);// 随机集合
			userId = userInfo.get(0).getUserId();
			city = userInfo.get(0).getCity();
			latitude = userInfo.get(0).getLatitude();
			longitude = userInfo.get(0).getLongitude();
		} else {
			List<UserInfo> userInfo = userInfoMapper.queryMan();
			if (userInfo == null) {
				userInfo = new ArrayList<UserInfo>();
			}
			Collections.shuffle(userInfo);// 随机集合
			userId = userInfo.get(0).getUserId();
			city = userInfo.get(0).getCity();
			latitude = userInfo.get(0).getLatitude();
			longitude = userInfo.get(0).getLongitude();
		}
		Date now = new Date();
		talk.setUpdateTime(now);
		talk.setCreateTime(now);
		talk.setState(0);
		talk.setSupportTotal(0);
		talk.setRewardTotal(0);
		talk.setCommentTotal(0);
		talk.setRelateState(0);
		talk.setCreatorId(userId);
		talk.setRelateTalkId(0);
		talk.setCity(city);
		talk.setLatitude(latitude);
		talk.setMediaTypes(Integer.valueOf(talk.getMediaType() + ""));
		talk.setLongitude(longitude);
		talkMapper.insertTalk(talk);
		List<TalkResource> lisPics = talk.getTalkResource();
		if (!CollectionUtils.isEmpty(lisPics)) {
			for (TalkResource talkResource : lisPics) {
				talkResource.setUserId(Integer.valueOf(talk.getCreatorId()));
				talkResource.setTalkId(talk.getId());
				talkResource.setMediaType(talk.getMediaTypes());
				talkResource.setCreateTime(now);
				talkResource.setUrl(talkResource.getUrl());
			}
			talkMapper.insertTalkResouce(talk.getTalkResource());// 插入图片
		}
		talkMapper.updateTalkAddOne(Integer.valueOf(talk.getCreatorId()));// 用户说说统计+1
		return null;
	}

	/**
	 * 批量删除说说
	 * 
	 * @param talk
	 * @return
	 */
	@Override
	public int updateTalkList(List<Talk> talk) {
		return talkMapper.updateTalkList(talk);
	}

	@Override
	public int updateTalkReadCount(Talk talk) {
		// TODO Auto-generated method stub
		return talkMapper.updateTalkReadCount(talk);
	}

	@Override
	public List<Talk> queryTalkList(PageBean pb) {
		// TODO Auto-generated method stub
		return talkMapper.queryTalkList(pb);
	}

	@Override
	public List<Talk> queryTalkReallyUser(Integer talk) {
		// TODO Auto-generated method stub
		return talkMapper.queryTalkReallyUser(talk);
	}

	@Override
	public int insertReferenceDiaty(Talk talk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer queryTalkUserCount(int countDay) {
		// TODO Auto-generated method stub
		return talkMapper.queryTalkUserCount(countDay);
	}

	@Override
	public int deleteTalkByReferenceIdAndReferenceType(Talk talk) {
		// TODO Auto-generated method stub
		return talkMapper.deleteTalkByReferenceIdAndReferenceType(talk);
	}

}
