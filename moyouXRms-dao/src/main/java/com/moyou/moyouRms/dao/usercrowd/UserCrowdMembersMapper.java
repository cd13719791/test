package com.moyou.moyouRms.dao.usercrowd;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdMembers;

public interface UserCrowdMembersMapper {
    int deleteByPrimaryKey(Integer id);


    int insert(UserCrowdMembers record);

    int insertSelective(UserCrowdMembers record);

    UserCrowdMembers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCrowdMembers record);

    int updateByPrimaryKey(UserCrowdMembers record);
    //添加群成员
    int addUserCrowdMembers(List<UserCrowdMembers> record);
  /**
   * 根据加入群的编号查询群成员
   * @param crowdId
   * @return
   */
    List<UserInfo> queryUserCrowdMembersList(PageBean pageBean);
    /**
     * 根据用户ID查询该用户是否已经加入该群了
     * @param userId
     * @return
     */
    int queryIsNot(UserCrowdMembers record);
    
    /**
     * 根据群Id查询这个群的所有成员ID
     * @param crowdPkid
     * @return
     */
    List<UserCrowdMembers> queryMemberCrowd(Integer crowdPkid);
    /**
     * 根据用户ID删除群成员
     * @param crowdPkid
     * @return
     */
    int deleteMemberCrowd(UserCrowd userCrowd);
    /**
     *根据群ID查询群成员ID以及成员环信ID
     * @param crowdPkId
     * @return
     */
    List<UserInfo> queryUserIdAndEasemodCroweId(Integer crowdPkId);
    /**
     * 查询群总用户数( 真实用户)
     * @return
     */
    Integer queryUserCrowdCount();
}