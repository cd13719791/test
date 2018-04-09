package com.moyou.moyouRms.controller.h5;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.report.Report;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.ReportService;

@RestController
@RequestMapping(value = "/h5")
public class ReportController extends BaseController {
	@Resource
	private ReportService reportService;

	/**
	 * 添加一条举报内容
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/report/insertReport", method = RequestMethod.POST)
	public ApiResult insertTalk(@RequestBody Report record) {
		record.setState(0);
		record.setCreateTime(new Date( ));
		reportService.insert(record);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}
}
