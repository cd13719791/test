package com.moyou.moyouRms.controller.adsupply;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.adsupply.AdSupply;
import com.moyou.moyouRms.model.adsupply.AdSupplyComment;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.adsupplyservice.AdSupplyService;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月18日 上午10:53:34 类说明 广告业务controller
 */
@Controller
@RequestMapping(value = "adsupply")
public class AdSupplyController extends BaseController
{
    @Resource
    private AdSupplyService adSupplyService;
    @Resource
    private CommonResourceService commonResourceService;

    /**
     * 初始化广告
     * 
     * @param map `audit` smallint(1) DEFAULT '0' COMMENT '0等待审核1通过2不通过',
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/selectadsupplylist", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectAdSupplyList(@RequestBody Map<String, Object> map)
    {
        // Assert.notNull(map.get("audit"));
        PageBean pb = this.getJsonWrapPageBean(map);
        pb.setResults(adSupplyService.selectAdSupplyList(pb));
        return new ApiResult(ResponseEnum.SUCCESS, pb);
    }

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

    /**
     * 修改审核状态
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/updateadsupplybyid", method = RequestMethod.POST)
    public @ResponseBody ApiResult updateAdSupplyById(@RequestBody AdSupply record)
    {
        Assert.notNull(record);
        Map<String, Object> map = new HashMap<String, Object>();
        if (this.getAdminUser() != null)
        {
            map.put("auditer", this.getAdminUser().getLoginName());
        }
        else
        {
            map.put("auditer", "没有登录");
        }
        record.setExtendData(JsonUtil.toJson(map));// 添加审核人
        return new ApiResult(ResponseEnum.SUCCESS, adSupplyService.updateByPrimaryKeySelective(record,
            this.getRequest()));
    }

    /**
     * 修改消费金币额度
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/updateconsumercoin", method = RequestMethod.POST)
    public @ResponseBody ApiResult updateConsumerCoin(@RequestBody CommonResource commonResource)
    {
        Assert.notNull(commonResource.getExtendInt());
        return new ApiResult(ResponseEnum.SUCCESS, commonResourceService.updateConsumerCoin(commonResource));
    }

    /**
     * 查询消费金币额度
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/selectconsumercoin", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectConsumerCoin()
    {
        CommonResource commonResource = new CommonResource();
        commonResource.setObjectType(CommonResource.OBJECT_TYPE_CONSUMER_COIN);
        commonResource = commonResourceService.selectCommonResourceByObjectType(commonResource);
        if (commonResource == null)
        {
            return new ApiResult(ResponseEnum.SUCCESS, 0);
        }
        return new ApiResult(ResponseEnum.SUCCESS, commonResource.getExtendInt());
    }

    /**
     * 查询广告处理统计信息
     * 
     * @return
     */
    @RequestMapping(value = "/selectadsupplycount", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectAdsupplyCount()
    {
        return new ApiResult(ResponseEnum.SUCCESS, adSupplyService.selectAdsupplyCount());
    }

    /**
     * 删除广告
     * 
     * @return
     */
    @RequestMapping(value = "/deleteadsupply", method = RequestMethod.POST)
    public @ResponseBody ApiResult deleteAdsupply(@RequestBody Map<String, Object> map)
    {
        Assert.notNull(map.get("id"));
        return new ApiResult(ResponseEnum.SUCCESS,
            adSupplyService.deleteAdsupply(Integer.valueOf(map.get("id").toString())));
    }

    /**
     * 广告增量统计数据
     * 
     * @return
     */
    @RequestMapping(value = "/selectadsupplyallcount", method = RequestMethod.POST)
    public @ResponseBody ApiResult selectAdsupplyAllCount()
    {
        return new ApiResult(ResponseEnum.SUCCESS, adSupplyService.selectAdsupplyAllCount());
    }

    /**
     * 添加广告
     * 
     * @return
     */
    @RequestMapping(value = "/insertadsupplycomment", method = RequestMethod.POST)
    public @ResponseBody ApiResult insertAdSupplyComment(@RequestBody AdSupplyComment adSupplyComment)
    {
        return adSupplyService.insertAdSupplyComment(adSupplyComment);
    }

    /**
     * 删除广告评论
     * 
     * @return
     */
    @RequestMapping(value = "/deleteadsupplycomment", method = RequestMethod.POST)
    public @ResponseBody ApiResult deleteAdSupplyComment(@RequestBody AdSupplyComment adSupplyComment)
    {
        return adSupplyService.deleteAdSupplyComment(adSupplyComment);
    }

    // /**
    // * 删除广告
    // *
    // * @return
    // */
    // @RequestMapping(value = "/deleteadsupply", method = RequestMethod.POST)
    // public @ResponseBody ApiResult deleteAdSupply(@RequestBody AdSupply adSupply)
    // {
    // return adSupplyService.deleteAdSupply(adSupply);
    // }
}
