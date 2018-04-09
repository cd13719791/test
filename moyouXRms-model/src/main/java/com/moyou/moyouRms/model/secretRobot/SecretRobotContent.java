package com.moyou.moyouRms.model.secretRobot;

import java.util.Date;

/**
 * 描述:t_secret_robot_content表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-07
 */
public class SecretRobotContent {
    /**
     * 
     */
    private Integer id;

    /**
     * 秘密 id
     */
    private Integer secretId;

    /**
     * 文字内容或者图片 URL
     */
    private String textOrPicture;

    /**
     * 文字和图片的排序 从 1 开始
     */
    private Integer sorting;

    /**
     * 0表示文字 1表示图片
     */
    private Integer contentType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 附加数据
     */
    private String extendData;

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
     * 秘密 id
     * @return secret_id 秘密 id
     */
    public Integer getSecretId() {
        return secretId;
    }

    /**
     * 秘密 id
     * @param secretId 秘密 id
     */
    public void setSecretId(Integer secretId) {
        this.secretId = secretId;
    }

    /**
     * 文字内容或者图片 URL
     * @return text_or_picture 文字内容或者图片 URL
     */
    public String getTextOrPicture() {
        return textOrPicture;
    }

    /**
     * 文字内容或者图片 URL
     * @param textOrPicture 文字内容或者图片 URL
     */
    public void setTextOrPicture(String textOrPicture) {
        this.textOrPicture = textOrPicture == null ? null : textOrPicture.trim();
    }

    /**
     * 文字和图片的排序 从 1 开始
     * @return sorting 文字和图片的排序 从 1 开始
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 文字和图片的排序 从 1 开始
     * @param sorting 文字和图片的排序 从 1 开始
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    /**
     * 0表示文字 1表示图片
     * @return content_type 0表示文字 1表示图片
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * 0表示文字 1表示图片
     * @param contentType 0表示文字 1表示图片
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 附加数据
     * @return extend_data 附加数据
     */
    public String getExtendData() {
        return extendData;
    }

    /**
     * 附加数据
     * @param extendData 附加数据
     */
    public void setExtendData(String extendData) {
        this.extendData = extendData == null ? null : extendData.trim();
    }
}