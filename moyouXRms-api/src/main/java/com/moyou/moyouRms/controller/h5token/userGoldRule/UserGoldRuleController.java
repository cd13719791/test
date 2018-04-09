package com.moyou.moyouRms.controller.h5token.userGoldRule;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.userGoldRule.UserGoldRuleService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月3日 下午2:29:44 类说明
 */
@RestController
@RequestMapping("h5token/usergoldrule")
public class UserGoldRuleController
{
    @Resource
    private UserGoldRuleService userGoldRuleService;

    @RequestMapping(value = "/selectusergoldrule", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectUserGoldRule(@RequestBody Map<String, Object> map)
    {
        Assert.notNull(map);
        Assert.notNull(map.get("systemType"));// 系统类型 ios 1 Android 2;

        return userGoldRuleService.selectUserGoldRuleByType(Integer.valueOf(map.get("systemType").toString()));
    }
}
