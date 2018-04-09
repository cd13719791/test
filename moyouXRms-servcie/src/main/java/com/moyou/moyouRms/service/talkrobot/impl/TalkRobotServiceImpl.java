package com.moyou.moyouRms.service.talkrobot.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.talkrobot.TalkRobotCommentMapper;
import com.moyou.moyouRms.dao.talkrobot.TalkRobotMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.model.talkrobot.TalkRobotComment;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.util.JsonUtil;

@Service
public class TalkRobotServiceImpl implements TalkRobotService {

	@Resource
	private TalkRobotCommentMapper talkRobotCommentMapper;
	@Resource
	private TalkRobotMapper talkRobotMapper;
	@Resource
	private CommonResourceService commonResourceService;

	/**
	 * 添加一条评论模板
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ApiResult insert(TalkRobotComment record) {
		record.setCreateTime(new Date());
		record.setParentId(1);
		int i = 1;
		short b = (short) i;
		record.setState(b);
		record.setTalkId(1);
		record.setUserId(1);
		talkRobotCommentMapper.insert(record);
		return new ApiResult(RESPONSE.SUCCESS, "添加成功");
	}

	/**
	 * 查询评论模板集合
	 * 
	 * @return
	 */
	@Override
	public List<TalkRobotComment> queryTalkRobotCommentList(Page page) {
		int countcomment = talkRobotCommentMapper.queryCountComment();
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(page.getPageNumber());
		pageBean.setPageSize(page.getPageSize());
		List<TalkRobotComment> results = talkRobotCommentMapper.queryTalkRobotCommentList(pageBean);
		page.setTotal(pageBean.getTotalRecord());
		page.setResults(results);
		for (TalkRobotComment talkRobotComment : results) {
			talkRobotComment.setCountcomment(countcomment);
		}
		return results;
	}

	/**
	 * 去除所有在冷却时间的评论
	 */
	@SuppressWarnings("all")
	@Override
	public List<TalkRobotComment> queryAllTalkRobotCommentList() {
		// RedisTemplate<String, String> stringRedisTemplate =
		// (RedisTemplate<String, String>) SpringBeanUtils
		// .getBean("redisTemplate_KVString");
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getFirstBeanOfType(RedisTemplate.class);
		if (stringRedisTemplate.hasKey(TalkRobotComment.TALK_ROBOT_COMMENT_IN_REDIS_REPEAT + ":*")) {

			Set<String> keys = stringRedisTemplate
					.keys(TalkRobotComment.TALK_ROBOT_COMMENT_IN_REDIS_REPEAT + ":*");
			List<TalkRobotComment> talkRobotCommentList = new ArrayList<TalkRobotComment>();
			if (keys != null) {
				keys.forEach(key -> {
					String talkRobotCommentJson = stringRedisTemplate.opsForValue().get(key);
					List talkRobotComment = JsonUtil.toObject(talkRobotCommentJson, List.class);
					if (talkRobotComment == null) {
						return;
					}
					talkRobotComment.forEach(u -> {
						talkRobotCommentList.add(JsonUtil.toObject(u, TalkRobotComment.class));
					});
				});
			}
			List<TalkRobotComment> results = talkRobotCommentMapper.queryAllTalkRobotCommentList();
			List<TalkRobotComment> talkRobotList = new ArrayList<TalkRobotComment>();
			for (TalkRobotComment talkRobotComment : results) {
				for (int i = 0; i < talkRobotCommentList.size(); i++) {
					if (talkRobotComment.getId() == talkRobotCommentList.get(i).getId()) {
						talkRobotList.add(talkRobotComment);
					}
				}
			}
			results.removeAll(talkRobotList);
			return results;
		} else {
			List<TalkRobotComment> results = talkRobotCommentMapper.queryAllTalkRobotCommentList();
			return results;
		}
	}

	@Override
	public void updateTalkRobotCommentUseCount(int talkRobotCommentId) {
		talkRobotCommentMapper.updateTalkRobotCommentUseCount(talkRobotCommentId);
	}

	/**
	 * 根据评论ID修改评论状态
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ApiResult updateTalkRobotCommentState(TalkRobotComment record) {
		talkRobotCommentMapper.updateTalkRobotCommentState(record);
		return new ApiResult(RESPONSE.SUCCESS, "修改成功");
	}

	@Override
	public List<TalkRobot> queryTalkRobotList(PageBean page) {
		List<TalkRobot> list = talkRobotMapper.queryTalkRobotList(page);
		return list;
	}

	@Override
	public List<TalkRobot> queryTalkRobotPageList(PageBean page) {
		// TODO Auto-generated method stub
		List<TalkRobot> list = talkRobotMapper.queryTalkRobotList(page);
		if (list != null && list.size() > 0) {
			list.forEach(talkRobot -> {
				CommonResource commonResource = new CommonResource();
				commonResource.setObjectId(talkRobot.getId());
				commonResource.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
				talkRobot.setResources(commonResourceService
						.selectCommonResourceByObjectId(commonResource));
				// talkRobot.setPicCount(talkRobot.getResources().size());
			});
		}
		return list;
	}

	@Override
	public int insertTalkRobot(TalkRobot record) {
		// TODO Auto-generated method stub

		return talkRobotMapper.insertSelective(record);
	}

	@Override
	public int updateTalkRobotState(TalkRobot record) {

		return talkRobotMapper.updateTalkRobotState(record);
	}

	@Override
	public TalkRobot queryTalkRobotId(int talkRobot) {
		// TODO Auto-generated method stub
		return talkRobotMapper.selectByPrimaryKey(talkRobot);
	}

	@Override
	public Map<String, Long> queryNewTalkRobotAndAllTalkRobotCount() {
		// TODO Auto-generated method stub
		return talkRobotMapper.queryNewTalkRobotAndAllTalkRobotCount();
	}

	@Override
	public void updateTalkPublishStatus(TalkRobot record) {
		talkRobotMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TalkRobot selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return talkRobotMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TalkRobot> queryTalkRobotListForAutoPublish(PageBean pb) {
		// TODO Auto-generated method stub
		List<TalkRobot> list = talkRobotMapper.queryTalkRobotListForAutoPublish(pb);
		if (list != null && list.size() > 0) {
			list.forEach(talkRobot -> {
				CommonResource commonResource = new CommonResource();
				commonResource.setObjectId(talkRobot.getId());
				commonResource.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
				talkRobot.setResources(commonResourceService
						.selectCommonResourceByObjectId(commonResource));
				// talkRobot.setPicCount(talkRobot.getResources().size());
			});
		}
		return list;
	}
}
