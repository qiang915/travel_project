package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.service.UserService;

/**
 * @author WQ
 * @version 创建时间：2019年8月24日 上午8:47:56
 * @ClassName 类名称
 * @Description 类描述
 */

/**
 * 	用户激活账号的servlet
 * */
public class ActiveUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取service层对象
		UserService userService = new UserService();
		//拿到当前的激活码
		String code = request.getParameter("code");
		//根据激活码查询修改用户的激活状态
		userService.updateUserByCode(code);
		//成功则跳转到登录界面
		response.sendRedirect("email_ok.html");
	}

}
