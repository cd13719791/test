package com.moyou.moyouRms.service.statistics.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.statistics.UserFundRegulationDao;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.statistics.UserFundRegulation;
import com.moyou.moyouRms.service.statistics.UserFundRegulationService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class UserFundRegulationServiceImpl implements UserFundRegulationService {
	@Autowired
	private UserFundRegulationDao userFundRegulationDao;

	@Override
	public long getUserFundRegulationRowCount(Assist assist) {
		return userFundRegulationDao.getUserFundRegulationRowCount(assist);
	}

	@Override
	public List<UserFundRegulation> selectUserFundRegulation(Assist assist) {
		return userFundRegulationDao.selectUserFundRegulation(assist);
	}

	@Override
	public UserFundRegulation selectUserFundRegulationByObj(UserFundRegulation obj) {
		return userFundRegulationDao.selectUserFundRegulationByObj(obj);
	}

	@Override
	public UserFundRegulation selectUserFundRegulationById(Integer id) {
		return userFundRegulationDao.selectUserFundRegulationById(id);
	}

	@Override
	public int insertUserFundRegulation(UserFundRegulation value) {
		return userFundRegulationDao.insertUserFundRegulation(value);
	}

	@Override
	public int insertNonEmptyUserFundRegulation(UserFundRegulation value) {
		return userFundRegulationDao.insertNonEmptyUserFundRegulation(value);
	}

	@Override
	public int deleteUserFundRegulationById(Integer id) {
		return userFundRegulationDao.deleteUserFundRegulationById(id);
	}

	@Override
	public int deleteUserFundRegulation(Assist assist) {
		return userFundRegulationDao.deleteUserFundRegulation(assist);
	}

	@Override
	public int updateUserFundRegulationById(UserFundRegulation enti) {
		return userFundRegulationDao.updateUserFundRegulationById(enti);
	}

	@Override
	public int updateUserFundRegulation(UserFundRegulation value, Assist assist) {
		return userFundRegulationDao.updateUserFundRegulation(value, assist);
	}

	@Override
	public int updateNonEmptyUserFundRegulationById(UserFundRegulation enti) {
		return userFundRegulationDao.updateNonEmptyUserFundRegulationById(enti);
	}

	@Override
	public int updateNonEmptyUserFundRegulation(UserFundRegulation value, Assist assist) {
		return userFundRegulationDao.updateNonEmptyUserFundRegulation(value, assist);
	}

	public UserFundRegulationDao getUserFundRegulationDao() {
		return this.userFundRegulationDao;
	}

	public void setUserFundRegulationDao(UserFundRegulationDao userFundRegulationDao) {
		this.userFundRegulationDao = userFundRegulationDao;
	}

	@Override
	public List<UserFundRegulation> queryUserFundRegulationList(Map<String, Object> record) {
		List<UserFundRegulation> userFundRegulationList = userFundRegulationDao
				.queryUserFundRegulationList(record);
		try {
			Integer year = Integer.valueOf(record.get("year").toString());
			if (StringUtil.isNotEmpty(record.get("day"))
					&& StringUtil.isNotEmpty(record.get("month"))) {
				// 传了日 月 数据 查看当日的数据
				Integer day = Integer.valueOf(record.get("day").toString());
				Integer month = Integer.valueOf(record.get("month").toString());
				if (userFundRegulationList.size() == 0 || userFundRegulationList == null) {
					for (int i = 0; i < 24; i++) {
						userFundRegulationList.add(
								i,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												day, i, 0))));
					}
					return userFundRegulationList;
				}
				for (int i = 0; i < 24 && userFundRegulationList.size() > 0; i++) {

					if (userFundRegulationList.size() <= i) {
						userFundRegulationList.add(
								i,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												day, i, 0))));
					}
					if (DateTimeUtil.convertDateToLocalDateTime(
							userFundRegulationList.get(i).getCreateTime()).getHour() != i) {
						userFundRegulationList.add(
								i,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												day, i, 0))));
					}
				}
			} else if (StringUtil.isNotEmpty(record.get("month"))
					&& StringUtil.isEmpty(record.get("day"))) {// 查看当月数据
				Integer month = Integer.valueOf(record.get("month").toString());
				int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
				if (userFundRegulationList.size() == 0 || userFundRegulationList == null) {
					for (int i = 1; i <= maxDay; i++) {
						userFundRegulationList.add(
								i - 1,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												i, 0, 0))));
					}
					return userFundRegulationList;
				}
				for (int i = 1; i <= maxDay && userFundRegulationList.size() > 0; i++) {
					if (DateTimeUtil.convertDateToLocalDateTime(
							userFundRegulationList.get(i - 1).getCreateTime()).getDayOfMonth() != i) {
						userFundRegulationList.add(
								i - 1,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												i, 0, 0))));
						continue;
					}
					if (userFundRegulationList.size() == i && i < maxDay) {
						userFundRegulationList.add(
								i,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, month,
												i + 1, 0, 0))));
					}
				}
			} else if (StringUtil.isEmpty(record.get("month"))) {
				if (userFundRegulationList.size() == 0 || userFundRegulationList == null) {
					for (int i = 1; i <= 12; i++) {
						userFundRegulationList.add(
								i - 1,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, i, 1, 0,
												0))));
					}
					return userFundRegulationList;
				}
				for (int i = 1; i <= 12 && userFundRegulationList.size() > 0; i++) {
					// if (userFundRegulationList.size() <= i) {
					if (DateTimeUtil.convertDateToLocalDateTime(
							userFundRegulationList.get(i - 1).getCreateTime()).getMonthValue() != i) {
						userFundRegulationList.add(
								i - 1,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, i, 1, 0,
												0))));
						continue;
					}
					if (userFundRegulationList.size() == i && i < 12) {
						userFundRegulationList.add(
								i,
								new UserFundRegulation(0, DateTimeUtil
										.convertLocalDateTimeToDate(LocalDateTime.of(year, i + 1,
												1, 0, 0))));
					}
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userFundRegulationList;
	}
}