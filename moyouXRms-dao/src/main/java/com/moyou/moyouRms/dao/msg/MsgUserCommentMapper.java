package com.moyou.moyouRms.dao.msg;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msg.MsgItemReturn;
import com.moyou.moyouRms.model.msg.MsgUserComment;

/**
 * 评论推送
 * 
 * @author PzC.
 * @time 2016年11月30日 下午5:58:04
 * 
 */
public interface MsgUserCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserComment record);

    int insertSelective(MsgUserComment record);

    MsgUserComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserComment record);

    int updateByPrimaryKey(MsgUserComment record);
    
    /**
     * list 插入
     * 
     * @param records
     * @return
     */
    int insertList(List<MsgUserComment> records);
    
    /**
     * 查询一条评论消息
     * 
     * @param userId
     * @return
     */
    MsgItemReturn queryCommentMsgItem(String userId);
    
    /**
     * 评论消息列表
     * 
     * @param pageBean
     * @return
     */
    List<Map<String, Object>> queryCommentMsgList(PageBean pageBean);
}