package com.moyou.moyouRms.controller.msgsystem;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;

@RestController
@RequestMapping(value = "/msgSystem")
public class MsgSystemXController extends BaseController
{

    @Resource
    private MsgSystemXService msgSystemXService;

    /**
     * 添加一条警告（這裡需要推送）
     * 
     * @param MsgSystemX
     * @return
     */
    @RequestMapping(value = "/addWarn", method = RequestMethod.POST)
    public @ResponseBody ApiResult addWarn(@RequestBody MsgSystemX record)
    {
        record.setMsgSendType(CONSTANT.SYS_USER_WARNING);
        if (record.getWarnType() == MsgSystemX.CUSTOM)
        {
            record.setMsgTitle(CONSTANT.SYS_CONSTOM_WARNING_TITLE);
        }
        else
        {
            record.setMsgTitle(CONSTANT.SYS_USER_WARNING_TITLE);
        }
        return new ApiResult(RESPONSE.SUCCESS, "成功", msgSystemXService.addSysMsgAndPushCustomMsg(record,
            CONSTANT.JPUSH_CUSTOM_MSG_PUSH_CONTENT));
    }
}
