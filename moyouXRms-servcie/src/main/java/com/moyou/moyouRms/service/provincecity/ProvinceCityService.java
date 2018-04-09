package com.moyou.moyouRms.service.provincecity;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.provinceCity.ProvinceCity;


/**
 * @describe 省市Service
 * @author liuxinyi
 * @date 2017年4月12日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public interface ProvinceCityService {
	/**
	 * 获取省市数据列表
	 * @param provinceCity
	 * @return
	 */
	List<ProvinceCity> queryProvinceCityList(ProvinceCity provinceCity);

	Map<String,List<ProvinceCity>> selectCity();

	int updateCity(List<ProvinceCity> list);
}
