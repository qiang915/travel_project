package com.wq.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wq.domain.Route;
import com.wq.util.JDBCUtils;

/**
 * @author WQ
 * @version 创建时间：2019年8月25日 上午10:17:09
 * @ClassName 类名称
 * @Description 类描述
 */

//用于分页显示信息查询的与数据库交互的持久层
public class PageInfoDao {

	// 获取jdbcTemplate对象
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

	// 获取数据总数
	public int findTotalNums(int cid, String rname) {
//		String sql = "select count(*) from tab_route where cid=? ";
		String sql = " select count(*) from tab_route where 1=1 ";
		// 用于字符串的拼接
		StringBuilder sb = new StringBuilder(sql);
		// 用于存储参数们
		List<String> params = new ArrayList<>();
		// 若cid的值不为0，则将cid拼接到sql语句中
		if (cid != 0) {
			sb.append(" and cid = ? ");
			// 将cid作为条件
			params.add(String.valueOf(cid));
		}
		// 若rname不为空，则将rname拼接到sql语句中
		if (rname != null && !"".equals(rname)) {
			sb.append(" and rname like ? ");
			// 将rname作为条件
			params.add("%" + rname + "%");
		}
		// 将拼接后字符串赋值给sql
		sql = sb.toString();
//		System.out.println(sql);
		return jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
	}

	// 获取每页要显示的数据
	public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//		String sql = "select * from tab_route where cid=? limit ?,?";
		// sql语句模板
		String sql = "select * from tab_route where 1=1 ";
		//可变字符串对象
		StringBuilder sb = new StringBuilder(sql);
		//用于存储条件们
		List<Object> params = new ArrayList<Object>();
		//判断cid是否为0
		if(cid != 0) {
			//给sql增加条件
			sb.append(" and cid = ? ");
			//将cid作为条件添加到条件数组中
			params.add(cid);
		}
		//判断rname是否为空
		if(rname!= null && !"".equals(rname)) {
			//给sql增加条件
			sb.append(" and rname like ? ");
			//将rname作为条件添加到数组中
			params.add("%"+rname+"%");
		}
		//将start和pageSize作为条件添加到sql语句中
		sb.append(" limit ?,? ");
		//将start、pageSize作为条件添加到数组中
		params.add(start);
		params.add(pageSize);
		//重新给sql语句赋值
		sql = sb.toString();
//		System.out.println(sql);
		//将params转为数组形式作为预编译的条件
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
	}
}
