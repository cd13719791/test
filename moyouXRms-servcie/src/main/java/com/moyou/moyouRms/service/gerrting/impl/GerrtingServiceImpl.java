package com.moyou.moyouRms.service.gerrting.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.moyou.moyouRms.dao.gerrting.GerrtingMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.gerrting.GreetingAbstract;
import com.moyou.moyouRms.model.gerrting.GreetingUserSendLog;
import com.moyou.moyouRms.model.gerrting.PicList;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.gerrting.GerrtingService;

@Service
public class GerrtingServiceImpl implements GerrtingService {

	@Resource
	private GerrtingMapper gerrtingMapper;
	@Resource
	private UserInfoMapper userInfoMapper;

	/**
	 * 添加一条祝福日志
	 */
	@Override
	public Map<String, Object> insertBlessingLog(
			GreetingUserSendLog greetingUserSendLog) {
		gerrtingMapper.insertBlessingLog(greetingUserSendLog);
		Map<String, Object> map = Maps.newHashMap();
		map.put("blessingId", greetingUserSendLog.getId());
		return map;
	}

	@Override
	public List<GreetingAbstract> queryBlessing() {
		return gerrtingMapper.queryBlessing();
	}

	/**
	 * 查询一条祝福语
	 */
	@Override
	public GreetingUserSendLog queryBlessingDetails(
			GreetingAbstract greetingAbstract) {
		GreetingUserSendLog greetingUserSendLog=new GreetingUserSendLog();
	UserInfo userInfo=	userInfoMapper.selectUsreNickNameAndAvatar(greetingAbstract.getFromId());
		greetingUserSendLog=gerrtingMapper.queryBlessingDetails(greetingAbstract.getBlessingId());
		greetingUserSendLog.setAvatar(userInfo.getAvatar());
		return greetingUserSendLog;
	}

	/**
	 * 卡片id查询图片集合
	 */
	@Override
	public List<PicList> queryBlessingPicList(Integer blessingId) {
		return gerrtingMapper.queryBlessingPicList(blessingId);
	}

	/**
	 * 初始化祝福语管理
	 * 
	 * @return
	 */

	@Override
	public List<GreetingAbstract> queryGreetingAbstractList(Page page) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(page.getPageNumber());
		pageBean.setPageSize(page.getPageSize());
		List<GreetingAbstract> results = gerrtingMapper
				.queryGreetingAbstractList(pageBean);
		page.setTotal(pageBean.getTotalRecord());
		page.setResults(results);
		return results;
	}

	@Override
	public int AddGreeting(GreetingUserSendLog greetingUserSendLog) {
		List<PicList> greetingAbstract = greetingUserSendLog
				.getGreetingAbstract();
		for (PicList greetingAbstract2 : greetingAbstract) {
			greetingAbstract2.setFestivalContent(greetingAbstract2
					.getFestivalContent());
			greetingAbstract2.setFestivalTitle(greetingAbstract2
					.getFestivalTitle());
			greetingAbstract2.setFestivalPicture(greetingAbstract2
					.getFestivalPicture());
			greetingAbstract2.setFestivalDate(greetingAbstract2
					.getFestivalDate());
			greetingAbstract2.setFestivalDateText(greetingAbstract2
					.getFestivalDateText());
			greetingAbstract2.setState(1);
			greetingAbstract2.setCreateTime(new Date());
			greetingAbstract2.setUpdateTime(new Date());
		}
		gerrtingMapper.insertGerrtingGreetingAbstractList(greetingAbstract);
		List<String> List2 = greetingAbstract.stream()
				.map(PicList::getFestivalPicture).collect(Collectors.toList());
		for (String string : List2) {
			List<Integer> id = gerrtingMapper
					.queryGreetingAbstracIdList(string);
			for (Integer integer : id) {
				greetingUserSendLog.setGreetingContent(greetingUserSendLog
						.getGreetingContent());
				greetingUserSendLog.setGreetingAppellation(greetingUserSendLog
						.getGreetingAppellation());
				greetingUserSendLog.setGreetingInscribe(greetingUserSendLog
						.getGreetingInscribe());
				greetingUserSendLog.setGreetingAbstractId(integer.intValue());
			}
			gerrtingMapper.insertBlessing(greetingUserSendLog);
		}
		return 1;
	}

	/**
	 * 根据节日ID删除封面图片
	 * 
	 * @return
	 */
	@Override
	public int deleteGreeting(GreetingAbstract greetingAbstract) {
		return gerrtingMapper.deleteGreeting(greetingAbstract);
	}

	/**
	 * 根据内容ID删除内容
	 * 
	 * @param greetingAbstract
	 * @return
	 */
	@Override
	public int deleteGreetingContent(GreetingAbstract greetingAbstract) {
		return gerrtingMapper.deleteGreetingContent(greetingAbstract);
	}

	@Override
	public int updateGreetingState(Integer state) {
		return gerrtingMapper.updateGreetingState(state);
	}

	/**
	 * 根据节日名查询节日封面图片和节日内容
	 * 
	 * @param festivalTitle
	 * @return
	 */
	@Override
	public List<GreetingAbstract> queryGreetingAbstractInfo(Page page) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(page.getPageNumber());
		pageBean.setPageSize(page.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("sortRule", page.getSortRule());
		pageBean.setConditions(map);
		List<GreetingAbstract> results = new ArrayList<GreetingAbstract>();
		results = gerrtingMapper.queryGreetingAbstractInfo(pageBean);
		page.setTotal(pageBean.getTotalRecord());
		page.setResults(results);
		return results;
	}

	/**
	 * 根据节日ID查询节日内容
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<GreetingAbstract> queryGreetingAbstract(Integer id) {
		return gerrtingMapper.queryGreetingAbstract(id);
	}

	/**
	 * 插入一条祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@Override
	public Integer insertBlessing(GreetingUserSendLog greetingUserSendLog) {
		return gerrtingMapper.insertBlessing(greetingUserSendLog);
	}

	/*
	 * 根据ID修改称呼和内容
	 */
	@Override
	public int updateGreetingContent(GreetingAbstract greetingAbstract) {
		return gerrtingMapper.updateGreetingContent(greetingAbstract);
	}
/**
 *  根据祝福语ID查询祝福语详情用于举报
 * @param BlessingId
 * @return
 */
	@Override
	public GreetingUserSendLog queryBlessingById(Integer BlessingId) {
		return gerrtingMapper.queryBlessingById(BlessingId)
				;
	}

}
