package com.moyou.moyouRms.service.user;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.user.GenerateNumberRecordMapper;
import com.moyou.moyouRms.dao.user.UserActiveInfoMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.model.user.GenerateNumberRecord;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.qiniu.service.QiNiuUploadService;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.util.UUIDUtil;

public class UserUpdateMoyouId extends BaseJunit4 {
	@Resource
	GenerateNumberRecordMapper numberRecordMapper;
	@Autowired
	private QiNiuUploadService qiNiuUploadService;
	@Resource
	private CommonResourceService commonResourceService;
	@Resource
	private UserService userService;
	@Resource
	private UserActiveInfoMapper userActiveInfoMapper;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	private UserInfoService UserInfoService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@SuppressWarnings({"all" })
	@Test
	@Rollback(false)
	/**
	 * 数据库莫有id重复 修改 假用户的moyouID
	 */
	public void insert() throws Exception {
		List<UserInfo> userInfos=UserInfoService.queryUserMoyouIdCount2();
		System.out.println("有["+userInfos.size()+"]个重复");
		if(userInfos.size()>0){
			for (int i = 0; i < userInfos.size(); i++) {
				String oldMouyouId=userInfos.get(i).getMoyouId();
				List<UserInfo> userInfo2=UserInfoService.queryByMoyouId(oldMouyouId);
				for (int j = 0; j < userInfo2.size(); j++) {
					if(userInfo2.get(j).getType()==1){
						UserInfo u=new UserInfo();
						u.setUserId(userInfo2.get(j).getUserId());
						String newMoyouId = initMoyouId().toString();
						u.setMoyouId(newMoyouId);
						UserInfoService.updateMoyouId(u);
					}
				}
			}
		}
	}
	/**
	 * 陌友 id
	 * 
	 * @return
	 */
	private Integer initMoyouId() throws Exception{
		int moyouId = 0;
		String moyouIdString = redisTemplate.opsForList().rightPop(CONSTANT.MOYOUID_LIST);
		if (StringUtils.isEmpty(moyouIdString)) {
			// moyouId取值区间
			GenerateNumberRecord numberRecord = numberRecordMapper.queryRangeOfMoyouId();
			int startNum = numberRecord.getStartNumber(), endNum = numberRecord.getEndNumber();
			int surplus = endNum - startNum;// 区间值
			if (numberRecord.getRegisterCount() >= surplus) {// 用户注册超过区间，需要新生成一条区间值记录
				startNum = endNum;
				endNum = startNum + surplus;
				GenerateNumberRecord newGenerateNumberRecord = new GenerateNumberRecord();
				newGenerateNumberRecord.setCreateTime(new Date());
				newGenerateNumberRecord.setStartNumber((startNum));
				newGenerateNumberRecord.setEndNumber((endNum));
				newGenerateNumberRecord.setRegisterCount(0);
				newGenerateNumberRecord.setId(UUIDUtil.getUUID());
				numberRecordMapper.insert(newGenerateNumberRecord);
			}
			List<String> moyouIdList = Lists.newArrayList();
			for (; startNum < endNum; startNum++) {
				moyouIdList.add(startNum + "");
			}
			Collections.shuffle(moyouIdList);
			redisTemplate.opsForList().leftPushAll(CONSTANT.MOYOUID_LIST, moyouIdList);
			moyouId = Integer.parseInt(redisTemplate.opsForList().rightPop(CONSTANT.MOYOUID_LIST));
		} else {
			moyouId = Integer.parseInt(moyouIdString);
		}
		boolean isExist = isExistMoyouId(moyouId+"");
		if (isExist) {// 存在，则再次回调方法获取id
			return initMoyouId();
		}
		return moyouId;
	}
	public boolean isExistMoyouId(String moyouId) {
		String id = userInfoMapper.queryContaninsMoyouId(moyouId);
		if (id != null) {
			return true;
		}
		return false;
	}
}
