package com.moyou.moyouRms.urlshareanalysis.service;

import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;

public interface AnalysisService {


	
	/**   
	 * 后台重新解析今日头条视频地址变化
	 * @param videoId        
	 * @param diarySourceTypeEnum
	 * @return
	 */

	String analysisToutiaoVideoUrl(String videoId, DiarySourceTypeEnum diarySourceTypeEnum);

}
