package com.moyou.moyouRms.service.report.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.moyou.moyouRms.dao.report.CommonResourceMapper;
import com.moyou.moyouRms.dao.report.ReportMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.report.Report;
import com.moyou.moyouRms.service.report.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Resource
	private ReportMapper reportMapper;
	@Resource
	private CommonResourceMapper commonResourceMapper;

	/**
	 * 添加一条举报内容
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int insert(Report record) {
		reportMapper.insert(record);
		List<CommonResource> reportResourceList = record
				.getReportResourceList();
		if (!CollectionUtils.isEmpty(reportResourceList)) {
			for (CommonResource commonResource : reportResourceList) {
				commonResource.setObjectId(record.getId());
				commonResource.setUrl(commonResource.getUrl());
				commonResource.setUserId(record.getUserId());
				commonResource.setPicOrder(commonResource.getPicOrder());
			}
			commonResourceMapper.insertReporResouce(reportResourceList);
		}
		return 1;
	}

	@Override
	public List<Report> selectReportsBySelctive(PageBean pbn) {
		return reportMapper.selectReportsBySelctive(pbn);
	}

	@Override
	public Report selectReportsById(Report pbn) {
		// TODO Auto-generated method stub
		return reportMapper.selectReportsById(pbn);
	}

	@Override
	public int updateReportsWithState(Report pbn) {
		// TODO Auto-generated method stub
		return reportMapper.updateReportsWithState(pbn);
	}

	/**
	 * 举报处理和未处理总数
	 * 
	 * @return
	 */
	@Override
	public CommonResource queryCountDispose() {
		CommonResource report = new CommonResource();
		report.setState(reportMapper.queryCountDispose());
		report.setNoDispose(reportMapper.queryCountNoDispose());
		return report;
	}
}
