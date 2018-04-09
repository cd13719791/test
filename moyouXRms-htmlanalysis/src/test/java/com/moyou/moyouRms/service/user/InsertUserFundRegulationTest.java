package com.moyou.moyouRms.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.StringUtil;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.statistics.UserFundRegulation;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.statistics.UserFundRegulationService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @author created by Chenxv
 * @date 2017年9月18日 下午3:55:46
 */
public class InsertUserFundRegulationTest extends BaseJunit4 {

	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserFundRegulationService userFundRegulationService;
	@Resource
	private DiaryService diaryService;

	public void start() {
		List<UserFundLog> userFundLogList = userFundLogService.selectUserFundLogForRegulation();
		if (userFundLogList == null || userFundLogList.size() == 0) {
			return;
		}
		userFundLogList.parallelStream().forEach(s -> {
			UserFundRegulation userFundRegulation = new UserFundRegulation();
			userFundRegulation.setCreateTime(s.getCreateTime());
			userFundRegulation.setModeId(s.getId());
			userFundRegulation.setModeType((int) s.getModeType());
			userFundRegulation.setNumber(s.getUserFund());
			userFundRegulationService.insertNonEmptyUserFundRegulation(userFundRegulation);
		});
	}

	public void add(){		
		List<Diary> userReceiveDataAnalysisResult = diaryService.selectUserReceiveDataAnalysis();
		for (int i = 0; i < userReceiveDataAnalysisResult.size(); i++) {
			String mapstr=userReceiveDataAnalysisResult.get(i).getExtendData();
			Map<String, Object> map=JsonUtil.toMap(mapstr);
			if(map.get("video")!=null&&StringUtil.isNotEmpty(map.get("video").toString())){
				String url=JsonUtil.toMap(userReceiveDataAnalysisResult.get(i).getExtendData()).get("video").toString();
				if(url.indexOf("http:")<0){
					url="http:"+url;
				}
				diaryService.updateByPrimaryKeySelective(new Diary(userReceiveDataAnalysisResult.get(i).getId(),userReceiveDataAnalysisResult.get(i).getExtendData(),url));
			}
		}
		
			
		
	}
	
	@Test
	public void test() {
		try {
			add();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
