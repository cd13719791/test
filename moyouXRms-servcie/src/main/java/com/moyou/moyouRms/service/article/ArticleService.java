package com.moyou.moyouRms.service.article;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年7月12日 下午4:08:07 
 * 类说明 
 */
public interface ArticleService {
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
