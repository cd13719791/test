package com.moyou.moyouRms.dao.banner;

import java.util.List;

import com.moyou.moyouRms.model.banner.Banner;

/**
 * 生活圈首页相关
 * 
 * @author PzC.
 * @time 2017年2月27日 下午4:59:23
 * 
 */
public interface LifeMomentMapper
{
    /**
     * 初始化Banner 以前的banner 查询 只查询 type= 1,2,3 的
     * 
     * @return
     */
    List<Banner> queryBanner();

    /**
     * 查询广告banner
     * 
     * @return
     */
    List<Banner> queryAdSupplyBanner();
    /**
     * 查询直播banner
     */
    List<Banner> queryLiveBanner();
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
    int updateState(Banner banner);

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
    int updateOrderNo(Banner banner);

    /**
     * 查询banner总共推荐了多少
     * 
     * @return
     */
    int queryBannerState();

    /**
     * 根据id查询banner的排序
     * 
     * @param id
     * @return
     */
    int queryOrderNo(Integer id);
}
