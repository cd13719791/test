package com.moyou.moyouRms.dao.feedback;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.feedback.Feedback;
import com.moyou.moyouRms.model.statistics.UserStatistics;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);
/**
 * 添加一条意见反馈的回复
 * @param record
 * @return
 */
    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);
   
    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
    /**
     * 查询意见反馈集合
     * @return
     */
    List<Feedback> selectFeedBackList(PageBean pagebean);
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
     * 已处理和未处理
     * @return
     */
    UserStatistics queryCountYFeedBack();
}