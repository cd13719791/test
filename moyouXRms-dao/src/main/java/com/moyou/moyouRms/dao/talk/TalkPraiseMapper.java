package com.moyou.moyouRms.dao.talk;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.talk.TalkPraise;

public interface TalkPraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalkPraise record);

    int insertSelective(TalkPraise record);

    TalkPraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalkPraise record);

    int updateByPrimaryKey(TalkPraise record);
    
    /**
     * 查询点赞状态
     * 
     * @param params
     * @return
     */
    Integer queryPraiseState(Map<String, Object> params);
    
    /**
     * 改变点赞状态
     * 
     * @param talkPraise
     * @return
     */
    Boolean updatePraise(TalkPraise talkPraise);
    
    /**
     * 点赞统计+1
     * @param talkId
     * @return
     */
    Integer updatePraiseAddOne(Integer talkId);
    /**
     * 点赞统计-1
     * @param talkId
     * @return
     */
    Integer updatePraiseDeleOne(Integer talkId);
    /**
     * 根据talkid 点赞查询点赞人头像 只能查出来前5条数据 
     * @param talkId
     * @return
     */
    List<TalkPraise> queryPraiseByTalkId(Integer talkId);
}