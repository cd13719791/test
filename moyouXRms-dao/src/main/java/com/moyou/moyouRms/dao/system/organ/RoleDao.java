package com.moyou.moyouRms.dao.system.organ;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.dao.BaseDao;
import com.moyou.moyouRms.dao.MoYouBatis;
import com.moyou.moyouRms.interceptor.Page;
import com.moyou.moyouRms.model.po.system.organ.Role;
import com.moyou.moyouRms.model.po.system.resource.RoleResources;
import com.moyou.moyouRms.model.tree.ZNodes;
@MoYouBatis
public interface RoleDao extends BaseDao<Role>{
	/**
     * 根据Id获得角色
     * @param 角色Id
     * @return
     */
	public Role getRole(@Param("id")String id);
	 /**
     * 权限列表包含按钮
     * @param 角色Id
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("roleId")String roleId,@Param("layer")String layer);	
	 /**
     * 根据角色Id删除所有权限关系
     * @param roleId 角色Id
     * @return
     */
	public void delAuthorizedByRoleId(@Param("roleId")String roleId);
	 /**
     * 根据角色Id和显示层级删除权限关系
     * @param roleId 角色Id
     * @param layer 显示层级
     * @return
     */
	public void delAuthorizedByRoleIdAndLayer(@Param("roleId")String roleId,@Param("layer")String layer);
	 /**
     * 根据角色Id删除所有权限关系(批量)
     * @param os 角色Id集合
     * @return
     */
	public void deleteBatchAuthorizedByRoleId(List<Role> os);
	/**
     * 通过角色资源对象列表建立权限关系(批量插入)
     * @param  list 角色资源对象列表
     * @return
     */
	public void insertAuthorizedByRoleId(List<RoleResources> list);
	 /**
     * 获取所有角色
     * @return
     */
	public List<Role> findAllRoleByPage(@Param("param")Role o,Page<Role> page);
	
}
