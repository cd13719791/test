package com.moyou.moyouRms.service.feedback;

import java.util.List;

import com.moyou.moyouRms.model.feedback.Feedback;
import com.moyou.moyouRms.model.feedback.FeedbackPage;

public interface FeedBackService {
	/**
	 * 添加一条意见反馈
	 * @param record
	 * @return
	 */
	int insert(Feedback record);
	   /**
     * 查询意见反馈集合
     * @return
     */
    List<Feedback> selectFeedBackList(FeedbackPage feedbackPage);
    /**
     * 根据意见反馈主键ID查询意见反馈详情
     * @param feedbackId
     * @return
     */
    Feedback selectFeedBackInfo(Integer feedbackId);
    
    /**
     * 根据意见反馈的主键ID 修改它的处理状态
     * @param record
     * @return
     */
    Integer updateFeedBackState(Feedback record);
    /**
     * 意见反馈修改状态为忽略
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(Feedback record);
    
}
