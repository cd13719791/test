package com.moyou.moyouRms.service.systalkmanagerinfo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.sysTalkManagerInfo.SysTalkManagerInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;
import com.moyou.moyouRms.service.systalkmanagerinfo.SysTalkManagerinfoService;
@Service
public class SysTalkManagerinfoServiceImpl implements SysTalkManagerinfoService {
@Resource
SysTalkManagerInfoMapper sysTalkManagerinfoMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysTalkManagerInfo record) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.insert(record);
	}

	@Override
	public int insertSelective(SysTalkManagerInfo record) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.insertSelective(record);
	}

	@Override
	public SysTalkManagerInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysTalkManagerInfo record) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysTalkManagerInfo record) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysTalkManagerInfo> selecTalkAccountInfoByAccount(PageBean pb) {
		// TODO Auto-generated method stub
		return sysTalkManagerinfoMapper.selecTalkAccountInfoByAccount(pb);
	}

	@Override
	public int insertSysTalkManagerInfo(Integer userId,String account) {
		// TODO Auto-generated method stub
		SysTalkManagerInfo sysTalkManagerInfo=new SysTalkManagerInfo();
		sysTalkManagerInfo.setUserId(userId);
		sysTalkManagerInfo.setAccountId(account);
		sysTalkManagerInfo=sysTalkManagerinfoMapper.selectSysTalkManagerInfoByUserAndAccountId(sysTalkManagerInfo);
		if(sysTalkManagerInfo==null){//没有就添加
			sysTalkManagerInfo=new SysTalkManagerInfo();
			sysTalkManagerInfo.setAccountId(account);
			sysTalkManagerInfo.setUserId(userId);
			sysTalkManagerInfo.setTalkNumber(1);
			sysTalkManagerInfo.setUserNumber(1);
			sysTalkManagerInfo.setCreateTime(new Date());
			return sysTalkManagerinfoMapper.insertSelective(sysTalkManagerInfo);
		}else {
			return	sysTalkManagerinfoMapper.updateTalkCountJIA1(sysTalkManagerInfo);
		}
	}

	@Override
	public Map<String,Object> selectAccountTalkInfo(Account account) {
		// TODO Auto-generated method stub
		Map<String,Object> map=sysTalkManagerinfoMapper.selectAccountTalkInfo(account);
		if(map.get("thisDayTalk")==null){
			map.put("thisDayTalk", 0);
		}
		return map;
	}
	
}
