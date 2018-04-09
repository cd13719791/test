package com.moyou.moyouRms.dao.report;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.report.Report;
import com.moyou.moyouRms.model.statistics.UserStatistics;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);
    /**
	 * 举报接口初始化
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
	 * 查询举报未处理总数
	 * @return
	 */
	Short queryCountNoDispose();
	/**
	 * 查询举报已经处理总数
	 * @return
	 */
	Short queryCountDispose();
	/**
	 * 已处理和未处理
	 * @return
	 */
	UserStatistics queryUserStatisticsReport();
}