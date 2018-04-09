package com.moyou.moyouRms.model.category;

import java.util.Date;

/**
 * 描述:t_category表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-02-20
 */
public class Category {
	//群分类删除状态
	public static final short CATEGORY_STATUS_YES=1;
	public static final short CATEGORY_STATUS_NO=0;
	//1=群分类
	public static final short MODE_TYPE_CROWD=1;
	/**
	 * 群数量
	 */
	private Integer crowdNumbre;
	/**
	 * 群成员数量
	 */
	private Integer crowdMemberNumbre;
    public Integer getCrowdNumbre() {
		return crowdNumbre;
	}

	public void setCrowdNumbre(Integer crowdNumbre) {
		this.crowdNumbre = crowdNumbre;
	}

	public Integer getCrowdMemberNumbre() {
		return crowdMemberNumbre;
	}

	public void setCrowdMemberNumbre(Integer crowdMemberNumbre) {
		this.crowdMemberNumbre = crowdMemberNumbre;
	}

	/**
     * 主键uuid
     */
    private String id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 0删除1可用
     */
    private Short categoryStatus;

    /**
     * 1群分类
     */
    private Short modeType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 排序越小越靠前
     */
    private Short categorySort;

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
     * 分类名称
     * @return category_name 分类名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 分类名称
     * @param categoryName 分类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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
     * 0删除1可用
     * @return category_status 0删除1可用
     */
    public Short getCategoryStatus() {
        return categoryStatus;
    }

    /**
     * 0删除1可用
     * @param categoryStatus 0删除1可用
     */
    public void setCategoryStatus(Short categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    /**
     * 1群分类
     * @return mode_type 1群分类
     */
    public Short getModeType() {
        return modeType;
    }

    /**
     * 1群分类
     * @param modeType 1群分类
     */
    public void setModeType(Short modeType) {
        this.modeType = modeType;
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
     * 排序越小越靠前
     * @return category_sort 排序越小越靠前
     */
    public Short getCategorySort() {
        return categorySort;
    }

    /**
     * 排序越小越靠前
     * @param categorySort 排序越小越靠前
     */
    public void setCategorySort(Short categorySort) {
        this.categorySort = categorySort;
    }
}