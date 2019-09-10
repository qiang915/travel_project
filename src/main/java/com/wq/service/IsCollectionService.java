package com.wq.service;

import com.wq.dao.IsCollectionDao;
import com.wq.domain.Favorite;

/**
 * @author WQ
 * @version 创建时间：2019年8月26日 下午3:49:24
 * @ClassName 类名称
 * @Description 类描述
 */

//判断当前登录用户是否已经收藏该路线
public class IsCollectionService {

	// 获取dao层对象
	IsCollectionDao dao = new IsCollectionDao();
	
	// 根据rid、uid获取收藏对象，判断是否已经收藏
	public Boolean findByRidUid(String ridStr, Integer uid) {
		int rid ;
		//判断rid是否为空
		if(ridStr == null || "".equals(ridStr) || "null".equals(ridStr)) {
			//返回false;
			return false;
		}else {
			//否则将rid转为整型
			rid = Integer.parseInt(ridStr);
		}
		Boolean flag;
		//调用dao层查询
		Favorite favorite = dao.findByRidUid(rid,uid);
		//是否为空判断，为空则表示未收藏
		if(favorite == null) {
			flag = false;
		//不为空，则表示已经收藏
		}else {
			flag = true;
		}
		return flag;
	}


}
