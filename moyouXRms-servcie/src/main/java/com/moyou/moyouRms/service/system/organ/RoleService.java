package com.moyou.moyouRms.service.system.organ;

import java.util.List;

import com.moyou.moyouRms.interceptor.Page;
import com.moyou.moyouRms.model.po.system.organ.Role;
import com.moyou.moyouRms.model.tree.ZNodes;
import com.moyou.moyouRms.service.BaseService;

public interface RoleService extends BaseService<Role>{

	 /**
     * 权限列表包含按钮
     * @param roleId 角色Id
     * @param layer  显示层级
     * @return
     */
	public List<ZNodes> listAuthorized(String roleId,String layer);
	 /**
     * 根据角色Id保存权限列表
     * @param roleId 角色Id
     * @param auss 权限数组
     * @return
     */
	public void saveAuthorized(String roleId,String auss,String layer);
	 /**
     * 获取所有角色
     * @return
     */
	public Page<Role> findAllRoleByPage(Role o,Page<Role> page);
	/**
	 * 根据id获取角色
	 * @param roleId
	 * @return
	 */
	Role get(String roleId);
	
	}
