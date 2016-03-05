package com.mango.blogs.service.impl;



import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.blogs.action.ControllerBlogsMood;
import com.mango.blogs.bean.BlogsMood;
import com.mango.blogs.service.IServiceBlogsMood;
import com.mango.dao.base.DaoSupport;

/**
 * 
 * 心情(说说)service
 * @author lyy
 */
@Service("serviceblogsmood")
@Transactional
public class ServiceBlogsMood extends DaoSupport implements IServiceBlogsMood{
	
	/**
	 * 获取mood的前十条
	 * @return
	 */
	public QueryResult<BlogsMood> selectMood(int index,int userId) {
		QueryResult<BlogsMood> qr = this.getScrollData(BlogsMood.class, index, ControllerBlogsMood.pageSize, " user_id = "+userId, null);
		return qr;
	}	
	/**
	 * 获取最新的mood
	 * @return
	 */
	public QueryResult<BlogsMood> selectMoodLast(int userId) {
		LinkedHashMap<String, String> lhm = new  LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		QueryResult<BlogsMood> qr = this.getScrollData(BlogsMood.class, 0, 2, " user_id = "+userId, lhm);
		return qr;
	}
	/**
	 * 添加心情
	 */
	public boolean addMood(BlogsMood blogsMood){
		
		try {
			em.persist(blogsMood);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 删除心情
	 */
	public int delectMood(BlogsMood blogsMood) {
		
		try {
			this.delete(BlogsMood.class, blogsMood.getId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
