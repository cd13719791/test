package com.moyou.moyouRms.controller.pushmsg;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgBar.MsgBar;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.msgbar.MsgBarService;
import com.moyou.moyouRms.service.task.pushmsg.PushMsgService;

@Controller
@RequestMapping(value = "/pushMsg")
public class PushMsgController extends BaseController
{

    @Resource
    private PushMsgService pushMsgService;
    @Resource
    private MsgBarService msgBarService;

    /**
     * 初始化运营推送所有数据
     * 
     * @param pageBean
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/queryPushMsgList", method = RequestMethod.POST)
    public @ResponseBody ApiResult queryPushMsgList(@RequestBody Map<String, Object> map)
    {
        map.put("state", MsgArticle.STATE_DEFAULT);
        PageBean page = this.getJsonWrapPageBean(map);
        page.setResults(pushMsgService.queryPushMsgList(page));
        return new ApiResult(RESPONSE.SUCCESS, "请求成功", page);
    }

    /**
     * 初始化运营推送所有数据
     * 
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/querypushmsg", method = RequestMethod.POST)
    public @ResponseBody ApiResult querypushmsg(@RequestBody Map<String, Object> map)
    {
        return new ApiResult(RESPONSE.SUCCESS,
            "请求成功",
            pushMsgService.selectById(Integer.valueOf(map.get("id").toString())));
    }

    /**
     * 添加一条运营推送
     * 
     * @param pushMsg
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/insertPushMsg", method = RequestMethod.POST)
    public @ResponseBody ApiResult insertPushMsg(@RequestBody MsgArticle pushMsg) throws ParseException
    {
        String loginName = "未登录";
        if (super.getAdminUser() != null)
        {
            loginName = super.getAdminUser().getLoginName();
        }
        pushMsg.setAccount(loginName);
        return pushMsgService.insertPushMsg(pushMsg);
    }

    /**
     * 根据Id删除一条运营推送
     * 
     * @param pushMsg
     * @return
     */
    @RequestMapping(value = "/deletePushMsg", method = RequestMethod.POST)
    public @ResponseBody ApiResult deletePushMsg(@RequestBody MsgArticle pushMsg)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", pushMsgService.deletePushMsg(pushMsg));
    }

    /**
     * 修改推送文章
     * 
     * @param pushMsg
     * @return
     */
    @RequestMapping(value = "/updatePushMsg", method = RequestMethod.POST)
    public @ResponseBody ApiResult updatePushMsg(@RequestBody MsgArticle pushMsg)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", pushMsgService.updatePushMsg(pushMsg));
    }

    /**
     * 初始化通知栏推送所有数据
     * 
     * @param pageBean
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/querymsgbarlist", method = RequestMethod.POST)
    public @ResponseBody ApiResult queryMsgBarList(@RequestBody Map<String, Object> map)
    {
        PageBean page = this.getJsonWrapPageBean(map);
        page.setResults(msgBarService.queryMsgBarList(page));
        return new ApiResult(RESPONSE.SUCCESS, "请求成功", page);
    }

    /**
     * 发布通知栏推送
     * 
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/insertmsgbar", method = RequestMethod.POST)
    public @ResponseBody ApiResult insertMsgBarList(@RequestBody MsgBar msgBar)
    {
        if (this.getAdminUser() != null)
        {
            msgBar.setAccount(this.getAdminUser().getLoginName());
        }
        else
        {
            msgBar.setAccount("未登录");
        }
        return msgBarService.insertMsgBarList(msgBar);
    }
}
