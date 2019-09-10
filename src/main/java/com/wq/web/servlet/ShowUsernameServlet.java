package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.ResultInfo;

/**
 * @author WQ
 * @version 创建时间：2019年8月24日 下午5:32:30
 * @ClassName 类名称
 * @Description 类描述
 */

@WebServlet("/showUsernameServlet")
public class ShowUsernameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取的session中的用户名
		String USER_NAME = (String) request.getSession().getAttribute("USER_NAME");
		// 获取封装对象
		ResultInfo info = new ResultInfo();
		if (USER_NAME != null) {
			info.setFlag(true);
			info.setMsg(USER_NAME);
		}else {
			info.setFlag(false);
			info.setMsg("未登录");
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(info);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return;
	}

}
