package com.moyou.moyouRms.dao.chatRevert;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.chatRevert.ChatRevert;

public interface ChatRevertMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(ChatRevert record);

    int insertSelective(ChatRevert record);

    ChatRevert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatRevert record);

    int updateByPrimaryKey(ChatRevert record);

    List<ChatRevert> queryListChatRevert();

    List<ChatRevert> queryListAccostRevert(PageBean pb);
}
