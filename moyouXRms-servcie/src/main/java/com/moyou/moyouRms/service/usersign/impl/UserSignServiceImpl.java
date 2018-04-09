package com.moyou.moyouRms.service.usersign.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userSign.UserSignMapper;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.model.userSign.UserSign;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.service.usersign.UserSignService;
import com.moyou.moyouRms.service.usersignlog.UserSignLogService;

@Service
public class UserSignServiceImpl implements UserSignService {
	/***
	 * 是否连续签到 1是 0否
	 */
	// private static final int CONTINUOUSATTENDANCE_YES = 1;
	// private static final int CONTINUOUSATTENDANCE_NO = 0;
	@Resource
	private UserSignMapper userSignMapper;
	// @Resource
	// private UserGoldRuleMapper userGoldRuleMapper;
	@Resource
	private UserGoldService userGoldService;
	@Resource
	private UserSignLogService userSignLogService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userSignMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserSign record) {
		return userSignMapper.insert(record);
	}

	@Override
	public int insertSelective(UserSign record) {
		return userSignMapper.insertSelective(record);
	}

	@Override
	public UserSign selectByPrimaryKey(Integer id) {
		return userSignMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserSign record) {
		return userSignMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserSign record) {
		return userSignMapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据用户Id 获取签到数据
	 */
	@Override
	public UserSign getUSerSignByUserId(String userId) {
		// TODO Auto-generated method stub
		return userSignMapper.getUSerSignByUserId(userId);
	}

	/**
	 * 用户签到
	 */
	@Override
	public UserGoldRule addUserCheck(String userId) {
		// TODO Auto-generated method stub
		// UserSign userSign=this.getUSerSignByUserId(userId);
		UserGoldRule result = null;
		// /**
		// * 判断用户是否是连续签到
		// */
		// Date
		// yesterDay=DateTimeUtil.convertLocalDateToDate(DateTimeUtil.convertDateToLocalDate().minusDays(1));
		// Date
		// toDay=DateTimeUtil.convertLocalDateToDate(DateTimeUtil.convertDateToLocalDate().minusDays(0));
		// if(userSign!=null){//判断是否签到过
		//
		// String userCheckDate=DateTimeUtil.formatDate(userSign.getLastDate(),
		// "yyyy-MM-dd");
		// Date
		// userDate=DateTimeUtil.convertLocalDateToDate(DateTimeUtil.convertDateToLocalDate(userSign.getLastDate()));
		// if(userDate.equals(yesterDay)){//昨天签到过
		// result=insertUserSignGoldAndLog(userId,CONTINUOUSATTENDANCE_YES);
		// }else if(userDate.equals(toDay)){
		// return result;
		// }else{//昨天没签到
		// result=insertUserSignGoldAndLog(userId,CONTINUOUSATTENDANCE_NO);
		// }
		// }else{//没签到过
		// result= insertUserSignGoldAndLog(userId,CONTINUOUSATTENDANCE_NO);
		// }
		return result;
	}

	/**
	 * 用户签到，并维护表关系
	 * 
	 * @param ContinuousAttendance
	 *            0 没有连续签到 1连续签到
	 */
	// private UserGoldRule insertUserSignGoldAndLog(String userId, int
	// continuousAttendance)
	// {
	// UserGoldRule userGoldRule = new UserGoldRule();
	// try
	// {
	// UserSign userSign = this.getUSerSignByUserId(userId);
	// UserGold userGold = userGoldService.selectByUserIdLock(userId);
	// SignEnum signEnum = null;
	// /**
	// * 根据是否连续签到 获取签到规则
	// */
	// if (continuousAttendance == 1)
	// {
	// signEnum = SignEnum.getByValue(userSign.getContinuousSignCount() + 1);
	// }
	// else
	// {
	// signEnum = SignEnum.FIRST_DAY;
	// }
	// userGoldRule.setModeRuleType((short) signEnum.getValue());
	// userGoldRule.setModeType(ModeRuleEnum.SIGN_MODE.getValue());
	// userGoldRule =
	// userGoldRuleMapper.selectByModeTypeAndRuleId(userGoldRule);
	/**
	 * 维护表用户签到统计数据
	 */
	// if (userSign != null)
	// {
	// userSign.setLastDate(new Date());
	// userSign.setSignCount(userSign.getSignCount() + 1);
	// userSign.setContinuousSignCount(1 + userSign.getContinuousSignCount() *
	// continuousAttendance);// 如果不是连续签到就重置为1
	// // 如果是连续签到就是+1
	// this.updateByPrimaryKeySelective(userSign);
	// }
	// else
	// {
	// userSign = new UserSign();
	// userSign.setLastDate(new Date());
	// userSign.setUserId(Integer.valueOf(userId));
	// userSign.setContinuousSignCount(1);
	// userSign.setSignCount(1);
	// this.insertSelective(userSign);
	// }
	// /**
	// * 修改当前金币数量
	// */
	// if (userGold != null)
	// {
	// userGold.setUserGold(userGold.getUserGold() + userGoldRule.getGoldNum());
	// userGold.setUpdateTime(new Date());
	// userGoldService.updateByPrimaryKeySelective(userGold);
	// }
	// else
	// {
	// userGold = new UserGold();
	// userGold.setUserId(Integer.valueOf(userId));
	// userGold.setUserGold(userGoldRule.getGoldNum());
	// userGold.setUpdateTime(new Date());
	// userGoldService.insert(userGold);
	// }
	// /**
	// * 添加金币获取日志
	// */
	// UserSignLog userSignLog = new UserSignLog();
	// userSignLog.setCreateTime(new Date());
	// userSignLog.setSignGet("金币: " + userGoldRule.getGoldNum());
	// userSignLog.setUserId(Integer.valueOf(userId));
	// userSignLog.setSignSource(UserSignLog.USER_SIGN_LOG_H5);// h5 签到
	// userSignLogService.insert(userSignLog);
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return null;
	// }
	// return userGoldRule;
	// }

	/**
	 * 判断是否是昨天
	 * 
	 * @param createTime
	 * @return
	 */
	public static boolean isYesterday(Date a) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		Date today = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today).equals(format.format(a));

	}

	// public static void main(String[] args) {
	// SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/DD");
	// try {
	// System.out.println(isYesterday(sdf.parse("2017/02/20 01:00:00")));
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	@Override
	public List<UserInfo> selectOffLineUser(int day) {
		// TODO Auto-generated method stub
		return userSignMapper.selectOffLineUser(day);
	}

}
