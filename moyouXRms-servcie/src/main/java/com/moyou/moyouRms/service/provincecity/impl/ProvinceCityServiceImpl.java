package com.moyou.moyouRms.service.provincecity.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.provinceCity.ProvinceCityMapper;
import com.moyou.moyouRms.model.provinceCity.ProvinceCity;
import com.moyou.moyouRms.service.provincecity.ProvinceCityService;

@Service
public class ProvinceCityServiceImpl implements ProvinceCityService {
	@Resource
	private ProvinceCityMapper provinceCityMapper;

	@Override
	public List<ProvinceCity> queryProvinceCityList(ProvinceCity provinceCity) {
		return provinceCityMapper.queryProvinceCityList(provinceCity);
	}

	@Override
	public Map<String, List<ProvinceCity>> selectCity() {
		List<ProvinceCity> list = provinceCityMapper.selectCity();
		Map<String, List<ProvinceCity>> map = new HashMap<String, List<ProvinceCity>>();
		map.put("orderList", list.stream().filter(p -> p.getIsHot() > 0).sorted(Comparator.comparing(ProvinceCity::getIsHot)).collect(Collectors.toList()));
		map.put("unOrderList", list.stream().filter(p -> p.getIsHot()==0).collect(Collectors.toList()));
		return map;
	}

	@Override
	public int updateCity(List<ProvinceCity> list) {
		//修改所有的排序字段为0  
		provinceCityMapper.updateCityOrder();
		list.forEach(provinceCity ->{
			provinceCityMapper.updateByPrimaryKeySelective(provinceCity);
		});
		return ResponseEnum.SUCCESS.getValue();
	}

}