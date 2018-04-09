package com.moyou.moyouRms.model.sysBaseResources;

import java.util.Date;
import java.util.List;

/**
 * 描述:sys_base_resources表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-24
 */
public class SysBaseResources {
	
	/**
	 * 前端选中状态 0未选中 1选中
	 */
	private Integer isCheck;
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	/**
     * Id
     */
    private String id;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 父Id
     */
    private String parentid;

    /**
     * 资源模块乘积
     */
    private Integer layer;

    /**
     * 资源类型(1:为菜单，2:功能，3:按钮)
     */
    private Integer type;

    /**
     * 菜单链接
     */
    private String resurl;

    /**
     * 
     */
    private String btnid;

    /**
     * 功能类型
     */
    private String btnfun;

    /**
     * 菜单Icon
     */
    private String icon;

    /**
     * 菜单顺序(由小到大排列)
     */
    private Integer sort;

    /**
     * 禁用状态(1:有效,0:无效)
     */
    private Integer isvalid;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * Id
     * @return id Id
     */
    public String getId() {
        return id;
    }

    /**
     * Id
     * @param id Id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 菜单名字
     * @return name 菜单名字
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名字
     * @param name 菜单名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父Id
     * @return parentId 父Id
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 父Id
     * @param parentid 父Id
     */
    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    /**
     * 资源模块乘积
     * @return layer 资源模块乘积
     */
    public Integer getLayer() {
        return layer;
    }

    /**
     * 资源模块乘积
     * @param layer 资源模块乘积
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    /**
     * 资源类型(1:为菜单，2:功能，3:按钮)
     * @return type 资源类型(1:为菜单，2:功能，3:按钮)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 资源类型(1:为菜单，2:功能，3:按钮)
     * @param type 资源类型(1:为菜单，2:功能，3:按钮)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 菜单链接
     * @return resUrl 菜单链接
     */
    public String getResurl() {
        return resurl;
    }

    /**
     * 菜单链接
     * @param resurl 菜单链接
     */
    public void setResurl(String resurl) {
        this.resurl = resurl == null ? null : resurl.trim();
    }

    /**
     * 
     * @return btnId 
     */
    public String getBtnid() {
        return btnid;
    }

    /**
     * 
     * @param btnid 
     */
    public void setBtnid(String btnid) {
        this.btnid = btnid == null ? null : btnid.trim();
    }

    /**
     * 功能类型
     * @return btnFun 功能类型
     */
    public String getBtnfun() {
        return btnfun;
    }

    /**
     * 功能类型
     * @param btnfun 功能类型
     */
    public void setBtnfun(String btnfun) {
        this.btnfun = btnfun == null ? null : btnfun.trim();
    }

    /**
     * 菜单Icon
     * @return icon 菜单Icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单Icon
     * @param icon 菜单Icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 菜单顺序(由小到大排列)
     * @return sort 菜单顺序(由小到大排列)
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 菜单顺序(由小到大排列)
     * @param sort 菜单顺序(由小到大排列)
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 禁用状态(1:有效,0:无效)
     * @return isValid 禁用状态(1:有效,0:无效)
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 禁用状态(1:有效,0:无效)
     * @param isvalid 禁用状态(1:有效,0:无效)
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
     * 创建时间
     * @return createTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     * @return updateTime 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 修改时间
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * 子资源
     */
	private List<SysBaseResources> childSysBaseResources;
    public List<SysBaseResources> getChildSysBaseResources() {
		return childSysBaseResources;
	}

	public void setChildSysBaseResources(
			List<SysBaseResources> childSysBaseResources) {
		this.childSysBaseResources = childSysBaseResources;
	}
}