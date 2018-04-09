package com.moyou.moyouRms.controller.secretRobot;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.SecretInsertModel;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.secretrobot.SecretRobotContentService;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;

@Controller
@RequestMapping("secretRobot")
@ResponseBody
public class SecretRobotController extends BaseController
{
    @Resource
    private SecretRobotService secretRobotService;
    @Resource
    private SecretRobotContentService secretRobotContentService;
    @Resource
    SecretService secretService;

    /**
     * 机器人秘密初始化接口
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "/selectSecretRobotListBySelctive", method = RequestMethod.POST)
    public ApiResult selectSecretRobotListBySelctive(@RequestBody Map record)
    {
        Assert.notNull(record, "参数不能为空");
        PageBean pb = this.getJsonWrapPageBean(record);
        pb.setResults(secretRobotService.selectSecretRobotListExtendsContent(pb));
        return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
    }

    /**
     * 机器人秘密初 统计
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "/selectSecretRobotCount", method = RequestMethod.POST)
    public ApiResult selectSecretRobotCount()
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", secretRobotService.selectSecretRobotCount());
    }

    /**
     * 修改秘密
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    public ApiResult updateByPrimaryKey(@RequestBody SecretRobot record)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", secretRobotService.updatePushStateByPrimaryKey(record));
    }

    /**
     * insert Secret
     * 
     * @param insertModel
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ApiResult insertSecret(@RequestBody SecretInsertModel insertModel)
    {
        Integer secretId = secretService.insertSecret(insertModel);
        Map<String, Object> map = Maps.newHashMap();
        map.put("secretId", secretId);
        return new ApiResult(map);
    }

    /**
     * 机器人秘密详情
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "/selectsecretrobotdetailbysecretid", method = RequestMethod.POST)
    public ApiResult selectSecretRobotDetailBySecretId(@RequestBody Map record)
    {
        Assert.notNull(record, "参数不能为空");
        return new ApiResult(RESPONSE.SUCCESS,
            "成功",
            secretRobotService.selectSecretRobotDetailBySecretId(Integer.valueOf(record.get("id").toString())));
    }
}
