package com.moyou.moyouRms.controller.h5;

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
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycomment.DiaryCommentService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.util.StringUtil;

@Controller
@RequestMapping("h5/diary")
@ResponseBody
public class H5DiaryController extends BaseController
{

    @Resource
    private DiaryService diaryService;
    @Resource
    private DiaryContentService diaryContentService;
    @Resource
    private DiaryCommentService diaryCommentService;

    /**
     * 日记详情接口
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/selectH5DiaryByDiaryId", method = RequestMethod.POST)
    public ApiResult selectH5DiaryByDiaryId(@RequestBody Map<String, Integer> record)
    {
        Assert.notNull(record.get("diaryId"), "参数不能为空");

        return new ApiResult(RESPONSE.SUCCESS,
            "成功",
            diaryService.selectH5DiaryByDiaryId(Integer.valueOf(record.get("diaryId") + "")));
    }

    /**
     * 日记初始化接口
     * 
     * @param record
     * @return
     */
    @SuppressWarnings({"unchecked" })
    @RequestMapping(value = "/selectdiarysbycreatorId", method = RequestMethod.POST)
    public ApiResult selectDiarysBySelctive(@RequestBody Map<String, Object> record)
    {
        Assert.notNull(record, "参数不能为空");
        if (StringUtil.isEmpty(record.get("orderBy")))
        {
            record.put("orderBy", 0);
        }
        PageBean pb = super.getJsonWrapPageBean(record);
        List<Diary> list = diaryService.selectDiaryListBySelective(pb);
        list.forEach(diary -> {
            // diary.setFirstTextAndPic(diaryContentService.selectFirstDiaryContentListByDiaryId(diary.getId()));
            diary.setDiaryContents(diaryContentService.selectDiaryContentListByDiaryId(diary.getId()));
        });
        pb.setResults(list);
        return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
    }

    /**
     * 日记详情接口
     * 
     * @param record
     * @return
     */
    @RequestMapping(value = "/selectDiaryDetailByDiaryId", method = RequestMethod.POST)
    public ApiResult selectDiaryDetailByDiaryId(@RequestBody Map<String, Integer> record)
    {
        Assert.notNull(record.get("id"), "参数不能为空");
        Diary diary = diaryService.selectByPrimaryKey(Integer.valueOf(record.get("id") + ""));
        if (null == diary)
        {
            return new ApiResult(RESPONSE.ERROR, "失败");
        }
        diary.setDiaryContents(diaryContentService.selectDiaryContentListByDiaryId(diary.getId()));
        // List<DiaryComment> list=diaryCommentService.selectDiaryCommentListByDiaryId(diary.getId());
        // list=this.setChildComment(list);
        // diary.setDiaryComments(list);
        return new ApiResult(RESPONSE.SUCCESS, "成功", diary);
    }
}
