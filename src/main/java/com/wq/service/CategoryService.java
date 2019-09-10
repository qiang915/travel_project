package com.wq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wq.dao.CategoryDao;
import com.wq.domain.Category;
import com.wq.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
* @author WQ
* @version 创建时间：2019年8月24日 下午8:35:22
* @ClassName 类名称
* @Description 类描述
*/

/**
 * 分类的服务层
 */
public class CategoryService {

	// 获取dao层对象
	CategoryDao categoryDao = new CategoryDao();

	// 获取所有分类名称信息
	public List<Category> findAll() {
		/*
		 * 由于分类的数据经常要使用，并且一般不会发生改变， 所以可以使用redis来减少与数据库的交互，提高系统性能
		 */
		// 使用jedis客户端获取redis的连接
		Jedis jedis = JedisUtil.getJedis();
		// 从redis 中获取数据
		//Set<String> category = jedis.zrange("category", 0, -1);
		//由于需要cid，所以从redis获取数据时要带上scores
		Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
		// 获取集合对象，Category类型集合
		List<Category> categories = null;
		// 若category为空，即第一次访问时redis中没有数据，则从mysql数据库中查询出来，并将数据存入redis中
		if (category == null || category.size() == 0) {
			// 调用dao层获取数据
			categories = categoryDao.findAll();
			// 遍历查询出来的结果，将数据存入redis中
			for (int i = 0; i < categories.size(); i++) {
				jedis.zadd("category", categories.get(i).getCid(), categories.get(i).getCname());
			}
			// 若category不为空，则返回redis中查询的数据，但是redis中查询出来的是set类型，需转换为list类型
		} else {
			// 获取list的实例，用于存放转型的set数据
			categories = new ArrayList<Category>();
			// 遍历Set集合数据，存入list集合中，用于返回
			for (Tuple tuple : category) {
				// 实例化需要存入list的对象
				Category c = new Category();
				// 为对象的cname属性赋值，cname属性的值通过redis查询获得
				c.setCname(tuple.getElement());
				//为对象的cid属性赋值，cid属性的值通过redis查询获得，有double类型强制转换为int型
				c.setCid((int)tuple.getScore());
				categories.add(c);
			}
		}
		// 释放连接
		JedisUtil.close(jedis);
		return categories;
	}

}
