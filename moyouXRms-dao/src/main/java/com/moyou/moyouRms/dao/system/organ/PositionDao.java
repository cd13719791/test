package com.moyou.moyouRms.dao.system.organ;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.dao.BaseDao;
import com.moyou.moyouRms.dao.MoYouBatis;
import com.moyou.moyouRms.interceptor.Page;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.po.system.organ.AccountPosition;
import com.moyou.moyouRms.model.po.system.organ.Position;
import com.moyou.moyouRms.model.tree.ZNodes;
@MoYouBatis
public interface PositionDao extends BaseDao<Position>{
   /**
   * 获得岗位树
   * @return
   */
   public List<ZNodes> getOrgAndPositionTree();
   /**
   * 获得上级组织树
   * @return
   */
   public List<ZNodes> getPreOrgTree();
   /**
    * 增加用户职务表 
    * @param list
    */
   public void insertAccountPosition(List<AccountPosition> list);  
   /**
    * 删除用户职务表通过用户Id
    * @param accountId 用户Id
    */
   public void deleteAccPosByAccId(String accountId);
   /**
    * 批量删除用户职务表通过用户Id
    * @param accountId 用户Id
    */
   public void deleteBatchAccPosByAccId(List<Account> accs);
   /**
    * 删除用户职务表通过职务Id
    * @param posId 职务Id
    */
   public void deleteAccPosByPosId(String posId);
   /**
    * 批量删除用户职务表通过职务Id
    * @param posId 职务Id
    */
   public void deleteBatchAccPosByPosId(List<Position> poss);
   /**
  	 * 通过职位Id寻找可安排用户列表
  	 * @param Position 
  	 */
   public List<Account> findArrangeAccByPage(@Param("param")Position o,Page<Account> page);
   /**
 	 * 通过职位Id寻找已安排用户列表
 	 * @param Position 
 	 */
   public List<Account> findPosByPage(@Param("param")Position o,Page<Account> page);
   /**
	 * 通过组织Id寻找职务表
	 * @param orgId 
	 */
   public List<Position> findByOrgId(@Param("orgId")String orgId);
   /**
    * 获取所有职务
    * @return
    */
   public List<Position> findAllPosByPage(@Param("param")Position o,Page<Position> page);
   
}
