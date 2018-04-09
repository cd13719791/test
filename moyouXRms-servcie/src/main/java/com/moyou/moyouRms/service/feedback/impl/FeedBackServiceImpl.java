package com.moyou.moyouRms.service.feedback.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.feedback.FeedbackMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.feedback.Feedback;
import com.moyou.moyouRms.model.feedback.FeedbackPage;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.service.feedback.FeedBackService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;

@Service
public class FeedBackServiceImpl implements FeedBackService {
	@Resource
	private FeedbackMapper feedbackMapper;
	@Resource
	private MsgSystemXService msgSystemXService;

	/**
	 * 添加一条意见反馈
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int insert(Feedback record) {
		record.setFormApp((short) 0);
		record.setState(1);
		return feedbackMapper.insert(record);
	}

	/**
	 * 查询意见反馈集合
	 * 
	 * @return
	 */
	@Override
	public List<Feedback> selectFeedBackList(FeedbackPage feedbackPage) {
		PageBean pagebean = new PageBean();
		pagebean.setPageNumber(feedbackPage.getPageNumber());
		pagebean.setPageSize(feedbackPage.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("state", feedbackPage.getState());
		pagebean.setConditions(map);
		List<Feedback> results = new ArrayList<Feedback>();
		results = feedbackMapper.selectFeedBackList(pagebean);
		feedbackPage.setTotal(pagebean.getTotalRecord());
		feedbackPage.setResults(results);
		return results;
	}

	/**
	 * 根据意见反馈主键ID查询意见反馈详情
	 * 
	 * @param feedbackId
	 * @return
	 */
	@Override
	public Feedback selectFeedBackInfo(Integer feedbackId) {
		Feedback feedback = feedbackMapper.selectFeedBackInfo(feedbackId);
		feedback.setMsgSystemXList(msgSystemXService.queryMsgSystemByModeTypeAndModeId(
				MsgSystemX.FEED_BACK.intValue(), feedbackId));
		return feedback;
	}

	/**
	 * 根据意见反馈的主键ID 修改它的处理状态
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer updateFeedBackState(Feedback record) {
		MsgSystemX msgSystemX = new MsgSystemX();
		Integer id = record.getId();
		msgSystemX.setMsgSendType(CONSTANT.FEEDBACK_SYSTEM);
		msgSystemX.setMsgTitle(CONSTANT.FEEDBACK_SYSTEM_TITLE);
		msgSystemX.setModeId(record.getId());
		msgSystemX.setModeType((short) 5);
		msgSystemX.setMsgContent(record.getContent());
		msgSystemX.setReceiveUserId(record.getUserId());
		msgSystemX.setSendUserId(record.getSendUserId());
		msgSystemXService.addSysMsgAndPushCustomMsg(msgSystemX);
		record.setContent(null);
		record.setId(id);
		record.setState(Feedback.DOING);
		record.setCreateTime(null);
		return feedbackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Integer updateByPrimaryKeySelective(Feedback record) {
		// TODO Auto-generated method stub
		record.setCreateTime(null);
		return feedbackMapper.updateByPrimaryKeySelective(record);
	}

}
