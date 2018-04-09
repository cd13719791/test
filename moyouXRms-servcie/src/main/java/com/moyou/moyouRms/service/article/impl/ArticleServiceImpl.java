package com.moyou.moyouRms.service.article.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.msgarticle.MsgArticleMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;
import com.moyou.moyouRms.service.article.ArticleService;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年7月12日 下午4:09:16 
 * 类说明 
 */
@Service
public class ArticleServiceImpl implements ArticleService {
@Resource
private MsgArticleMapper msgArticleMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return msgArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MsgArticle record) {
		// TODO Auto-generated method stub
		return msgArticleMapper.insert(record);
	}

	@Override
	public int insertSelective(MsgArticle record) {
		// TODO Auto-generated method stub
		return msgArticleMapper.insertSelective(record);
	}

	@Override
	public MsgArticle selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return msgArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgArticle record) {
		// TODO Auto-generated method stub
		return msgArticleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(MsgArticle record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MsgArticle record) {
		// TODO Auto-generated method stub
		return msgArticleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MsgArticle> queryPushMsgList(PageBean page) {
		// TODO Auto-generated method stub
		return msgArticleMapper.queryPushMsgList(page);
	}

}
