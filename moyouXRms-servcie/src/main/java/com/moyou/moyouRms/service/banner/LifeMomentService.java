package com.moyou.moyouRms.service.banner;

import java.util.List;

import com.moyou.moyouRms.model.banner.Banner;
import com.moyou.moyouRms.response.ApiResult;

/**
 * 生活圈首页 Service
 * 
 * @author PzC.
 * @time 2017年2月27日 下午5:02:52
 * 
 */
public interface LifeMomentService
{
    List<Banner> queryAdSupplyBanner();
    
    /**
     * 查询直播banner
     */
    List<Banner> queryLiveBanner();

    List<Banner> queryBanner();

    /**
     * 根据bannerID删除一条
     * 
     * @param id
     * @return
     */
    int deleteBanner(Integer id);

    /**
     * 添加一条Banner
     * 
     * @param banner
     */
    int insertBanner(Banner banner);

    /**
     * 启用/禁用banner
     * 
     * @param id
     * @return
     */
    ApiResult updateState(Banner banner);

    /**
     * 查询banner详情
     * 
     * @param id
     * @return
     */
    Banner queryBannerInfo(Integer id);

    /**
     * 修改banner排序
     * 
     * @param id
     * @return
     */
    ApiResult updateOrderNo(Banner banner);

    int insertAdSupplyBanner(Banner banner);
}
