package com.moyou.moyouRms.service.adsupplyservice.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.dao.adsupply.AdSupplyCommentMapper;
import com.moyou.moyouRms.dao.adsupply.AdSupplyMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.adsupply.AdSupply;
import com.moyou.moyouRms.model.adsupply.AdSupplyComment;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.adsupplyservice.AdSupplyService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月18日 上午10:30:12 类说明 广告
 */
@Service
public class AdSupplyServiceImpl implements AdSupplyService
{
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AdSupplyMapper adSupplyMapper;
    @Resource
    private AdSupplyCommentMapper adSupplyCommentMapper;
    @Resource
    private UserFundLogService userFundLogService;
    @Resource
    private UserFundService userFundService;
    @Resource
    private MsgSystemXService msgSystemXService;
    @Resource
    private UserService userService;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AdSupply record)
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.insert(record);
    }

    @Override
    public int insertSelective(AdSupply record)
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.insertSelective(record);
    }

    @Override
    public AdSupply selectAdsupplyDetailByPrimaryKey(Integer id)
    {
        AdSupply adSupply = adSupplyMapper.selectAdsupplyDetailByPrimaryKey(id);
        adSupply.setAdSupplyCommentList(adSupplyCommentMapper.selectByAdsupplyId(adSupply.getId()));
        return adSupply;
    }

    @Override
    public int updateByPrimaryKeySelective(AdSupply record, HttpServletRequest req)
    {
        // TODO Auto-generated method stub
        record.setAuditTime(new Date());
        record.setUpdateTime(new Date());
        UserFundLog userFundLog = null;
        Integer userId = null;
        AdSupply oldAdSupply = adSupplyMapper.selectAdsupplyDetailByPrimaryKey(record.getId());
        userId = oldAdSupply.getCreatorId();
        // 如果审核未通过 就返回金币
        if (record.getAudit() == AdSupply.AUDIT_NO)
        {
            if (oldAdSupply.getAudit() != AdSupply.AUDIT_DEFAULT)
            {
                // 已经审核过了。
                return RESPONSE.ERROR;
            }
            Map<String, Object> map = JsonUtil.toMap(oldAdSupply.getExtendData());
            userFundLog =
                userFundLogService.selectUserFundLogByTypeAndId(UserFundModeEnum.SUPPLY_PALAZA_TYPE.getValue(),
                    record.getId());// 查询消耗金币

            Integer goldNumber = 0;
            if (userFundLog != null && userFundLog.getUserFund() != null)
            {
                goldNumber = userFundLog.getUserFund();// 用户消耗的金币
            }
            if (StringUtil.isNotEmpty(goldNumber) && goldNumber.intValue() != 0)
            {
                Map<String, Object> auditMap = JsonUtil.toMap(record.getExtendData());
                if (auditMap != null && map != null)
                {
                    auditMap.putAll(map);
                }
                record.setExtendData(JsonUtil.toJson(auditMap));
                UserFund userFund = new UserFund();
                userFund.setUserId(userId);
                userFund.setUserGold(goldNumber * -1);
                userFundService.updateUserGold(userFund,
                    UserFundModeEnum.SYSTEM_GOLD_BACK.getValue(),
                    oldAdSupply.getId(),
                    req);
            }
        }
        MsgSystemX msgSystemX = new MsgSystemX();
        msgSystemX.setMsgSendType(CONSTANT.SYS_AUDIT_ADSUPPLY_TYPE);
        msgSystemX.setMsgTitle(CONSTANT.SYS_AUDIT_ADSUPPLY);
        msgSystemX.setReceiveUserId(userId);
        msgSystemX.setMsgContent(record.getMsg());
        msgSystemX.setSendUserId(StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
            CONSTANT.SYS_CONF_PATH)));
        msgSystemXService.addSysMsgAndPushCustomMsg(msgSystemX, record.getMsg());
        return adSupplyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdSupply record)
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AdSupply> selectAdSupplyList(PageBean pb)
    {
        // TODO Auto-generated method stub
        List<AdSupply> list = adSupplyMapper.selectAdSupplyList(pb);
        list.stream().forEach(s -> {
            s.setPicCount(s.getPic().split(",").length);
            Map<String, Object> map = JsonUtil.toMap(s.getExtendData());
            if (map != null && StringUtil.isNotEmpty(map.get("auditer")))
            {
                s.setAuditer(map.get("auditer").toString());
            }
        });
        return list;
    }

    @Override
    public Map<String, Object> selectAdsupplyCount()
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.selectAdsupplyCount();
    }

    @Override
    public int deleteAdsupply(Integer valueOf)
    {
        // TODO Auto-generated method stub
        AdSupply adSupply = new AdSupply();
        adSupply.setId(valueOf);
        adSupply.setState(AdSupply.DELETE);
        return adSupplyMapper.updateByPrimaryKeySelective(adSupply);
    }

    @Override
    public Map<String, Object> selectAdsupplyAllCount()
    {
        // TODO Auto-generated method stub
        return adSupplyMapper.selectAdsupplyAllCount();
    }

    @Override
    public ApiResult insertAdSupplyComment(AdSupplyComment adSupplyComment)
    {
        // TODO Auto-generated method stub
        adSupplyComment.setCreateTime(new Date());
        adSupplyComment.setState(AdSupplyComment.STATE_OK);
        adSupplyComment.setUserId(userService.queryFakeUserLimit1().getUserId());
        if (adSupplyCommentMapper.insert(adSupplyComment) == 1)
        {
            return new ApiResult(RESPONSE.SUCCESS, "成功");
        }
        else
        {
            return new ApiResult(RESPONSE.ERROR, "添加异常");
        }
    }

    @Override
    public ApiResult deleteAdSupplyComment(AdSupplyComment adSupplyComment)
    {
        // TODO Auto-generated method stub
        adSupplyComment.setState(AdSupplyComment.STATE_DELETE);
        if (adSupplyCommentMapper.updateByPrimaryKeySelective(adSupplyComment) == 1)
        {
            return new ApiResult(RESPONSE.SUCCESS, "成功");
        }
        else
        {
            return new ApiResult(RESPONSE.ERROR, "删除异常");
        }
    }

    @Override
    public ApiResult deleteAdSupply(AdSupply adSupply)
    {
        adSupply.setState(AdSupply.DELETE);
        if (adSupplyMapper.updateByPrimaryKey(adSupply) == 1)
        {
            return new ApiResult(RESPONSE.SUCCESS, "成功");
        }
        // TODO Auto-generated method stub
        return new ApiResult(RESPONSE.ERROR, "异常");
    }
}
