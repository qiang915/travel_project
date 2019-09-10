package com.wq.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wq.domain.Route;
import com.wq.domain.RouteImg;
import com.wq.domain.Seller;
import com.wq.util.JDBCUtils;

/**
* @author WQ
* @version 创建时间：2019年8月26日 上午10:30:41
* @ClassName 类名称
* @Description 类描述
*/

public class RouteDetailDao {

	//获取jdbcTemplate对象
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	//根据rid查询对应的图片
	public List<RouteImg> findImgByRid(int rid) {
		//定义sql
		String sql = "select * from tab_route_img where rid = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
	}

	//根据rid查询对应的卖家信息
	public Seller findSellerByRid(int rid) {
		//sql语句
		String sql = "SELECT s.* FROM tab_seller s LEFT JOIN tab_route r ON s.`sid` = r.`sid` WHERE rid = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),rid);
	}

	//根据rid查询对应的route基本信息
	public Route findRouteByRid(int rid) {
		//sql语句
		String sql = "select * from tab_route where rid = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
	}

}
