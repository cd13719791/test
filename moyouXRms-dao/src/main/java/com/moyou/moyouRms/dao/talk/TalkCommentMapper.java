package com.moyou.moyouRms.dao.talk;

import java.util.List;

import com.moyou.moyouRms.model.talk.TalkComment;


public interface TalkCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalkComment record);

    int insertSelective(TalkComment record);

    TalkComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalkComment record);

    int updateByPrimaryKey(TalkComment record);
    List<TalkComment> queryTalkCommentListByTalkId(Integer talkId);
} 