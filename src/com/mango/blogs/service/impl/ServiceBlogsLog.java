package com.mango.blogs.service.impl;


import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.blogs.action.ControllerBlogsLog;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.service.IServiceBlogsLog;
import com.mango.dao.base.DaoSupport;


@Transactional
@Service("serviceblogslog")
public class ServiceBlogsLog extends DaoSupport implements IServiceBlogsLog{
	
	/**
	 * 获取log的分页日志从
	 * @return
	 */
	public QueryResult<BlogsLog> sele(int index,int userId) {
		QueryResult<BlogsLog> qr = this.getScrollData(BlogsLog.class, index, ControllerBlogsLog.pageSize, " user_id = "+userId, null);
		//获取到的log个数
		return qr;
	}
	/**
	 * 查询最后一条log
	 * @return
	 */
	public QueryResult<BlogsLog> selectLast(int userId) {
		LinkedHashMap<String, String> lhm = new  LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		QueryResult<BlogsLog> qr = this.getScrollData(BlogsLog.class, 0, 2, " user_id = "+userId,lhm);
		//获取到的log个数
		return qr;
	}/**
	 * 查询指定log
	 * @return
	 */
	public QueryResult<BlogsLog> selectById(int id) {
		QueryResult<BlogsLog> qr = this.getScrollData(BlogsLog.class, 0, 1, "id = "+id,null);
		//获取到的log个数
		return qr;
	}
	/**
	 * 添加日志
	 */
	public boolean addLog(BlogsLog blogsLog){
		
		try {
			em.persist(blogsLog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 删除日志
	 */
	public int delectLog(BlogsLog log) {
		
		try {
			this.delete(BlogsLog.class, log.getId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
}
