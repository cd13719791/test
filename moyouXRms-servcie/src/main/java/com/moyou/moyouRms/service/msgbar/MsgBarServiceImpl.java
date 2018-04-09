package com.moyou.moyouRms.service.msgbar;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.msgBar.MsgBarMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.pushmsg.PushMsgManager;
import com.moyou.moyouRms.model.msgBar.MsgBar;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;

@Service
public class MsgBarServiceImpl implements MsgBarService {
	@Resource
	private MsgBarMapper msgBarMapper;

	@Resource
	private PushMsgManager pushMsgManager;
	@Resource
	MsgSystemXService msgSystemXService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return msgBarMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MsgBar record) {
		// TODO Auto-generated method stub
		return msgBarMapper.insert(record);
	}

	@Override
	public int insertSelective(MsgBar record) {
		// TODO Auto-generated method stub
		return msgBarMapper.insertSelective(record);
	}

	@Override
	public MsgBar selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return msgBarMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgBar record) {
		// TODO Auto-generated method stub
		return msgBarMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MsgBar record) {
		// TODO Auto-generated method stub
		return msgBarMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MsgBar> queryMsgBarList(PageBean page) {
		// TODO Auto-generated method stub
		return msgBarMapper.queryMsgBarList(page);
	}

	@Override
	public ApiResult insertMsgBarList(MsgBar msgBar) {
		ApiResult apiResult = new ApiResult();
		if (msgBar.getPushTime() == null) {
			msgBar.setPushTime(new Date());
		}
		Date pushTime = msgBar.getPushTime();
		msgBar.setState(MsgBar.STATE_NO);
		msgBar.setCreateTime(new Date());
		msgBarMapper.insertSelective(msgBar);
		Timer time = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				MsgSystemX record = new MsgSystemX();
				record.setCreateTime(new Date());
				msgSystemXService.addSysMsgAndPushCustomMsgToAllUser(msgBar);
				msgBar.setState(MsgBar.STATE_OK);
				msgBarMapper.updateByPrimaryKeySelective(msgBar);
			}
		};
		time.schedule(task, pushTime);
		return apiResult;
	}

	@Override
	public List<MsgBar> selectUnPushMsgBar() {
		// TODO Auto-generated method stub
		return msgBarMapper.selectUnPushMsgBar();
	}

	@Override
	public void replayMsgBarList(MsgBar msgBar) {
		if (msgBar == null) {
			return;
		}
		Date pushTime = msgBar.getPushTime();
		Timer time = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				MsgSystemX record = new MsgSystemX();
				record.setCreateTime(new Date());
				msgSystemXService.addSysMsgAndPushCustomMsgToAllUser(msgBar);
				msgBar.setState(MsgBar.STATE_OK);
				msgBarMapper.updateByPrimaryKeySelective(msgBar);
			}
		};
		time.schedule(task, pushTime);
	}
}
