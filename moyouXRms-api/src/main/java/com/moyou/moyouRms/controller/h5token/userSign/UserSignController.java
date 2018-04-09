package com.moyou.moyouRms.controller.h5token.userSign;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.userSignLog.UserSignLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.goldSignConfigure.GoldSignConfigureService;
import com.moyou.moyouRms.service.usersign.UserSignService;
import com.moyou.moyouRms.service.usersignlog.UserSignLogService;
import com.moyou.moyouRms.util.DateTimeUtil;

@Controller
@ResponseBody
@RequestMapping(value = "/h5token/userSign")
public class UserSignController extends BaseController
{

    @Resource
    private UserSignService userSignService;
    @Resource
    private UserSignLogService userSignLogService;
    @Resource
    private GoldSignConfigureService goldSignConfigureService;

    /**
     * 用户签到初始化
     * 
     * @param userId
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    @RequestMapping(value = "/selectUserSignLog", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectUserSign(@RequestBody Map param)
    {
        Integer userId = null;
        try
        {
            Assert.notNull(param.get("token"), "参数不能为空");
            Assert.notNull(param.get("startTime"), "参数不能为空");
            Assert.notNull(param.get("endTime"), "参数不能为空");
            userId = getUserIdByToken(param.get("token") + "");
            // System.out.println(userId);
            param.put("userId", userId + "");
        }
        catch (Exception e)
        {
            return new ApiResult(RESPONSE.ERROR, "失败");
        }
        List<UserSignLog> list = userSignLogService.selectUserSignLogListByDate(param);
        /**
         * 格式化时间为 yyyy-MM-dd
         */
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).setSignDate(DateTimeUtil.formatDate(list.get(i).getCreateTime(), "yyyy-MM-dd"));
        }
        /**
         * 查询用户签到统计数据
         */
        param.clear();
        param.put("data", list);
        param.put("userSign", userSignService.getUSerSignByUserId(userId + ""));
        param.put("userSignRule", goldSignConfigureService.selectAllForGoldSet());
        return new ApiResult(RESPONSE.SUCCESS, "成功", param);
    }

    /**
     * 用户签到
     * 
     * @param userId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/insertUserSign", method = RequestMethod.POST)
    public @ResponseBody ApiResult insertUserSign(@RequestBody Map param)
    {
        Assert.notNull(param, "参数不能为空");
        // Integer userId = getUserIdByToken(param.get("token") + "");
        // UserGoldRule result = userSignService.addUserCheck(userId + "");
        // if (result != null)
        // {
        // return new ApiResult(RESPONSE.SUCCESS, "成功", result);
        // }
        // else
        // {
        return new ApiResult(RESPONSE.ERROR, "失败", false);
        // }

    }

}
