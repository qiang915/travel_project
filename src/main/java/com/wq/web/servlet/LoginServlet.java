package com.wq.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.ResultInfo;
import com.wq.domain.User;
import com.wq.service.UserService;

/**
 * @author WQ
 * @version 创建时间：2019年8月24日 下午3:38:34
 * @ClassName 类名称
 * @Description 类描述
 */

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取service层对象
		UserService userService = new UserService();
		// 获取前端发送来的操作标识符
		String action = request.getParameter("action");
		// 获取输入的用户名
		String username = request.getParameter("username");
		// 调用service方法，根据用户名查询用户
		User user = userService.findUserByUsername(username);
		if ("checkUsername".equals(action)) {
			// 获取返回到浏览器的对象
			ResultInfo info = new ResultInfo();
			// 若存在此用户
			if (user != null) {
				info.setFlag(true);
				info.setMsg("用户名存在，可登录");
				// 将用户名放入session域中
				request.getSession().setAttribute("USER_NAME", user.getUsername());
				// 将登录用户id存入session域中
				request.getSession().setAttribute("UID", user.getUid());
			}
			// 若不存在
			if (user == null) {
				info.setFlag(false);
				info.setMsg("用户名不存在，请先注册！");
			}
			// 封装info对象为json对象
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(info);
			// 设置写会前端的数据类型
			response.setContentType("application/json;charset=utf-8");
			// 写回前段界面
			response.getWriter().write(json);
			return;
		}
		if ("checkPassword".equals(action) && user != null) {
			// 获取返回到浏览器的对象
			ResultInfo info = new ResultInfo();
			// 获取前端输入的密码
			String password = request.getParameter("password");
			// 判断输入的password是否正确
			if (password.equals(user.getPassword())) {
				info.setFlag(true);
				info.setMsg("密码正确");
			}
			// 密码错误
			if (!password.equals(user.getPassword())) {
				info.setFlag(false);
				info.setMsg("密码错误，再想想？");
			}
			// 将info对象封装成json对象
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(info);
			// 设置返回到浏览器的数据类型
			response.setContentType("application/json;charset=utf-8");
			// 将json写回浏览器
			response.getWriter().write(json);
			return;
		}
		if ("checkCode".equals(action)) {
			// 获取前端传来的输入的验证码
			String code = request.getParameter("code");
			// 拿到存储在session中的验证码数据
			HttpSession session = request.getSession();
			String CHECKCODE_SERVER = (String) session.getAttribute("CHECKCODE_SERVER");
			// 获取返回到浏览器的数据封装对象
			ResultInfo info = new ResultInfo();
			// 判断验证码输入是否在正确忽略大小写比较
			if (CHECKCODE_SERVER.equalsIgnoreCase(code)) {
				// 若正确
				info.setFlag(true);
			} else {
				info.setFlag(false);
			}
			// 将数据封装为json对象
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(info);
			// 定义返回数据类型
			response.setContentType("application/json;charset=utf-8");
			// 将json写回浏览器
			response.getWriter().write(json);
			return;
		}

	}

}
