package com.moyou.moyouRms.service.user;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.moyou.moyouRms.model.provinceCity.ProvinceCity;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.provincecity.ProvinceCityService;
import com.moyou.moyouRms.service.talk.TalkService;

/**
 * 修改用户活跃时间
 * 
 * @author chenxu
 * @date 2017年9月11日
 * @version 1.0.0
 */
public class Testclass extends BaseJunit4 {
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceCityService provinceCityService;
	@Autowired
	private TalkService talkService;

	@Test
	public void unit() {
		try {
			taskCycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void taskCycle() {
		try {
			ProvinceCity provinceCity = new ProvinceCity();
			provinceCity.setDataType(3);// 1国2省3市4县5乡 获取城市
			// provinceCity.setName("南京");
			List<ProvinceCity> allCityList = provinceCityService
					.queryProvinceCityList(provinceCity);
			for (int i = 0; i < allCityList.size(); i++) {
				ProvinceCity p = allCityList.get(i);
				// allCityList.forEach(p -> {
				String city = p.getName();
				System.err.println(city);
				List<User> userList = userService.queryFakeUserByCity(city);// 在数据库获取假用户
				Collections.shuffle(userList);// 打乱数据顺序
				// 开始占位 目前规则是每个城市占位4个用户， 占位金币为200 24小时更新一次
				if (userList.size() >= 1) {
					talkService.createPlaceholder(userList.get(0).getUserId(), 200, city);
				}
				if (userList.size() >= 2) {
					talkService.createPlaceholder(userList.get(1).getUserId(), 200, city);
				}
				if (userList.size() >= 3) {
					talkService.createPlaceholder(userList.get(2).getUserId(), 200, city);
				}
				if (userList.size() >= 4) {
					talkService.createPlaceholder(userList.get(3).getUserId(), 200, city);
				}
				// });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
