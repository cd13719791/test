package com.moyou.moyouRms.controller.citymanager;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.CommonResourceService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年6月27日 下午2:52:27 类说明 发布文案接口
 */
@Controller
@RequestMapping("initManager")
public class InitManagerController
{
    @Resource
    private CommonResourceService commonResourceService;

    /**
     * 查询文案
     * 
     * @return
     */
    @RequestMapping(value = "/selectcommonresource", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectCommonResource()
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", commonResourceService.selectCommonResource()
            .getExtendData());
    }

    /**
     * 修改文案
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "/updatecommonresourceextenddata", method = RequestMethod.POST)
    public @ResponseBody ApiResult updateCommonResourceExtendData(@RequestBody Map<String, Object> param)
    {

        return new ApiResult(RESPONSE.SUCCESS,
            "成功",
            commonResourceService.updateCommonResourceExtendData((int) CommonResource.OBJECT_TYPE_TEXT,
                param.get("text").toString()) == 1 ? "修改成功" : "修改失败");
    }
}
