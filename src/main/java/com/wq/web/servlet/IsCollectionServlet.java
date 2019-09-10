package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.ResultInfo;
import com.wq.service.IsCollectionService;

/**
* @author WQ
* @version 创建时间：2019年8月26日 下午3:21:05
* @ClassName 类名称
* @Description 类描述
*/

/**
 * 判断用户是否收藏此线路
 */
@WebServlet("/isCollectionServlet")
public class IsCollectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取前端发送来的rid
		String rid = request.getParameter("rid");
		//session中获取uid
		Integer uid = (Integer) request.getSession().getAttribute("UID");
		System.out.println(rid+" "+uid);
		//封装返回给浏览器的数据
		ResultInfo info = new ResultInfo();
		//若uid为空，说明未登录
		if(uid  == null) {
			info.setFlag(false);
			info.setMsg("未登录！");
		}
		//调用service层方法判断是否已经收藏
		IsCollectionService isCollectionService = new IsCollectionService();
		Boolean isCollection = isCollectionService.findByRidUid(rid, uid);
		//若isCollection为false，说明未收藏
		if(isCollection == false) {
			info.setFlag(false);
		}else {
			//收藏了
			info.setFlag(true);
		}
		//封装数据
		ObjectMapper mapper = new ObjectMapper();
		//以json格式返回数据
		response.setContentType("application/json;charset=utf-8");
		//写会浏览器
		mapper.writeValue(response.getOutputStream(), info);
	}

}
