package com.mango.blogs.service;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;

public interface IServiceBlogsComment {

	/**
	 * 添加评论
	 */
	public abstract boolean addComment(BlogsComment blogsComment,int type,int userId);

	/**
	 * 查询评论
	 * @return
	 */
	public abstract QueryResult<BlogsComment> selectAll(int type,int userId,int tableId);

	/**
	 * 删除评论
	 * @return
	 */
	public abstract boolean deleteCommentById(int id);
	/**
	 * 查询关于某表的所有评论
	 * @param type
	 * @param userId
	 * @return
	 */
	public QueryResult<BlogsComment> selectAllFromTable(int type,int userId,int tableid);
}