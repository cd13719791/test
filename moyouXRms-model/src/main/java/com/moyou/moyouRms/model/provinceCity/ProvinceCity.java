package com.moyou.moyouRms.model.provinceCity;

import java.util.Date;

/**
 * 描述:t_province_city表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-11
 */
public class ProvinceCity {
	Integer isHot;//热点排序
    public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	/**
     * 主键uuid
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 0删除1正常
     */
    private Integer state;

    /**
     * 1国2省3市4县5乡
     */
    private Integer dataType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 用于检索如市类型数据，则该值为四川绵阳
     */
    private String searchContent;

    /**
     * 主键uuid
     * @return id 主键uuid
     */
    public String getId() {
        return id;
    }

    /**
     * 主键uuid
     * @param id 主键uuid
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 名字
     * @return name 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 名字
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父级id
     * @return parent_id 父级id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级id
     * @param parentId 父级id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 0删除1正常
     * @return state 0删除1正常
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0删除1正常
     * @param state 0删除1正常
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 1国2省3市4县5乡
     * @return data_type 1国2省3市4县5乡
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 1国2省3市4县5乡
     * @param dataType 1国2省3市4县5乡
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 用于检索如市类型数据，则该值为四川绵阳
     * @return search_content 用于检索如市类型数据，则该值为四川绵阳
     */
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * 用于检索如市类型数据，则该值为四川绵阳
     * @param searchContent 用于检索如市类型数据，则该值为四川绵阳
     */
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent == null ? null : searchContent.trim();
    }
}