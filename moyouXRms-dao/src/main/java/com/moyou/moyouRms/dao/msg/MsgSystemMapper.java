package com.moyou.moyouRms.dao.msg;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msg.MsgItemReturn;
import com.moyou.moyouRms.model.msg.MsgSystem;

public interface MsgSystemMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgSystem record);

    int insertSelective(MsgSystem record);

    MsgSystem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgSystem record);

    int updateByPrimaryKey(MsgSystem record);
    
    /**
     * 查询一条系统消息
     * 
     * @param userId
     * @return
     */
    MsgItemReturn querySystemMsgItem(String userId);
    
    /**
     * 系统消息列表
     * 
     * @param pageBean
     * @return
     */
    List<Map<String, Object>> querySystemMsgList(PageBean pageBean);
}