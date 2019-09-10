package com.wq.service;

import com.wq.dao.UserDao;
import com.wq.domain.User;
import com.wq.util.MailUtils;
import com.wq.util.UuidUtil;

/**
* @author WQ
* @version 创建时间：2019年8月23日 下午9:34:48
* @ClassName 类名称
* @Description 类描述
*/

public class UserService {
	
	//调用dao层将数据插入数据库
	UserDao userDao = new UserDao();
	
	//新增用户
	public Boolean insertUser(User user) {
		//新增要经过用户名是否存在判断
		//先使用用户名查询用户是否存在
		User exitUser = userDao.findUserByUsername(user.getUsername());
		//若exitUser存在，则返回false
		if(exitUser != null) {
			return false;
		//若不存在
		}else {
			//为用户设置一个全球唯一的激活码
			user.setCode(UuidUtil.getUuid());
			//设置激活状态
			user.setStatus("N");
			//调用dao层的插入方法
			userDao.inserUser(user);
			//向用户填写的邮箱发送邮件
			MailUtils.sendMail(user.getEmail(), 
					"离注册成功只差一步喽，<a href='http://localhost:8080/travelProduct/activeUserServlet?code="+user.getCode()+"'>点击激活旅游网</a>", "激活邮件");
			return true;
		}
	}

	//根据code修改用户的status
	public void updateUserByCode(String code) {
		userDao.updateUserByCode(code);
	}

	//校验登录时输入的用户名是否存在对应的用户
	public User findUserByUsername(String username) {
		User user = userDao.findUserByUsername(username);
		return user;
	}


}
