package com.wq.service;

import com.wq.dao.PageInfoDao;
import com.wq.domain.PageInfo;
import com.wq.domain.Route;

/**
 * @author WQ
 * @version 创建时间：2019年8月25日 上午10:16:14
 * @ClassName 类名称
 * @Description 类描述
 */

//用于页面分页显示的service层
public class PageInfoService {

	// 获取dao层对象
	PageInfoDao dao = new PageInfoDao();

	// 封装pageInfo对象的方法
	public PageInfo<Route> findPageInfo(int cid, int currentPage, int pageSize, String rname) {
		// 获取对象
		PageInfo<Route> info = new PageInfo<Route>();
		// 设置当前页面
		info.setCurrentPage(currentPage);
		// 设置总记录数
		int totalNums = dao.findTotalNums(cid,rname);
		info.setTotalNums(totalNums);
		// 设置每页显示记录数
		info.setPageSize(pageSize);
		// 设置总页数
		int totalPages = totalNums % pageSize == 0 ? totalNums / pageSize : totalNums / pageSize + 1;
		info.setTotalPages(totalPages);

		// 设置开始索引
		int start = (currentPage - 1) * pageSize;
		// 设置list集合
		info.setList(dao.findByPage(cid, start, pageSize,rname));

		return info;
	}

}
