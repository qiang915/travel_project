package com.wq.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wq.domain.Category;
import com.wq.util.JDBCUtils;

/**
* @author WQ
* @version 创建时间：2019年8月24日 下午8:36:18
* @ClassName 类名称
* @Description 类描述
*/

/**
 * 	分类的数据库交互的持久层
 * */
public class CategoryDao {

	//jdbcTemplate对象
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	//获取所有分类信息
	public List<Category> findAll() {
		String sql = "select * from tab_category";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}

}
