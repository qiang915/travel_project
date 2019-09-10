package com.wq.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wq.domain.User;
import com.wq.util.JDBCUtils;

/**
 * @author WQ
 * @version 创建时间：2019年8月23日 下午9:35:35
 * @ClassName 类名称
 * @Description 类描述
 */

public class UserDao {

	// 初始化jdbcTemplate对象（参数是获取的数据源信息）
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

	// 新增用户
	public Boolean inserUser(User user) {
		// sql语句
		String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
		// 插入用户
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(),
				user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());
		return true;
	}

	// 根据用户名查询用户
	public User findUserByUsername(String username) {
		// 定义sql语句
		String sql = "select * from tab_user where username = ?";
		// 调用查询
		List<User> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
		if(user.size() == 0) {
			return null;
		}else {
			return user.get(0);
		}
	}

	//根据code更新用户的status
	public void updateUserByCode(String code) {
		//定义sql语句
		String sql = "update tab_user set status = 'Y' where code = ?";
		//执行更新
		jdbcTemplate.update(sql, code);
	}

}
