package com.moyou.moyouRms.controller.adsupply;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.banner.Banner;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.banner.LifeMomentService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月19日 下午2:17:52 类说明
 */
@RestController
@RequestMapping(value = "adsupplybanner")
public class AdSupplyBannerController
{
    @Resource
    private LifeMomentService lifeMomentService;

    /**
     * 初始化banner
     * 
     * @return
     */
    @RequestMapping(value = "/queryadsupplybanner", method = RequestMethod.POST)
    public ApiResult queryBanner()
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.queryAdSupplyBanner());
    }

    /**
     * 删除banner
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletebanner", method = RequestMethod.POST)
    public ApiResult deleteBanner(@RequestBody String id)
    {
        String processId = JsonUtil.toObject(id, HashMap.class).get("id").toString();
        Integer it = Integer.valueOf(processId);
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.deleteBanner(it));
    }

    /**
     * 添加banner
     * 
     * @param banner
     * @return
     */
    @RequestMapping(value = "/insertbanner", method = RequestMethod.POST)
    public ApiResult insertBanner(@RequestBody Banner banner)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.insertAdSupplyBanner(banner));
    }

    /**
     * 启用/禁用banner
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatestate", method = RequestMethod.POST)
    public ApiResult updateState(@RequestBody Banner banner)
    {
        // banner状态 0.停用未推荐1.启用推荐
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.updateState(banner));
    }

    /**
     * 查询banner详情
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/querybannerinfo", method = RequestMethod.POST)
    public ApiResult queryBannerInfo(@RequestBody String id)
    {
        String processId = JsonUtil.toObject(id, HashMap.class).get("id").toString();
        Integer it = Integer.valueOf(processId);
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.queryBannerInfo(it));
    }

    /**
     * 修改banner排序
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateorderno", method = RequestMethod.POST)
    public ApiResult updateOrderNo(@RequestBody Banner banner)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", lifeMomentService.updateOrderNo(banner));
    }

}
