package com.wq.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.ResultInfo;
import com.wq.domain.User;
import com.wq.service.UserService;

/**
 * @author WQ
 * @version 创建时间：2019年8月23日 下午9:04:08
 * @ClassName 类名称
 * @Description 类描述
 */

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置字符集编码
		request.setCharacterEncoding("utf-8");
		//获取前端数据
		Map<String, String[]> maps = request.getParameterMap();
		//封装对象
		User user = new User();
		try {
			//此方法会遍历maps中的所有键值，若键值与User中的属性相同则将该键对应的value赋给user中对应的属性
			BeanUtils.populate(user, maps);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//调用service层对象将user对象插入数据库中
		UserService userService = new UserService();
		Boolean flag = userService.insertUser(user);
		//获取返回前段的数据封装对象
		ResultInfo info = new ResultInfo();
		//若插入成功
		if(flag) {
			info.setFlag(true);
			info.setMsg("注册成功！");
		//若失败
		}else {
			info.setFlag(false);
			info.setMsg("注册失败！");
		}
		//将info对象封装为json
		ObjectMapper op = new ObjectMapper();
		String json = op.writeValueAsString(info);
		//设置返回到浏览器的数据类型
		response.setContentType("application/json");
		//将json数据返回到前段
		response.getWriter().write(json);
	}

}
