package com.moyou.moyouRms.service.user;

	import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.dao.user.UserActiveInfoMapper;
import com.moyou.moyouRms.model.user.UserRegCondition;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.Md5Util;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.data.DataframeInDC;
import com.moyou.moyouRms.util.excel.ExcelReadX;

	public class UserAddFromExcelTest extends BaseJunit4 {
		@Autowired
		private QiNiuUploadService qiNiuUploadService;
		@Resource
		private CommonResourceService commonResourceService;
		@Resource
		private UserService userService;
		@Resource
		private UserActiveInfoMapper userActiveInfoMapper;
		@Resource
		private UserInfoService UserInfoService;
		private static final String BEGINDATE="1990-01-01";
		private static final String ENDDATE="2000-12-31";
		@SuppressWarnings({ "all" })
		@Test
		@Rollback(false)
		public void insert() {
			ExcelReadX er = new ExcelReadX();
			DataframeInDC dc;
			DataframeInDC dc2;
			try {
				/**
				 * start 读取excel 向数据库里插入数据
				 */
				dc = er.converter("E:\\群用户1000\\假账户.xlsx", "假账户");
				Map<String, ArrayList<Object>> dataMap = dc.getColValues();
				dc2 = er.converter("E:\\群用户1000\\城市人数.xlsx", "Sheet1");
				Map<String, ArrayList<Object>> cityMap = dc2.getColValues();
				List cityList=cityMap.get("城市名");
				List numberList=cityMap.get("人数");
				
				
				List number = dataMap.get("序号");
				List nickNameList = dataMap.get("昵称");
				List sexList = dataMap.get("性别");
//				List birthdayList=dataMap.get("生日");
				List addressList=dataMap.get("地区");
				List sigList=dataMap.get("签名");
				List pictureList=dataMap.get("头像");
//				number.forEach(numb -> {
				Integer indexForCity=0;
						for (Integer numb = 0; numb < number.size(); numb++) {
//							for (Integer numb = 2001; numb <10000 ; numb++) {
								if(StringUtil.isNotEmpty(numb)){
									if(numberList.get(0).toString().isEmpty()){
										System.out.println("添加完成");
										System.out.println("添加完成");
										System.out.println("添加完成");
										System.out.println("添加完成");
										System.out.println("添加完成");
										System.out.println("添加完成");
									}
								Integer cityNumber=Integer.valueOf(numberList.get(0).toString());
								String city="";
								city=cityList.get(0).toString();
								indexForCity++;
								if(indexForCity==cityNumber){
									cityList=cityList.subList(1, cityList.size());
									numberList=numberList.subList(1, numberList.size());
									indexForCity=0;
								}
								System.out.println(cityNumber +"----" +city+"---------"+numb);
					/**
					 * start 封装说说内容 用excel的序号作为循环次数控制，并且取出数据都为序号-1的值
					 * 例:list.get(numb-1);
					 */
					int	index=Integer.valueOf(numb+"");
					long phoneNumber = 50000000000L + index;// 假用户3开头，僵尸用户以5开头
						UserRegCondition regCondition = new UserRegCondition ();
						regCondition.setPhoneNumber(phoneNumber+"");
						regCondition.setPassword(Md5Util.getMd5("123456"));
						regCondition.setLongitude((0.1));
						regCondition.setLatitude((0.1));
						regCondition.setNickname(nickNameList.get(index).toString());
						regCondition.setSex(sexList.get(index).toString().equals("男") ? 0:1);
						regCondition.setGender(sexList.get(index).toString().equals("男") ? 0:1);
						regCondition.setBirthday(this.randomDate(BEGINDATE, ENDDATE));
						
						regCondition.setCity(city);
						
						regCondition.setSig(sigList.get(index).toString());
						regCondition.setIp("");
						regCondition.setType(com.moyou.moyouRms.model.user.User.ADMIN_CREATE);
						regCondition.setAcountType(1);
//					LIST<FILE> PICFILELIST = GETFILELIST("E:\\MOYOUX_ACCOUNT_DOC\\"
//							+ PICTURELIST.GET(0) + "\\");
					File avatorFolder = new File("E:\\群用户1000\\假用户头像\\"+(pictureList.get(index)+".jpg"));
					Msg msg = qiNiuUploadService.uploadImageFile(avatorFolder,
							UUIDUtil.getUUID() + ".jpg");
					if(msg.isSuccess()){
						Map<String, Object> map = JsonUtil.toMap(msg.getData());
						regCondition.setAvatar(StringUtil.getStr(map.get("QINIU_HTTP_URL")));
						System.err.println(regCondition.getNickname()+"***********************序号："+index);
						userService.addUser(regCondition);
					}
				}
						}
//					});
				/**
				 * end 读取excel 向数据库里插入数据
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static List<File> getFileList(String strPath) {
			File dir = new File(strPath);
			File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
			List<File> filelist = new ArrayList<File>();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					String fileName = files[i].getName();
					if (files[i].isDirectory()) { // 判断是文件还是文件夹
						getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
					} else if (fileName.toLowerCase().endsWith("jpg")) { // 判断文件名是否以.avi结尾
						String strFileName = files[i].getAbsolutePath();
						System.out.println("---" + strFileName);
						filelist.add(files[i]);
					} else {
						continue;
					}
				}

			}
			return filelist;
		}
		private static Date randomDate(String beginDate,String  endDate ){  
			 
			try {  
			 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			 
			Date start = format.parse(beginDate);//构造开始日期  
			 
			Date end = format.parse(endDate);//构造结束日期  
			 
			//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
			 
			if(start.getTime() >= end.getTime()){  
			 
			return null;  
			 
			}  
			 
			long date = random(start.getTime(),end.getTime());  
			 
			return new Date(date);  
			 
			} catch (Exception e) {  
			 
			e.printStackTrace();  
			 
			}  
			 
			return null;  
			 
			}  
			 
			private static long random(long begin,long end){  
			 
			long rtn = begin + (long)(Math.random() * (end - begin));  
			 
			//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
			 
			if(rtn == begin || rtn == end){  
			 
			return random(begin,end);  
			 
			}  
			 
			return rtn;  
			 
			}
		
		public static void main(String[] args) {
//			System.out.println(randomDate(BEGINDATE,ENDDATE));
		}
		@SuppressWarnings("unused")
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
//		private double getRandomCity(List<String> city,List<Integer> number) {
//			// 116.28 ~117.37
//			String addrArr[] = addr.split("~");
//			String max = StringUtil.getStr(addrArr[1]);
//			String min = StringUtil.getStr(addrArr[0]);
//			
//			String maxDoubleStr = (StringUtil.getDouble(max) * 100)+"";
//			String minDoubleStr = (StringUtil.getDouble(min) * 100)+"";
//			
//			int maxDouble = StringUtil.getInt(maxDoubleStr.substring(0, maxDoubleStr.lastIndexOf(".")));
//			int minDouble = StringUtil.getInt(minDoubleStr.substring(0, minDoubleStr.lastIndexOf(".")));
//			
//			Random ran = new Random();
//			int chaInt =maxDouble - minDouble;
//			int tmp = ran.nextInt(chaInt);
//			DecimalFormat df2 = new DecimalFormat("######0.00");
//		
//			String returnNum = 	df2.format((minDouble + tmp)/100.0);
//			double doubl = Double.parseDouble(returnNum);
//			return doubl;
//		}27392 28394
		
	}

