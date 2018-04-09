package com.moyou.moyouRms.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.util.ObjectUtils;

import com.moyou.moyouRms.util.JsonUtil;

/**
 * @ClassName: PageBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 *
 */
@Alias(value = "PageBean")
@SuppressWarnings("rawtypes")
public class PageBean extends Page {
	/** 当前页 */
	private int pageNumber = 1;

	public PageBean() {
	}

	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		setPageSize(pageSize);
	}

	@SuppressWarnings("unchecked")
	public PageBean(int liveEarningsLogRowCount, List<?> collect) {
		// TODO Auto-generated constructor stub
		super();
		this.setTotalPage(liveEarningsLogRowCount);
		this.setResults(collect);
	}

	private Map<String, Object> conditions = new HashMap<>();
	private List rows;

	private int total;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

	public void addConditions(String key, Object value) {
		if (ObjectUtils.isEmpty(conditions)) {
			this.conditions = new HashMap<String, Object>();
		}
		if (!ObjectUtils.isEmpty(value)) {

			this.conditions.put(key, value);
		}
	}

	@Override
	public String toString() {
		return "PageBean [pageNumber:" + pageNumber + ", pageSize:" + getPageSize() + ", results:"
				+ JsonUtil.toJson(getResults()) + ", total:" + getTotalRecord() + ", conditions:"
				+ conditions + "]";
	}

}
