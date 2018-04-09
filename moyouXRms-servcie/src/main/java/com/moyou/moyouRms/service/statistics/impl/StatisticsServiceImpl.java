package com.moyou.moyouRms.service.statistics.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.dao.diaryRobot.DiaryRobotMapper;
import com.moyou.moyouRms.dao.feedback.FeedbackMapper;
import com.moyou.moyouRms.dao.gerrting.GerrtingMapper;
import com.moyou.moyouRms.dao.report.ReportMapper;
import com.moyou.moyouRms.dao.secret.SecretMapper;
import com.moyou.moyouRms.dao.secretRobot.SecretRobotMapper;
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.dao.talkrobot.TalkRobotCommentMapper;
import com.moyou.moyouRms.dao.talkrobot.TalkRobotMapper;
import com.moyou.moyouRms.dao.userSignLog.UserSignLogMapper;
import com.moyou.moyouRms.dao.userfund.UserFundMapper;
import com.moyou.moyouRms.model.statistics.Consume;
import com.moyou.moyouRms.model.statistics.Issue;
import com.moyou.moyouRms.model.statistics.MiniApps;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.statistics.NewUserResult;
import com.moyou.moyouRms.model.statistics.Robot;
import com.moyou.moyouRms.model.statistics.UserStatistics;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.service.statistics.StatisticsService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.JsonUtil;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	UserFundMapper userFundMapper;
	@Resource
	UserSignLogMapper userSignLogMapper;
	@Resource
	GerrtingMapper gerrtingMapper;
	@Resource
	TalkMapper talkMapper;
	@Resource
	SecretMapper secretMapper;
	@Resource
	DiaryMapper diaryMapper;
	@Resource
	FeedbackMapper feedbackMapper;
	@Resource
	ReportMapper reportMapper;
	@Resource
	TalkRobotMapper talkRobotMapper;
	@Resource
	DiaryRobotMapper diaryRobotMapper;
	@Resource
	SecretRobotMapper secretRobotMapper;
	@Resource
	TalkRobotCommentMapper talkRobotCommentMapper;
	@Resource
	UserService userService;

	@Override
	public Consume queryConsumeList() {
		Consume consume = new Consume();
		// consume.setCountAuditWithdraw(userFundMapper.queryAuditWithdraw()
		// .getCountAuditWithdraw());
		// consume.setCountMoney(userFundMapper.queryAuditWithdraw()
		// .getCountMoney());
		// consume.setCountHb(userFundMapper.queryCountHb().getCountHb());
		// consume.setCountHbMoney(userFundMapper.queryCountHb().getCountHbMoney());
		// consume.setCountSucceedMoney(userFundMapper.queryCountSucceedWithdraw()
		// .getCountSucceedMoney());
		// consume.setCountSucceedWithdraw(userFundMapper
		// .queryCountSucceedWithdraw().getCountSucceedWithdraw());
		// consume.setCountToDayWithdraw(userFundMapper.queryCountToDayWithdraw()
		// .getCountToDayWithdraw());
		// consume.setCounTtoDayWithdrawMoney(userFundMapper
		// .queryCountToDayWithdraw().getCounTtoDayWithdrawMoney());
		// consume.setCountGive(userFundMapper.queryCountGive().getCountGive());
		// consume.setCountGiveMoney(userFundMapper.queryCountGive()
		// .getCountGiveMoney());
		// consume.setCountUseFund(userFundMapper.queryCountUseFund()
		// .getCountUseFund());
		// consume.setCountFund(userFundMapper.queryCountFund().getCountFund());
		// consume.setCountAdminFund(userFundMapper.queryCountAdminFund()
		// .getCountAdminFund());
		// consume.setCountYesterDayHb(userFundMapper.queryCountYesterDayHb()
		// .getCountYesterDayHb());
		// consume.setCountYesterDayHbMoney(userFundMapper.queryCountYesterDayHb()
		// .getCountYesterDayHbMoney());
		// consume.setCountToDayHb(userFundMapper.queryCountToDayHb().getCountToDayHb());
		// consume.setCountToDayHbMoney(userFundMapper.queryCountToDayHb().getCountToDayHbMoney());
		// consume.setTalkGiveMoney(userFundMapper.queryTalkGiveMoney()
		// .getTalkGiveMoney());
		// consume.setCountTalkGive(userFundMapper.queryTalkGiveMoney()
		// .getCountTalkGive());
		// consume.setDiaryGiveMoney(userFundMapper.queryDiaryGiveMoney()
		// .getDiaryGiveMoney());
		// consume.setCountDiaryGvie(userFundMapper.queryDiaryGiveMoney()
		// .getCountDiaryGvie());
		// return consume;
		consume = userFundMapper.queryAllStatist();
		consume.setCountGiveMoney(consume.getCountGiveMoney() * -1);
		consume.setCountHbMoney(consume.getCountHbMoney() * -1);
		consume.setCountToDayHbMoney(consume.getCountToDayHbMoney() * -1);
		consume.setCountYesterDayHbMoney(consume.getCountYesterDayHbMoney() * -1);
		consume.setCountSucceedMoney(consume.getCountSucceedMoney() * -1);
		consume.setDiaryGiveMoney(consume.getDiaryGiveMoney() * -1);
		consume.setTalkGiveMoney(consume.getTalkGiveMoney() * -1);
		consume.setCountSucceedMoney(consume.getCountSucceedMoney() * -1);
		consume.setCountAuditWithdraw(consume.getCountAuditWithdraw() < 0 ? consume
				.getCountAuditWithdraw() * -1 : consume.getCountAuditWithdraw());
		consume.setTalkGiveMoney(consume.getTalkGiveMoney() * -1);
		consume.setDiaryGiveMoney(consume.getDiaryGiveMoney() * -1);
		return consume;
	}

	@Override
	public MiniApps queryConMiniApps() {
		MiniApps miniApps = new MiniApps();
		miniApps.setCountGerrting(gerrtingMapper.queryCountGerrting().getCountGerrting());
		miniApps.setCountSig(userSignLogMapper.queryCountSig().getCountSig());
		miniApps.setYesterdayGerrting(gerrtingMapper.queryYesterdayGerrting()
				.getYesterdayGerrting());
		miniApps.setYesterdaySig(userSignLogMapper.queryYesterdaySig().getYesterdaySig());
		return miniApps;
	}

	@Override
	@SuppressWarnings("all")
	public Issue queryIssue() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(talkMapper.queryStatistAllCount());
		map.putAll(talkMapper.queryStatistCount());
		map.putAll(diaryMapper.queryCountDiary());
		map.putAll(secretMapper.queryCountSecret());
		// try {
		// ExecutorService executors = Executors.newFixedThreadPool(Math.min(4,
		// 4));
		// Map<String, Object> completableFuture = CompletableFuture
		// .runAsync(() -> talkMapper.queryStatistAllCount(), executors)
		// .runAsync(() -> map.putAll(talkMapper.queryStatistCount()),
		// executors)
		// .runAsync(() -> map.putAll(diaryMapper.queryCountDiary()), executors)
		// .supplyAsync(() -> {
		// map.putAll(secretMapper.queryCountSecret());
		// return map;
		// }, executors).join();
		// return JsonUtil.toObject(completableFuture, Issue.class);
		// // secretMapper.queryCountSecret()
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// Issue issue = talkMapper.queryStatistAllCount();
		// issue.setCountPicTalk(talkMapper.queryCountTalkPic());
		// issue.setCountSecret(secretMapper.queryCountSecret().getCountSecret());
		// issue.setCountStory(diaryMapper.queryCountDiary().getCountStory());
		// issue.setCountTalk(talkMapper.queryCountTalk());
		// issue.setCountVidTalk(talkMapper.queryCountVideoTalk());
		// issue.setToDayCountTalk(talkMapper.queryNewCountTalk());
		// issue.setToDaySecret(secretMapper.queryCountSecret().getToDaySecret());
		// issue.setToDayStory(diaryMapper.queryCountDiary().getToDayStory());
		return JsonUtil.toObject(map, Issue.class);
	}

	@Override
	public UserStatistics queryUserStatistics() {
		UserStatistics userStatistics = new UserStatistics();
		userStatistics.setCountNFeedBack(feedbackMapper.queryCountYFeedBack().getCountNFeedBack());
		userStatistics.setCountYFeedBack(feedbackMapper.queryCountYFeedBack().getCountYFeedBack());
		userStatistics.setDispose(reportMapper.queryUserStatisticsReport().getDispose());
		userStatistics.setNoDispose(reportMapper.queryUserStatisticsReport().getNoDispose());
		return userStatistics;
	}

	@Override
	public Robot queryTalkRobot() {
		Robot robot = new Robot();
		robot.setTalkY(talkRobotMapper.queryTalkRobot().getTalkY());
		robot.setTalkN(talkRobotMapper.queryTalkRobot().getTalkN());
		robot.setCommentN(talkRobotCommentMapper.queryTalkComment().getCommentN());
		robot.setCommentY(talkRobotCommentMapper.queryTalkComment().getCommentY());
		robot.setDiaryN(diaryRobotMapper.queryDiaryY().getDiaryN());
		robot.setDiaryY(diaryRobotMapper.queryDiaryY().getDiaryY());
		robot.setSecretN(secretRobotMapper.querySecret().getSecretN());
		robot.setSecretY(secretRobotMapper.querySecret().getSecretY());

		return robot;
	}

	/**
	 * 资金池
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<UserFund> queryUserFundList(UserFund record) throws Exception {
		String time = record.getTime();
		String state = record.getState();
		String ymd = "";
		String states = "";
		int userFund = 0;
		switch (state) {
		case "1":// 年》每月
			states = "DATE_FORMAT(doc.update_time,'%Y-%m') AS dateTimes";
			break;
		case "2":// 月>每天
			states = "DATE_FORMAT(doc.update_time,'%Y-%m-%d') AS dateTimes";
			break;
		case "3":// 日》每时
			states = "DATE_FORMAT(doc.update_time,'%Y-%m-%d %H') AS dateTimes";
		default:
			break;
		}
		switch (time.length()) {
		case 4:
			ymd = "DATE_FORMAT(doc.update_time, '%Y') =" + time;
			break;
		case 7:
			ymd = "DATE_FORMAT(doc.update_time, '%Y-%m') = " + "'" + time + "'";
			break;
		case 10:
			ymd = "DATE_FORMAT(doc.update_time, '%Y-%m-%d') =" + "'" + time + "'";
		default:
			break;
		}
		List<UserFund> result = new ArrayList<UserFund>();
		switch (state) {
		case "1":// 年》每月
			List<UserFund> dbResult = userFundMapper.queryUserFundList(states, ymd);
			for (int i = 0; i < 12; i++) {
				String everyMonth = (time + "-" + (i + 1));
				StringBuilder sb = new StringBuilder(everyMonth);
				if (sb.length() == 6) {
					sb.insert(5, "0");
				}
				UserFund newUf = new UserFund();
				String everyMonth1 = sb.toString();
				newUf.setDateTimes(everyMonth1);
				newUf.setUserFund(new BigDecimal(Integer.toString(userFund)));
				for (int j = 0; j < dbResult.size(); j++) {
					if ((newUf.getDateTimes()).equals(dbResult.get(j).getDateTimes())) {
						newUf = dbResult.get(j);
						continue;
					}
				}
				result.add(newUf);
			}
			return result;
		case "2":// 月>每天
			List<UserFund> dbResult2 = userFundMapper.queryUserFundList(states, ymd);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for (int i = 0; i < getDaysOfMonth(sdf.parse(time)); i++) {
				String everyMonth = (time + "-" + (i + 1));
				StringBuilder sb = new StringBuilder(everyMonth);
				if (sb.length() == 9) {
					sb.insert(8, "0");
				}
				UserFund newUf = new UserFund();
				String everyMonth1 = sb.toString();
				newUf.setDateTimes(everyMonth1);
				newUf.setUserFund(new BigDecimal(Integer.toString(userFund)));
				for (int j = 0; j < dbResult2.size(); j++) {
					if ((newUf.getDateTimes()).equals(dbResult2.get(j).getDateTimes())) {
						newUf = dbResult2.get(j);
						continue;
					}
				}
				result.add(newUf);
			}
			return result;
		case "3":// 日》每时
			List<UserFund> dbResult3 = userFundMapper.queryUserFundList(states, ymd);
			for (int i = 0; i < 24; i++) {
				String everyMonth = (time + " " + (i + 1));
				StringBuilder sb = new StringBuilder(everyMonth);
				if (sb.length() == 12) {
					sb.insert(11, "0");
				}
				UserFund newUf = new UserFund();
				String everyMonth1 = sb.toString();
				newUf.setDateTimes(everyMonth1);
				newUf.setUserFund(new BigDecimal(Integer.toString(userFund)));
				for (int j = 0; j < dbResult3.size(); j++) {
					if ((newUf.getDateTimes()).equals(dbResult3.get(j).getDateTimes())) {
						newUf = dbResult3.get(j);
						continue;
					}
				}
				result.add(newUf);
			}
		}
		return result;
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	// public static void main(String[] args) throws ParseException {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// // System.out.println(getDaysOfMonth(sdf.parse("2017-12-01")));
	// }

	@Override
	public List<NewUserResult> queryUserCountList(NewUser record) {
		// TODO Auto-generated method stub

		try {
			return userService.queryUserCountList(record);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
