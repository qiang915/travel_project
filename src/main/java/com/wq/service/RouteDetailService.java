package com.wq.service;

import java.util.List;

import com.wq.dao.RouteDetailDao;
import com.wq.domain.Route;
import com.wq.domain.RouteImg;
import com.wq.domain.Seller;

/**
* @author WQ
* @version 创建时间：2019年8月26日 上午10:23:43
* @ClassName 类名称
* @Description 类描述
*/

public class RouteDetailService {
	
	//dao层对象
	RouteDetailDao dao = new RouteDetailDao();
	
	//根据rid获取路线详细界面所需的图片、卖家、基本信息
	public Route findByRid(int rid) {
		
		//3、根据rid调用dao层获取基本信息
		Route route = dao.findRouteByRid(rid);
		//1、根据rid调用dao层获取图片
		List<RouteImg> imgs = dao.findImgByRid(rid);
		//将数据封装到route中
		route.setRouteImgList(imgs);
		//2、根据rid调用dao层获取卖家
		Seller seller = dao.findSellerByRid(rid);
		//将数据封装到route中
		route.setSeller(seller);
		//返回数据
		return route;
		
	}

}
