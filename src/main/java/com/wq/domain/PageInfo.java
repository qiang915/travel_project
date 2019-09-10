package com.wq.domain;

import java.util.List;

/**
* @author WQ
* @version 创建时间：2019年8月25日 上午9:56:23
* @ClassName 类名称
* @Description 类描述
*/

//用于分页的pageBean
public class PageInfo<T> {

	//当前页面
	private Integer currentPage;
	//每页显示记录数
	private Integer pageSize;
	//总页码
	private Integer totalPages;
	//总记录数
	private Integer totalNums;
	//当前页面显示的数据
	private List<T> list;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getTotalNums() {
		return totalNums;
	}
	public void setTotalNums(Integer totalNums) {
		this.totalNums = totalNums;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", totalNums=" + totalNums + ", list=" + list + "]";
	}
	
	
}
