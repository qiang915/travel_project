package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WQ
 * @version 创建时间：2019年8月24日 下午5:47:40
 * @ClassName 类名称
 * @Description 类描述
 */

@WebServlet("/quitServlet")
public class QuitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//销毁session
		request.getSession().invalidate();
	}

}
