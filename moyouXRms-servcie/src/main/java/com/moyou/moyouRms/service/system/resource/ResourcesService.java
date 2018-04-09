package com.moyou.moyouRms.service.system.resource;

import java.util.List;

import com.moyou.moyouRms.model.po.system.resource.Resources;
import com.moyou.moyouRms.model.tree.ZNodes;
import com.moyou.moyouRms.service.BaseService;

public interface ResourcesService extends BaseService<Resources>{
	 /**
     * 菜单树
     * @param userId 用户Id
     * @param layer 显示层级
     * @return
     */
	public List<Resources> findMenuTree(String userId,String layer);
	 /**
     * 按钮列
     * @param type
     * @param menuId
     * @param userId
     * @return
     */
	public List<Resources> findBtn(String type,String menuId,String userId);
    /**
     * 资源列表只含菜单不含按钮
     * @param r
     * @return
     */
	public List<ZNodes> listResources(Resources r);
	 /**
     * 用户权限资源列表
     * @param userId
     * @param type
     * @return
     */
	public List<Resources> resAuthorized(String userId,String type);
	 /**
     * 事务删除资源
     * @param o
     * @return
     */
	public int tranDelete(Resources o);
	 /**
     * 事务删除资源（批量）
     * @param os
     * @return
     */
	public int tranDeleteBatch(List<Resources> os);
	 /**
     * 事务更新菜单资源
     * @param os
     * @return
     */
	public int updateMenu(Resources o);
	
	/**
	 * 验证用户权限
	 * @param accountId
	 * @param method
	 * @return
	 */
	public boolean validata(String accountId, String method);
}
