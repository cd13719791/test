package com.moyou.moyouRms.controller.diary;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryH5Insert;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycomment.DiaryCommentService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.diarycontentrobot.DiaryContentRobotService;
import com.moyou.moyouRms.service.diaryrobot.DiaryRobotService;

@Controller
@RequestMapping("diaryRobot")
@ResponseBody
public class DiaryRobotController extends BaseController
{
    @Resource
    private DiaryService diaryService;
    @Resource
    private DiaryContentService diaryContentService;
    @Resource
    private DiaryCommentService diaryCommentService;
    @Resource
    private DiaryRobotService diaryRobotService;
    @Resource
    private DiaryContentRobotService diaryContentRobotService;

    /**
     * 专辑库 初始化接口
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/queryDiaryRobot", method = RequestMethod.POST)
    public ApiResult queryDiaryRobot(@RequestBody Map<String, Object> record)
    {
        Assert.notNull(record, "参数不能为空");
        PageBean pb = this.getJsonWrapPageBean(record);
        List<DiaryRobot> diaryRobotList = diaryRobotService.queryDiaryRobotFirstResourceList(pb);
        pb.setResults(diaryRobotList);
        return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
    }

    /**
     * 故事详情
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/querydiarybydiaryid", method = RequestMethod.POST)
    public ApiResult queryDiaryByDiaryId(@RequestBody Map<String, Object> record)
    {
        Assert.notNull(record, "参数不能为空");
        return new ApiResult(RESPONSE.SUCCESS,
            "成功",
            diaryRobotService.queryDiaryByDiaryId(Integer.valueOf(record.get("id").toString())));
    }

    /**
     * 专辑库 统计查询
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/selectDiaryRobotCount", method = RequestMethod.POST)
    public ApiResult selectDiaryRobotCount()
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", diaryRobotService.selectDiaryCount());
    }

    /**
     * 修改发布状态
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/updatePushStateByPrimaryKey", method = RequestMethod.POST)
    public ApiResult updatePushStateByPrimaryKey(@RequestBody DiaryRobot record)
    {
        return new ApiResult(RESPONSE.SUCCESS, "成功", diaryRobotService.updatePushStateByPrimaryKey(record));
    }

    /**
     * h5代码 发布diary
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/insertdiarybyh5", method = RequestMethod.POST)
    public ApiResult insertDiarybyH5(@RequestBody DiaryH5Insert record)
    {
        int index = diaryRobotService.insertDiarybyH5(record);
        String msg = "添加失败";
        if (index == 1)
        {
            msg = "添加成功";
        }
        else if (index == 3)
        {
            msg = "人不够了";
        }
        return new ApiResult(RESPONSE.SUCCESS, "成功", msg);
    }
}
