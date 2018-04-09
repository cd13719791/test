package com.moyou.moyouRms.controller.userGold;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.LogEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.constants.enums.UserFundSrarchCategoryEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.intercept.Log;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

@Controller
@RequestMapping(value = "/userGold")
public class UserGoldController extends BaseController{
	private final static int DEFAULT_USER=0;
	@Resource
	private UserGoldService userGoldService;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserFundLogService userFundLogService;
	
	/**
	 * 修改用户金币
	 * @param map
	 * @return
	 */
	@Log(operationType="修改操作:",operationName="修改用户金币",operationLogEnum=LogEnum.UPDATE_GOLD)  
	@RequestMapping(value = "/updateUserGold", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserGold(@RequestBody Map<String,Object> map) {
		UserGold userGold=new UserGold();
		Integer userGoldNumber=Integer.valueOf(map.get("userGold").toString());
		userGold.setUserId(Integer.valueOf(map.get("userId").toString()));
		/**
		 *用户修改金币理由 
		 */
		String msg=map.get("msg").toString();
		if(StringUtil.isEmpty(msg)){
			return new ApiResult(ResponseEnum.ERROR,"失败,修改理由为空");
		}
		userGold.setUserGold(userGoldNumber);
		UserGold oldUserGold=userGoldService.selectByUserId(userGold.getUserId().toString());
		Integer newGold=null;
		if(oldUserGold!=null){
		newGold=oldUserGold.getUserGold()+userGold.getUserGold();
		userGold.setId(oldUserGold.getId());
		userGold.setUserGold(newGold);
		userGoldService.addOrUpdateGold(userGold.getUserId(),userGoldNumber);
		}else{
			userGold.setUpdateTime(new Date());
			userGoldService.insertSelective(userGold);
		}
		/**
		 * 插入修改日志
		 */
		UserFundLog userFundLog= new UserFundLog(); 
		userFundLog.setAudit(UserFundLog.AUDIT_SUCCESS);
		userFundLog.setClientIp(StringUtil.getIpAddr(super.getRequest()));
		userFundLog.setCreateTime(new Date());
		userFundLog.setPayUserId(DEFAULT_USER);//默认系统ID
		userFundLog.setPingxxState(UserFundLog.AUDIT_SUCCESS);
		Account account=this.getAdminUser();
		String adminName=account==null ? "未登录管理员":account.getName();
		userFundLog.setPayUserName(adminName);
		userFundLog.setModeType(UserFundModeEnum.SYSTEM_UPDATE.getValue());
		userFundLog.setReceiveUserId(userGold.getUserId());
		userFundLog.setSearchCategory(UserFundSrarchCategoryEnum.GOLD.getValue());
		userFundLog.setSearchUserid(userGold.getUserId());
		userFundLog.setUserFund(userGoldNumber);
		userFundLog.setReceiveUserName(userInfoMapper.queryUserNickName(userGold.getUserId()).getNickname());
		userFundLog.setTradeNumber(TradeNumberUtil.getTradeNumber());
		userFundLog.setExtnd("{'oldGold':"
				+ oldUserGold.getUserGold().toString() + ",'updateAdmin':'"
				+ adminName + "','newGold':" + (userGold.getUserGold())
				+ ",'msg':'" + msg + "'}");
		userFundLogService.insert(userFundLog);
		return new ApiResult(ResponseEnum.SUCCESS,"成功");
	}
}
