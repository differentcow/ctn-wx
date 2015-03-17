package com.ctn.entity.query;

import java.util.List;

public interface ListQueryParam extends QueryParam {
	/**
	 * 获取排序条件集合
	 * 
	 * @return
	 */
	List<SortCond> getSortCond();

	/**
	 * 添加排序条件
	 * 
	 * @param sortCond
	 */
	void addSortCond(SortCond sortCond);

	void addSortCond(List<SortCond> sortCondList);

	/**
	 * 获取当前页数
	 * 
	 * @return
	 */
	Integer getPage();

	/**
	 * 获取每页查询记录数
	 * 
	 * @return
	 */
	Integer getPageSize();

	/**
	 * 设置当前页数
	 */
	void setPage(Integer page);

	/**
	 * 设置每页查询记录数
	 */
	void setPageSize(Integer pageSize);
}
