package com.moyou.moyouRms.service.sysadminrelateuser.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.sysAdminRelateUser.SysAdminRelateUserMapper;
import com.moyou.moyouRms.dao.system.account.AccountDao;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.user.UserMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysAdminRelateUser.SysAdminRelateUser;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.sysadminrelateuser.SysAdminRelateUserService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.PropertiesUtil;

@Service
public class SysAdminRelateUserServiceImpl implements SysAdminRelateUserService {
	@Resource
	SysAdminRelateUserMapper sysAdminRelateUserMapper;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	AccountDao accountDao;
	@Override
	public int insert(SysAdminRelateUser record) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper.insert(record);
	}

	@Override
	public int insertSelective(SysAdminRelateUser record) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper.insertSelective(record);
	}

	@Override
	public List<SysAdminRelateUser> selectSysAdminRelateUserByAccountId(
			String account) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper
				.selectSysAdminRelateUserByAccountId(account);
	}

	@Override
	public ApiResult updateAccountUser(Integer userNumberStart, Integer userNumberEnd, String accountId) {
		// TODO Auto-generated method stub
		try {

			if(userNumberStart>userNumberEnd){//如果开始的数字 比结束的数字大 就交换下
			int temp=0;
			temp=userNumberStart;
			userNumberStart=userNumberEnd;
			userNumberEnd=temp;
		}
			int count=userMapper.queryFakeUserForIM();
			if(count<userNumberEnd){
				return new ApiResult(ResponseEnum.ERROR.getValue(),"修改人数不够。别瞎改.一共[ "+count+" ]人");
			};
		sysAdminRelateUserMapper.delete(new SysAdminRelateUser(accountId));//清空以前的记录
		userInfoMapper.selectFakeUserNotInRelatLimit(userNumberStart,userNumberEnd,PropertiesUtil.getValueByKey("crow_fade_user", CONSTANT.SYS_CONF_PATH))
		.stream().map(UserInfo::getUserId).forEach(userId->{
			sysAdminRelateUserMapper.insert(new SysAdminRelateUser(userId,accountId));
		});;
		accountDao.updateExtendData(new Account(accountId,userNumberStart+"_"+userNumberEnd));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功");
		} catch (Exception e) {
			return new ApiResult(ResponseEnum.ERROR.getValue(),e.getMessage());
		}
	}
	public static void main(String[] args) {
        List<String> parentList = new ArrayList<String>();
        
        for(int i = 0; i < 6; i++){
            parentList.add(String.valueOf(i));
        }
//        1 2 3 4 5   
        List<String> subList = parentList.subList(0,5);
        for(String s : subList){
            System.out.println(s);//output: 1, 2
        }
        
        //non-structural modification by sublist, reflect parentList
        subList.set(0, "new 1"); 
        for(String s : parentList){
            System.out.println(s);//output: 0, new 1, 2, 3, 4
        }
        
        //structural modification by sublist, reflect parentList
        subList.add(String.valueOf(2.5));
        for(String s : parentList){
            System.out.println(s);//output:0, new 1, 2,    2.5, 3,    4
        }
        
        //non-structural modification by parentList, reflect sublist
        parentList.set(2, "new 2");
        for(String s : subList){
            System.out.println(s);//output: new 1, new 2
        }
        
        //structural modification by parentList, sublist becomes undefined(throw exception)
        parentList.add("undefine");
//        for(String s : subList){
//            System.out.println(s);
//        }
//        subList.get(0);
    }

	@Override
	public int selectSysAdminRelateUserCountByAccountId(String account) {
		// TODO Auto-generated method stub
		return Integer.valueOf(sysAdminRelateUserMapper.selectSysAdminRelateUserCountByAccountId(account).get("userCount").toString());
	}

	@Override
	public int updateAccountOfflineCount(String valueOf) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper.updateAccountOfflineCount(valueOf);
	}

	@Override
	public List<SysAdminRelateUser> selectSysAdminRelateUserByAccountIdPage(
			PageBean pb) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper.selectSysAdminRelateUserByAccountIdPage(pb).stream().peek(s ->{
			if(s.getBirthday()!= null){
				s.setAge(Integer.valueOf(DateTimeUtil.getPersonAgeByBirthDate(s.getBirthday())));
			}
		}).collect(Collectors.toList());
	}

	@Override
	public int updateAccountOfflineCountByUserId(
			SysAdminRelateUser sysAdminRelateUser) {
		// TODO Auto-generated method stub
		return sysAdminRelateUserMapper.updateAccountOfflineCountByUserId(sysAdminRelateUser);
	}

	
}
