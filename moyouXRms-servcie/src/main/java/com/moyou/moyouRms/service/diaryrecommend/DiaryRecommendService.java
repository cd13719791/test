package com.moyou.moyouRms.service.diaryrecommend;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diaryRecommend.DiaryRecommend;

public interface DiaryRecommendService {
	   int deleteByPrimaryKey(Integer id);

	    int insert(DiaryRecommend record);

	    int insertSelective(DiaryRecommend record);

	    DiaryRecommend selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(DiaryRecommend record);

	    int updateByPrimaryKey(DiaryRecommend record);
	    
	    List<Diary> queryDiaryRecommendList(PageBean record);
	    /**
	     * 修改推荐状态
	     * @param record
	     * @return
	     */
		int updateByDiaryId(DiaryRecommend record);
}
