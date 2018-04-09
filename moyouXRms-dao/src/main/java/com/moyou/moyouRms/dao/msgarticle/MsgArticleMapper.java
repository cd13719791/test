package com.moyou.moyouRms.dao.msgarticle;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;

public interface MsgArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgArticle record);

    int insertSelective(MsgArticle record);

    MsgArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgArticle record);

    int updateByPrimaryKeyWithBLOBs(MsgArticle record);

    int updateByPrimaryKey(MsgArticle record);
    /**
     * 初始化推送消息
     * @param page
     * @return
     */
	List<MsgArticle> queryPushMsgList(PageBean page);
}