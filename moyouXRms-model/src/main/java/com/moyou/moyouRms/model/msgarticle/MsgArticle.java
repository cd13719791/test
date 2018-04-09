package com.moyou.moyouRms.model.msgarticle;

import java.util.Date;

/**
 * 描述:t_msg_article表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-07-11
 */
public class MsgArticle
{
    /**
     * 操作人员
     */
    private String account;

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 简介
     */
    private String articleAbstract;

    /**
     * 点赞数统计
     */
    private Integer praiseTotal;

    /**
     * 评论数统计
     */
    private Integer commentTotal;

    /**
     * 封面
     */
    private String cover;

    /**
     * 0删除 1正常
     */
    private Short state;

    /**
     * 1待发布2发布中3发布成功
     */
    private Short publishState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 阅读量
     */
    private Integer readTotal;

    /**
     * h5地址，默认空
     */
    private String h5Url;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 
     * @return id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 标题
     * 
     * @return article_title 标题
     */
    public String getArticleTitle()
    {
        return articleTitle;
    }

    /**
     * 标题
     * 
     * @param articleTitle 标题
     */
    public void setArticleTitle(String articleTitle)
    {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    /**
     * 简介
     * 
     * @return article_abstract 简介
     */
    public String getArticleAbstract()
    {
        return articleAbstract;
    }

    /**
     * 简介
     * 
     * @param articleAbstract 简介
     */
    public void setArticleAbstract(String articleAbstract)
    {
        this.articleAbstract = articleAbstract == null ? null : articleAbstract.trim();
    }

    /**
     * 点赞数统计
     * 
     * @return praise_total 点赞数统计
     */
    public Integer getPraiseTotal()
    {
        return praiseTotal;
    }

    /**
     * 点赞数统计
     * 
     * @param praiseTotal 点赞数统计
     */
    public void setPraiseTotal(Integer praiseTotal)
    {
        this.praiseTotal = praiseTotal;
    }

    /**
     * 评论数统计
     * 
     * @return comment_total 评论数统计
     */
    public Integer getCommentTotal()
    {
        return commentTotal;
    }

    /**
     * 评论数统计
     * 
     * @param commentTotal 评论数统计
     */
    public void setCommentTotal(Integer commentTotal)
    {
        this.commentTotal = commentTotal;
    }

    /**
     * 封面
     * 
     * @return cover 封面
     */
    public String getCover()
    {
        return cover;
    }

    /**
     * 封面
     * 
     * @param cover 封面
     */
    public void setCover(String cover)
    {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * 0删除 1正常
     * 
     * @return state 0删除 1正常
     */
    public Short getState()
    {
        return state;
    }

    /**
     * 0删除 1正常
     * 
     * @param state 0删除 1正常
     */
    public void setState(Short state)
    {
        this.state = state;
    }

    /**
     * 1待发布2发布中3发布成功
     * 
     * @return publish_state 1待发布2发布中3发布成功
     */
    public Short getPublishState()
    {
        return publishState;
    }

    public static final short PUBLISH_WAIT = 1;
    public static final short PUBLISH_ING = 2;
    public static final short PUBLISH_SUCCESS = 3;
    public static final short STATE_DEFAULT = 1;
    public static final short STATE_DELETE = 0;

    /**
     * 1待发布2发布中3发布成功
     * 
     * @param publishState 1待发布2发布中3发布成功
     */
    public void setPublishState(Short publishState)
    {
        this.publishState = publishState;
    }

    /**
     * 创建时间
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 创建时间
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 发布时间
     * 
     * @return publish_time 发布时间
     */
    public Date getPublishTime()
    {
        return publishTime;
    }

    /**
     * 发布时间
     * 
     * @param publishTime 发布时间
     */
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    /**
     * 阅读量
     * 
     * @return read_total 阅读量
     */
    public Integer getReadTotal()
    {
        return readTotal;
    }

    /**
     * 阅读量
     * 
     * @param readTotal 阅读量
     */
    public void setReadTotal(Integer readTotal)
    {
        this.readTotal = readTotal;
    }

    /**
     * h5地址，默认空
     * 
     * @return h5_url h5地址，默认空
     */
    public String getH5Url()
    {
        return h5Url;
    }

    /**
     * h5地址，默认空
     * 
     * @param h5Url h5地址，默认空
     */
    public void setH5Url(String h5Url)
    {
        this.h5Url = h5Url == null ? null : h5Url.trim();
    }

    /**
     * 内容
     * 
     * @return article_content 内容
     */
    public String getArticleContent()
    {
        return articleContent;
    }

    /**
     * 内容
     * 
     * @param articleContent 内容
     */
    public void setArticleContent(String articleContent)
    {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}
