package com.moyou.moyouRms.service.province;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.provincecity.ProvinceCityService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;

/**
 * 省市县
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class ProvinceUnit extends BaseJunit4
{
    @Resource
    private ProvinceCityService provinceCityService;
    @Resource
    private UserFundLogService userFundLogService;

    @Test
    @Rollback(false)
    public void unit()
    {
        userFundLogService.selectUserFundLogByTypeAndId(UserFundModeEnum.SUPPLY_PALAZA_TYPE.getValue(), 0);
    }
}
