package com.wq.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wq.domain.Favorite;
import com.wq.util.JDBCUtils;

/**
 * @author WQ
 * @version 创建时间：2019年8月26日 下午3:50:11
 * @ClassName 类名称
 * @Description 类描述
 */

//根据rid及uid查询Favorite对象
public class IsCollectionDao {

	// jdbcTemplate对象
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

	// 根据rid及uid获取Favorite对象
	public Favorite findByRidUid(int rid, Integer uid) {
		Favorite favorite = null;
		// 使用try-catch，若jdbcTemplate.queryForObject()无查询结果时程序不会报错
		try {
			// 定义sql
			String sql = "select * from tab_favorite where uid = ? and rid = ?";
			favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
		} catch (Exception e) {}
		return favorite;
	}

}
