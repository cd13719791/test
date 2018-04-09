package com.moyou.moyouRms.dao.user;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.H5UserModel;
import com.moyou.moyouRms.model.user.UserFollowers;

public interface UserFollowersMapper
{
    int insert(UserFollowers record);

    int insertSelective(UserFollowers record);

    List<H5UserModel> selectUserFollowersList(PageBean pb);
}
