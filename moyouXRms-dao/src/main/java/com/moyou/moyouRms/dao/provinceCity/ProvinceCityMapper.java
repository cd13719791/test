package com.moyou.moyouRms.dao.provinceCity;

import java.util.List;

import com.moyou.moyouRms.model.provinceCity.ProvinceCity;

public interface ProvinceCityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProvinceCity record);

    int insertSelective(ProvinceCity record);

    ProvinceCity selectByPrimaryKey(String id);
    List<ProvinceCity> queryProvinceCityList (ProvinceCity record);

    int updateByPrimaryKeySelective(ProvinceCity record);

    int updateByPrimaryKey(ProvinceCity record);

	List<ProvinceCity> selectCity();
	/**
	 * 修改 isHot 排序字段全部为0 
	 * @return
	 */
	int updateCityOrder();
}