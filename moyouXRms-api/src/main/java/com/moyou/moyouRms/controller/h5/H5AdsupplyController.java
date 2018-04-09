package com.moyou.moyouRms.controller.h5;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.adsupplyservice.AdSupplyService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月31日 上午11:25:36 类说明
 */
@Controller
@RequestMapping(value = "h5/adsupply")
public class H5AdsupplyController
{
    @Resource
    private AdSupplyService adSupplyService;

    // @Resource
    // private CommonResourceService commonResourceService;

    /**
     * 查询详细信息
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectadsupplybyid", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectAdSupplyById(@RequestBody Map<String, Object> map)
    {
        Assert.notNull(map.get("id"));
        Integer id = Integer.valueOf(map.get("id").toString());
        return new ApiResult(ResponseEnum.SUCCESS, adSupplyService.selectAdsupplyDetailByPrimaryKey(id));
    }
}
