package com.moyou.moyouRms.service.user;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.H5UserModel;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 下午2:41:16 类说明
 */
public interface UserFollowersService
{
    List<H5UserModel> selectUserFollowersList(PageBean pb);
}
