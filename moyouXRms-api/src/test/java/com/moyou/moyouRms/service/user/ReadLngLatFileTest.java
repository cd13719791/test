/**
 */
package com.moyou.moyouRms.service.user;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.dao.provinceCity.ProvinceCityMapper;
import com.moyou.moyouRms.dao.user.UserActiveInfoMapper;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserActiveInfo;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.util.file.CMyFileUtils;



/**
 * @describe 随机读取经纬度坐标文件
 * @author liuxinyi
 * @date 2017年3月23日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class ReadLngLatFileTest extends BaseJunit4 {
	@Resource
	UserInfoService userInfoService;
	@Resource
	UserService userService;
	@Resource
	UserActiveInfoMapper userActiveInfoMapper;
	@Resource
	ProvinceCityMapper provinceCityMapper;

	public static void main(String[] args) throws Exception {
		String str = CMyFileUtils.readFile("E:\\moyouX_doc\\假用户相关资料\\保定 100.txt");
		String strArr[] = str.split("\n");
		if (strArr != null && strArr.length > 0) {
			int len = strArr.length;
			Random ran = new Random();
			int index = ran.nextInt(len);
			System.out.println(strArr[index]);
		}
	}

	@Test
	@Rollback(false)
	public void insert() {
		List<User> user = userService.queryFakeUser();
		for (int i = 0; i < user.size(); i++) {
			User s = user.get(i);
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(s.getUserId());
			userInfo = userInfoService.selectUserInfoByUserId(userInfo);
			try {
				String str = CMyFileUtils.readFile("E:\\坐标\\" + userInfo.getCity()
						+ ".txt");
				String strArr[] = str.split("\n");
				if (strArr != null && strArr.length > 0) {
					int len = strArr.length;
					Random ran = new Random();
					int index = ran.nextInt(len);
					System.out.println(strArr[index]);
					System.out.println(strArr[index].split(",")[0]);
					System.out.println(strArr[index].split(",")[1]);
					UserActiveInfo activeInfo =	 new UserActiveInfo();;
					Integer userId=	userActiveInfoMapper.selectActiveInfoUserIdByUserId(s.getUserId());
					if (userId == null) {
						activeInfo.setUserId(s.getUserId());
						activeInfo.setLongitude(Double.valueOf(strArr[index]
								.split(",")[0]));
						activeInfo.setLatitude(Double.valueOf(strArr[index]
								.split(",")[1]));
						activeInfo.setMapFunction(1);
						activeInfo.setCreateTime(new Date());
						activeInfo.setOnlineState(1);
						userActiveInfoMapper.insertSelective(activeInfo);
					}else{
						
						activeInfo.setUserId(s.getUserId());
						activeInfo.setLongitude(Double.valueOf(strArr[index]
								.split(",")[0]));
						activeInfo.setLatitude(Double.valueOf(strArr[index]
								.split(",")[1]));
						activeInfo.setMapFunction(1);
						activeInfo.setCreateTime(new Date());
						activeInfo.setOnlineState(1);
						userActiveInfoMapper.updateUserActiveInfo(activeInfo);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
//	@Test
//	@Rollback(false)
//	public void insert2() {
//	List<File> list=	CMyFile.traverseFolder2("E:\\坐标\\");
//	if(list!=null){
//		for (File s : list) {
//			if(s.getName().endsWith(".txt")){
//				String name=s.getName().substring(0, s.getName().length()-4);
//				System.out.println(name);
//				ProvinceCity provinceCity=new ProvinceCity();
//				provinceCity.setId(UUIDUtil.getUUID());
//				provinceCity.setDataType(3);
//				provinceCity.setCreateTime(new Date());
//				provinceCity.setName(name);
//				provinceCity.setSearchContent(name);
//				provinceCity.setState(1);
//				provinceCityMapper.insertSelective(provinceCity);
//			}
//		}
//	}
//	}
}
