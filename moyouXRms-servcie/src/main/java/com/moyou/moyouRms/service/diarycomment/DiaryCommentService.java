package com.moyou.moyouRms.service.diarycomment;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryComment;

public interface DiaryCommentService {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryComment record);

    int insertSelective(DiaryComment record);

    DiaryComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryComment record);

    int updateByPrimaryKey(DiaryComment record);
    
    /**
     * 根据日记id 查询日记评论
     * @param id
     * @return
     */
    List<DiaryComment> selectDiaryCommentListByDiaryId(Integer id);
    /**
     * 根据日记id 查询日记评论    分页模型
     * @param pb
     * @return
     */
	List<DiaryComment> selectDiaryCommentListByDiaryIdLimit(PageBean pb);
}
