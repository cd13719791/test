package com.moyou.moyouRms.model.sysBaseRole;

import java.util.Date;

/**
 * 描述:sys_base_role表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-25
 */
public class SysBaseRole {
    /**
     * 
     */
    private String id;

    /**
     * 组织机构id
     */
    private String orgid;

    /**
     * 
     */
    private String name;

    /**
     * 禁用类型(1:有效，0:无效)
     */
    private Integer isvalid;

    /**
     * 描述
     */
    private String description;

    /**
     * 
     */
    private Date createtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 	
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 组织机构id
     * @return orgId 组织机构id
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * 组织机构id
     * @param orgid 组织机构id
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 禁用类型(1:有效，0:无效)
     * @return isValid 禁用类型(1:有效，0:无效)
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 禁用类型(1:有效，0:无效)
     * @param isvalid 禁用类型(1:有效，0:无效)
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 
     * @return createTime 
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 
     * @param createtime 
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 
     * @return updateTime 
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     * @param updatetime 
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}