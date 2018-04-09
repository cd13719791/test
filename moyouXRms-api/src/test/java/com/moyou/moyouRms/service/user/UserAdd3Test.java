package com.moyou.moyouRms.service.user;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.user.UserRegCondition;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.util.ConstellationUtil;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.Md5Util;
import com.moyou.moyouRms.util.RandomStringUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.data.DataframeInDC;
import com.moyou.moyouRms.util.excel.ExcelReadX;
import com.moyou.moyouRms.util.file.CMyFileUtils;

/**
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserAdd3Test extends BaseJunit4 {
	@Autowired
	private UserService  service;
//	@Autowired
//	private QiNiuUploadService  qiNiuUploadService;
	@SuppressWarnings("all")
	@Test
	@Rollback(false)
	public void insert() {
		try {
			ExcelReadX er = new ExcelReadX();
			DataframeInDC dc = er.converter("E:\\moyou_doc\\假账户.xlsx", "精细用户450个");
//			DataframeInDC cityDoc = er.converter("E:\\moyou_doc\\圈子假账号资料\\正式服假用户\\城市经纬度.xlsx", "Sheet1");
//			Map<String, ArrayList<Object>> cityDataMap = cityDoc.getColValues();
			Map<String, ArrayList<Object>> dataMap = dc.getColValues();
			List number = dataMap.get("序号");
			List nicknameList = dataMap.get("昵称");
			List sexList = dataMap.get("性别");
			List birthdayList = dataMap.get("生日");
			List sigList = dataMap.get("个性签名");
			List addressList = dataMap.get("来自哪里");
//			List constellationList = dataMap.get("星座");
			if (nicknameList.size() > 0) {
				List<UserRegCondition> regConditionList = new ArrayList<UserRegCondition>();
//				List<UserReturnBaseInfo> resultUserList = new ArrayList<UserReturnBaseInfo>();
				int total = nicknameList.size();
				int bachCount = 50;
				int breakNum = 89;
				String flag = "";
			for (int i = 0; i < total; i++) {
				flag = ("1======"+i);
				if (i == 100) {
					 // break;
				}
					if (i < 50) {
						 //continue;
					}
					flag = ("2======"+i);
					System.out.println(flag);
					String nickname = StringUtil.getStr(nicknameList.get(i));
					String sexStr = StringUtil.getStr(sexList.get(i));
					int sex = 1;
					if ("女".equals(sexStr)) {
						sex = 2;
					}
					String sig = StringUtil.getStr(sigList.get(i));
					String address = StringUtil.getStr(addressList.get(i));
					String birthdayStr = StringUtil.getStr(birthdayList.get(i));
					String birthday = birthdayStr.replace(".", "-");
					Date birthdayDate = DateTimeUtil.toDate(birthday);

					String constellation = ConstellationUtil.getConstellation(birthdayDate);
					
					int num = StringUtil.getInt(number.get(i));
//					UserRegCondition regCondition  = getUser(nickname, sex, birthdayDate, address, constellation, sig, num, cityDataMap);
//					regConditionList.add(regCondition);
					if ((i+1) % (bachCount) == 0) {
						service.addUsers(regConditionList, "094b01355f4c4d71b5428cb469af4197", 1);
						regConditionList = new ArrayList<UserRegCondition>();
					}
				}
			service.addUsers(regConditionList, "094b01355f4c4d71b5428cb469af4197", 1);
			regConditionList = new ArrayList<UserRegCondition>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("all")
	private UserRegCondition getUser(String nickname, int sex, Date birthday, String address, String constellation, String sig, int num, Map<String, ArrayList<Object>>  cityDataMap) {
//		Map<String, ArrayList> 
		List cityList = cityDataMap.get("城市");
		List lngList = cityDataMap.get("经度");
		List latList = cityDataMap.get("纬度");
		int cityIndex = 0;
		for (int i = 0; i < cityList.size(); i++) {
			String city = StringUtil.getStr(cityList.get(i));
			if (address.equals(city)) {
				cityIndex = i;
				break;
			}
		}
		String lngStr = StringUtil.getStr(lngList.get(cityIndex));
		String latStr = StringUtil.getStr(latList.get(cityIndex));
		double lng = 0;
		double lat = 0;
		String city = StringUtil.getStr(cityList.get(cityIndex));
		if ("成都".equals(city)) {
			lng = 104.07;
			lat = 30.55;
		} else {
			lng = getRandom(lngStr);
			lat = getRandom(latStr);
		}
		Random ran = new Random();
		
		int len = (num +"").length();
		int flag = ran.nextInt(2);
		if (flag == 0) {
			lng += Double.parseDouble("0."+RandomStringUtil.randomLen(len));
		} else {
			lat += Double.parseDouble("0." +RandomStringUtil.randomLen(len));
		}
		
		long phoneNumber = 30000000000L + num;// 假用户3开头，僵尸用户以5开头
		UserRegCondition regCondition = new UserRegCondition ();
		regCondition.setPhoneNumber(phoneNumber+"");
		regCondition.setPassword(Md5Util.getMd5("123456"));
		
		regCondition.setLongitude(lng);
		regCondition.setLatitude(lat);
		regCondition.setNickname(nickname);
		regCondition.setSex(sex);
		regCondition.setBirthday(birthday);
		regCondition.setAddress(address);
		regCondition.setConstellation(constellation);
		regCondition.setSig(sig);
		regCondition.setIp("");
		regCondition.setType(com.moyou.moyouRms.model.user.User.ADMIN_CREATE);
		// 为每个用户生成头像
		File avatorFolder = new File("E:\\moyou_doc\\圈子假账号资料\\正式服假用户\\上传头像\\450精确用户头像\\"+num);
		File avtorFile = CMyFileUtils.getFileByFolder(avatorFolder, "1.jpg");
//		Msg msg = qiNiuUploadService.uploadImageFile(avtorFile, UUIDUtil.getUUID()+".jpg");
//		if (msg.isSuccess()) {
//			Map<String, Object> map = JsonUtil.toMap(msg.getData());
//			regCondition.setAvatar(StringUtil.getStr(map.get("QINIU_HTTP_URL")));
//		}
		return regCondition;
	}
	private double getRandom(String addr) {
		// 116.28 ~117.37
		String addrArr[] = addr.split("~");
		String max = StringUtil.getStr(addrArr[1]);
		String min = StringUtil.getStr(addrArr[0]);
		
		String maxDoubleStr = (StringUtil.getDouble(max) * 100)+"";
		String minDoubleStr = (StringUtil.getDouble(min) * 100)+"";
		
		int maxDouble = StringUtil.getInt(maxDoubleStr.substring(0, maxDoubleStr.lastIndexOf(".")));
		int minDouble = StringUtil.getInt(minDoubleStr.substring(0, minDoubleStr.lastIndexOf(".")));
		
		Random ran = new Random();
		int chaInt =maxDouble - minDouble;
		int tmp = ran.nextInt(chaInt);
		DecimalFormat df2 = new DecimalFormat("######0.00");
	
		String returnNum = 	df2.format((minDouble + tmp)/100.0);
		double doubl = Double.parseDouble(returnNum);
		return doubl;
	}
}
