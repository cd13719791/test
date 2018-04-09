package com.moyou.moyouRms.service.statistics;

import java.util.List;

import com.moyou.moyouRms.model.statistics.Consume;
import com.moyou.moyouRms.model.statistics.Issue;
import com.moyou.moyouRms.model.statistics.MiniApps;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.statistics.NewUserResult;
import com.moyou.moyouRms.model.statistics.Robot;
import com.moyou.moyouRms.model.statistics.UserStatistics;
import com.moyou.moyouRms.model.userfund.UserFund;

public interface StatisticsService
{
    /**
     * 提现
     * 
     * @return
     */
    Consume queryConsumeList();

    /**
     * 签到
     * 
     * @return
     */
    MiniApps queryConMiniApps();

    /**
     * 发布
     * 
     * @return
     */
    Issue queryIssue();

    /**
     * 用户
     * 
     * @return
     */
    UserStatistics queryUserStatistics();

    /**
     * 机器人
     * 
     * @return
     */
    Robot queryTalkRobot();

    /**
     * 资金池
     * 
     * @param date
     * @return
     */
    List<UserFund> queryUserFundList(UserFund record) throws Exception;

    List<NewUserResult> queryUserCountList(NewUser record);
}
