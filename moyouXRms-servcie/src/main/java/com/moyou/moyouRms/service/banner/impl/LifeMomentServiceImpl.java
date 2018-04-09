package com.moyou.moyouRms.service.banner.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.banner.LifeMomentMapper;
import com.moyou.moyouRms.dao.talkrobot.TalkRobotMapper;
import com.moyou.moyouRms.model.banner.Banner;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.banner.LifeMomentService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;

/**
 * 生活圈首页 Service
 * 
 * @author PzC.
 * @time 2017年2月27日 下午5:03:21
 * 
 */
@Service
public class LifeMomentServiceImpl implements LifeMomentService
{
    @Resource
    private LifeMomentMapper lifeMomentMapper;
    @Resource
    private TalkRobotService talkRobotService;
    @Resource
    private TalkRobotMapper talkRobotMapper;

    @Override
    public List<Banner> queryBanner()
    {
        return lifeMomentMapper.queryBanner();
    }

    @Override
    public int deleteBanner(Integer id)
    {
        return lifeMomentMapper.deleteBanner(id);
    }

    @Override
    public int insertBanner(Banner banner)
    {
        banner.setOrderNo(0);
        banner.setBannerDel(1);
        banner.setUpdateTime(new Date());
        banner.setState(0);
        banner.setCreateTime(banner.getUpdateTime());
        banner.setBannerName(null);
        return lifeMomentMapper.insertBanner(banner);
    }

    @Override
    public int insertAdSupplyBanner(Banner banner)
    {
        banner.setOrderNo(0);
        banner.setBannerDel(1);
        banner.setUpdateTime(new Date());
        banner.setState(0);
        banner.setCreateTime(banner.getUpdateTime());
        banner.setBannerName(null);
        // banner.setBannerType(Banner.ADSUPPLY);
        return lifeMomentMapper.insertBanner(banner);
    }

    /**
     * 启用/禁用banner
     * 
     * @param id
     * @return
     */
    @Override
    public ApiResult updateState(Banner banner)
    {
        lifeMomentMapper.updateState(banner);
        return new ApiResult(RESPONSE.SUCCESS, "修改成功");
    }

    @Override
    public Banner queryBannerInfo(Integer id)
    {
        return lifeMomentMapper.queryBannerInfo(id);
    }

    @Override
    public ApiResult updateOrderNo(Banner banner)
    {
        banner.setUpdateTime(new Date());
        return new ApiResult(RESPONSE.SUCCESS, "操作成功", lifeMomentMapper.updateOrderNo(banner));
    }

    @Override
    public List<Banner> queryAdSupplyBanner()
    {
        // TODO Auto-generated method stub
        return lifeMomentMapper.queryAdSupplyBanner();
    }

	@Override
	public List<Banner> queryLiveBanner() {
		// TODO Auto-generated method stub
		return lifeMomentMapper.queryLiveBanner();
	}

}
