package com.moyou.moyouRms.service.report;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.report.Report;

public interface ReportService {
	/**
	 * 添加一条举报内容
	 * 
	 * @param record
	 * @return
	 */
	int insert(Report record);

	/**
	 * 举报接口初始化
	 * 
	 * @param pbn
	 * @return
	 */
	List<Report> selectReportsBySelctive(PageBean pbn);

	/**
	 * 举报接口 根据Id 查询一条举报内容
	 * 
	 * @param pbn
	 * @return
	 */
	Report selectReportsById(Report pbn);

	/**
	 * 举报接口 修改举报状态
	 * 
	 * @param pbn
	 * @return
	 */
	int updateReportsWithState(Report pbn);
	/**
	 * 举报处理和未处理总数
	 * @return
	 */
	CommonResource queryCountDispose();
}
