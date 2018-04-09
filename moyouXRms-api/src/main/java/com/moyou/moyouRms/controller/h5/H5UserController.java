package com.moyou.moyouRms.controller.h5;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.util.DateTimeUtil;

@RestController
@RequestMapping(value = "h5/user")
public class H5UserController extends BaseController
{
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserCountService userCountService;
    @Resource
    private TalkService talkService;
    @Resource
    private DiaryService diaryService;

    /**
     * h5 分享个人主页
     * 
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
    public ApiResult queryUserInfo(@RequestBody Map<String, Object> params)
    {
        Assert.notNull(params.get("userId"), "参数不能为空");
        int userId = Integer.valueOf(params.get("userId").toString());
        // PageBean pb = this.getJsonWrapPageBean(params);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo = userInfoService.selectUserInfoByUserId(userInfo);
        if (userInfo != null && userInfo.getBirthday() != null)
        {
            userInfo.setAge(DateTimeUtil.getPersonAgeByBirthDate(userInfo.getBirthday()));
        }
        UserCount userCount = new UserCount();
        userCount.setUserId(userId);
        userCount = userCountService.queryUserCount(userCount);
        Map<String, Object> result = new HashMap<String, Object>();// 返回的结果集
        // /**
        // * start 获取用户发布的说说
        // */
        // params.put("creatorId", userId);
        // pb.setConditions(params);
        // List<Talk> talkList = talkService.queryTalkByUserId(pb);
        // result.put("talk", talkList.size() <= 0 ? null : talkList.get(0));
        // /**
        // * end 获取用户发布的说说
        // */
        // /**
        // * start 获取用户发布的故事
        // */
        // params.put("userId", userId);
        // List<Diary> diaryList = diaryService.selectDiaryListByUserId(pb);
        // result.put("diary", diaryList.size() <= 0 ? null : diaryList.get(0));
        /**
         * end 获取用户发布的故事
         */
        result.put("userInfo", userInfo);
        result.put("userCount", userCount);
        return new ApiResult(RESPONSE.SUCCESS, "成功", result);
    }

}
