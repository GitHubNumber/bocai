package com.mango.blogs.service;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsMood;

public interface IServiceBlogsMood {

	/**
	 * 获取mood的前十条
	 * @return
	 */
	public abstract QueryResult<BlogsMood> selectMood(int index,int userId);

	/**
	 * 添加心情
	 */
	public abstract boolean addMood(BlogsMood blogsMood);

	/**
	 * 删除心情
	 */
	public abstract int delectMood(BlogsMood blogsMood);

	QueryResult<BlogsMood> selectMoodLast(int userId);

}