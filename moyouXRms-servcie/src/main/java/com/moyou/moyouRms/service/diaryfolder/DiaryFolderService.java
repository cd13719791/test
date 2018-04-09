package com.moyou.moyouRms.service.diaryfolder;


import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryFolder;

public interface DiaryFolderService {
	   int deleteByPrimaryKey(Integer id);

	    int insert(DiaryFolder record);

	    int insertSelective(DiaryFolder record);

	    DiaryFolder selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(DiaryFolder record);

	    int updateByPrimaryKey(DiaryFolder record);
	    
	    /**
	     * 根据状态查询所有的日记本
	     * @param state
	     * @return
	     */
	    List<DiaryFolder> selectDiaryFolderBySelective(PageBean pb);
}
