package com.wq.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.domain.Category;
import com.wq.service.CategoryService;


/**
* @author WQ
* @version 创建时间：2019年8月24日 下午8:33:13
* @ClassName 类名称
* @Description 类描述
*/

/**
 * 从数据库中获取类别的展示信息
 */
@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取service层对象
		CategoryService service = new CategoryService();
		List<Category> lists = service.findAll();
		//封装成json对象，写到客户端
		ObjectMapper mapper = new ObjectMapper();
		//设置返回数据类型
		response.setContentType("application/json;charset=utf-8");
		//以字符输出流的形式写回浏览器端
		mapper.writeValue(response.getOutputStream(), lists);
	}

}
