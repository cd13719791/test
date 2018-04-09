package com.moyou.moyouRms.service.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.dao.user.UserFollowersMapper;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.model.user.UserFollowers;
import com.moyou.moyouRms.service.BaseJunit4;

public class UpdateUserFollowers extends BaseJunit4
{
    @Resource
    private UserService userService;
    @Resource
    private UserFollowersMapper userFollowersMapper;
    @Resource
    private UserCountService userCountService;

    @SuppressWarnings({"all" })
    @Test
    @Rollback(false)
    /**
     * 数据库莫有id重复 修改 假用户的moyouID
     */
    public void insert() throws Exception
    {
        List<User> user = userService.queryAllFakeUser();
        List<User> userTemp = new ArrayList<User>();
        userTemp.addAll(user);
        user.stream().forEach(userIndex -> {

            // for (int i = 0; i < user.size(); i++)

            {
                Collections.shuffle(userTemp);
                Integer count = (int) (Math.random() * 100 + 1);
                List<User> doList = userTemp.subList(0, count);
                for (User user2 : doList)
                {
                    // 修改被关注人的粉丝数量
                    if (userCountService.queryUserCount(new UserCount(userIndex.getUserId())) == null)
                    {
                        UserCount userCount = new UserCount(userIndex.getUserId());
                        userCount.setUserFollowersCount(1);
                        userCountService.insertSelective(userCount);
                    }
                    else
                    {
                        userCountService.updateFollowersCountJIA1(userIndex.getUserId());
                    }
                    // 修改关注人的关注数量
                    if (userCountService.queryUserCount(new UserCount(user2.getUserId())) == null)
                    {
                        UserCount userCount = new UserCount(user2.getUserId());
                        userCount.setUserInterestCount(1);
                        userCountService.insertSelective(userCount);
                    }
                    else
                    {
                        userCountService.updateInterestCountJIA1(user2.getUserId());
                    }
                    userFollowersMapper.insertSelective(new UserFollowers(userIndex.getUserId(),
                        user2.getUserId(),
                        (short) 1,// 0不是粉丝的状态 1粉丝的状态
                        new Date(),
                        new Date()));
                }
            }
        });
    }
}
