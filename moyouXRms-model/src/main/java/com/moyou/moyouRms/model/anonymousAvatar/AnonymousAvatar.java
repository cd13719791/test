package com.moyou.moyouRms.model.anonymousAvatar;

/**
 * 描述:t_anonymous_avatar表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-10
 */
public class AnonymousAvatar {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String url;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return url 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}