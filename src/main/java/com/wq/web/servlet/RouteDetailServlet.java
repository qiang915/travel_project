package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.Route;
import com.wq.service.RouteDetailService;

/**
 * @author WQ
 * @version 创建时间：2019年8月26日 上午10:19:31
 * @ClassName 类名称
 * @Description 类描述
 */

@WebServlet("/routeDetailServlet")
public class RouteDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取前端发送的数据rid
		String ridStr = request.getParameter("rid");
		//判断是否为空，将字符串转为整型
		int rid = 0;
		if(ridStr != null && !"".equals(ridStr) && !"null".equals(ridStr)) {
			rid = Integer.parseInt(ridStr);
		}else {
			//rid默认值为1
			rid = 1;
		}
		//调用service层方法完成数据的获取
		RouteDetailService routeDetailService = new RouteDetailService();
		//获取数据
		Route route = routeDetailService.findByRid(rid);
		//将serice层获取的数据封装为json对象
		ObjectMapper mapper = new ObjectMapper();
		//定义返回浏览器的数据类型
		response.setContentType("application/json;charset=utf-8");
		//写回浏览器
		mapper.writeValue(response.getOutputStream(), route);
	}

}
