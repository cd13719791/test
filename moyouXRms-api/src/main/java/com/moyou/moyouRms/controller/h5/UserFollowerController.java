package com.moyou.moyouRms.controller.h5;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.UserFollowersService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 下午2:31:39 类说明
 */

@RestController
@RequestMapping(value = "/h5/follower")
public class UserFollowerController extends BaseController
{
    @Resource
    private UserFollowersService userFollowersService;

    /**
     * 查找个人粉丝
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/selectuserfollower", method = RequestMethod.POST)
    public ApiResult selectUserFollower(@RequestBody Map<String, Object> record)
    {
        PageBean pb = this.getJsonWrapPageBean(record);
        pb.setResults(userFollowersService.selectUserFollowersList(pb));
        return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
    }
}
