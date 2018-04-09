package com.moyou.moyouRms.service.adsupplyservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.adsupply.AdSupply;
import com.moyou.moyouRms.model.adsupply.AdSupplyComment;
import com.moyou.moyouRms.response.ApiResult;

public interface AdSupplyService
{
    int deleteByPrimaryKey(Integer id);

    int insert(AdSupply record);

    int insertSelective(AdSupply record);

    AdSupply selectAdsupplyDetailByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdSupply record, HttpServletRequest req);

    int updateByPrimaryKey(AdSupply record);

    /**
     * 初始化广告
     * 
     * @param pb
     * @return
     */
    List<AdSupply> selectAdSupplyList(PageBean pb);

    /**
     * 查询统计信息
     * 
     * @return
     */
    Map<String, Object> selectAdsupplyCount();

    int deleteAdsupply(Integer valueOf);

    Map<String, Object> selectAdsupplyAllCount();

    /**
     * 添加评论
     * 
     * @param adSupplyComment
     * @return
     */
    ApiResult insertAdSupplyComment(AdSupplyComment adSupplyComment);

    /**
     * 删除评论
     * 
     * @param adSupplyComment
     * @return
     */
    ApiResult deleteAdSupplyComment(AdSupplyComment adSupplyComment);

    ApiResult deleteAdSupply(AdSupply adSupply);
}
