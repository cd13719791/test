package com.moyou.moyouRms.service.userfundtake.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.userFundTake.UserFundTakeMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.commonReason.CommonReason;
import com.moyou.moyouRms.model.userFundTake.UserFundTake;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.commonreason.CommonReasonService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.service.userfundtake.UserFundTakeService;

@Service
public class UserFundTakeServiceImpl implements UserFundTakeService {
	@Resource
	private UserFundTakeMapper userFundTakeMapper;
	@Resource
	private UserFundService userFundService;
	@Resource
	private CommonReasonService commonReasonService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserFundTake record) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.insert(record);
	}

	@Override
	public int insertSelective(UserFundTake record) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.insertSelective(record);
	}

	@Override
	public UserFundTake selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFundTake record) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserFundTake record) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserFundTake> selectUserFundTakeByState(PageBean pb) {
		// TODO Auto-generated method stub
		return userFundTakeMapper.selectUserFundTakeByState(pb);
	}

	@Override
	public ApiResult updateUserFundTake(UserFundTake userFundTake) {
		// TODO Auto-generated method stub
		Assert.notNull(userFundTake.getAuditStatus(), "修改状态为空");
		Assert.notNull(userFundTake.getId(), "修改记录id为空");
		int auditStatus = userFundTake.getAuditStatus();
//		String auditorId = userFundTake.getAuditorId();
//		int id = userFundTake.getId();
		if (auditStatus == UserFundTake.AGREE) {
			// 审核通过 start
			userFundTake = userFundTakeMapper.selectByPrimaryKey(userFundTake
					.getId());// 数据库查询是否有申请记录
			userFundTake.setAuditStatus(UserFundTake.AGREE);
			if (userFundService.addAndUpdateUserFund(userFundTake.getUserId(),
					userFundTake.getTakeFund())) {// 修改用户资金 修改成功后 修改提现记录状态
				userFundTake.setAuditTime(new Date());
				userFundTakeMapper.updateByPrimaryKeySelective(userFundTake);
			} else {
				return new ApiResult(RESPONSE.ERROR, "失败");
			}
			;
			// todo 调取提现方法
			// 审核通过 end
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else if (auditStatus == UserFundTake.NOT_AGREE) {
			// 审核失败 start
			Assert.notNull(userFundTake.getReasonText(), "拒绝理由参数不能为空");
			CommonReason commonReason = new CommonReason();
			commonReason.setModeType(CommonReason.USER_FUND_TAKE);
			commonReason.setModeId(userFundTake.getId());
			CommonReason oldCommonReason = commonReasonService
					.selectCommonReasonByObjectId(commonReason);
			commonReason.setReasonText(userFundTake.getReasonText());
			if (oldCommonReason == null) {
				commonReasonService.insert(commonReason);
			} else {
				commonReasonService.updateByPrimaryKeySelective(commonReason);
			}
			userFundTake.setAuditTime(new Date());
			userFundTakeMapper.updateByPrimaryKeySelective(userFundTake);
			// 审核失败 end
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
	}

}
