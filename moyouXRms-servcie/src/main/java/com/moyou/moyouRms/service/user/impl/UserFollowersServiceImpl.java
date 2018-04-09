package com.moyou.moyouRms.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.user.UserFollowersMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.H5UserModel;
import com.moyou.moyouRms.service.user.UserFollowersService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 下午2:41:59 类说明
 */
@Service
public class UserFollowersServiceImpl implements UserFollowersService
{
    @Resource
    private UserFollowersMapper userFollowersMapper;

    @Override
    public List<H5UserModel> selectUserFollowersList(PageBean pb)
    {
        // TODO Auto-generated method stub
        return userFollowersMapper.selectUserFollowersList(pb);
    }

}
