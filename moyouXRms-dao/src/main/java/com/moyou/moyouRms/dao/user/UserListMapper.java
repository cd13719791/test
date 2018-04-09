package com.moyou.moyouRms.dao.user;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.UserList;

public interface UserListMapper {
	/**
	 * 用户列表初始化所有信息 * @param pageBean
	 * 
	 * @return
	 */
	List<UserList> selectUserListALL(PageBean pageBean);
	
	/**
	 * 推荐用户初始化接口
	 * @param pb
	 * @return
	 */
	List<UserList> queryUserRecommend(PageBean pb);
	
	
}
