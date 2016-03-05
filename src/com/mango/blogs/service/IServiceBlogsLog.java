package com.mango.blogs.service;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsLog;

public interface IServiceBlogsLog {

	/**
	 * 获取log的分页日志从
	 * @return
	 */
	public abstract QueryResult<BlogsLog> sele(int index,int userId);

	/**
	 * 查询最后一条log
	 * @return
	 */
	public abstract QueryResult<BlogsLog> selectLast(int userId);
	/**
	 * 查询指定log
	 * @return
	 */
	public abstract QueryResult<BlogsLog> selectById(int id);

	/**
	 * 添加日志
	 */
	public abstract boolean addLog(BlogsLog blogsLog);

	/**
	 * 删除日志
	 */
	public abstract int delectLog(BlogsLog log);

}