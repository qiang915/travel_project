package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.PageInfo;
import com.wq.domain.Route;
import com.wq.service.PageInfoService;

/**
 * @author WQ
 * @version 创建时间：2019年8月25日 上午10:06:48
 * @ClassName 类名称
 * @Description 类描述
 */

@WebServlet("/pageInfoServlet")
public class PageInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取前端发送的数据
		String cidStr = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		String rname = request.getParameter("rname");

		int cid = 0;
		// 接收的字符串不为空
		if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
			// 转为int型
			cid = Integer.parseInt(cidStr);
		} else {
			// cid默认为5
			cid = 5;
		}
		int currentPage = 0;
		if (currentPageStr != null && currentPageStr.length() > 0 && !"null".equals(currentPageStr)) {
			currentPage = Integer.parseInt(currentPageStr);
		} else {
			// 默认currentPage为1
			currentPage = 1;
		}
		int pageSize = 0;
		if (pageSizeStr != null && pageSizeStr.length() > 0) {
			pageSize = Integer.parseInt(pageSizeStr);
		} else {
			// 默认为每页显示5条记录
			pageSize = 5;
		}
		// 调用service层方法完成数据的封装
		PageInfoService info = new PageInfoService();
		// 调用service层获取数据
		PageInfo<Route> pageInfo = info.findPageInfo(cid, currentPage, pageSize, rname);
		// 封装成json对象
		ObjectMapper mapper = new ObjectMapper();
		// 定义返回的数据类型为json
		response.setContentType("application/json;charset=utf-8");
		// 写会浏览器
		mapper.writeValue(response.getOutputStream(), pageInfo);
	}

}
