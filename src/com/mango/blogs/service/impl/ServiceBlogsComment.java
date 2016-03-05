package com.mango.blogs.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.dao.base.DaoSupport;


@Service("serviceblogscomment")
@Transactional
public class ServiceBlogsComment extends DaoSupport implements IServiceBlogsComment {
	
	/**
	 * 添加留言
	 */
	public boolean addComment(BlogsComment blogsComment,int type,int userId){
		BlogsComment b=blogsComment;
		b.setType(type);
		b.setUser_id(userId);
		try {
			em.persist(b);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 查询评论
	 * @return
	 */
	public QueryResult<BlogsComment> selectAll(int type,int userId,int tableId) {
		QueryResult<BlogsComment> q = this.getScrollData(BlogsComment.class, 0,1, null, null);
		long max = q.getTotalrecord();
		QueryResult<BlogsComment> qr = this.getScrollData(BlogsComment.class, 0,(int)max, "type="+type+" and user_id = "+userId+" and table_id = "+tableId, null);
		return qr;
	}
	/**
	 * 查询关于某表的所有评论
	 * @return
	 */
	public QueryResult<BlogsComment> selectAllFromTable(int type,int userId,int tableid) {
		QueryResult<BlogsComment> q = this.getScrollData(BlogsComment.class, 0,1, null, null);
		long max=q.getTotalrecord();
		QueryResult<BlogsComment> qr = this.getScrollData(BlogsComment.class, 0,(int)max, "type="+type+" and user_id = "+userId+" and table_id = "+tableid, null);
		return qr;
	}
	/**
	 * 删除留言
	 * @return
	 */
	public boolean deleteCommentById(int id) {
		try {
			this.delete(BlogsComment.class, id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
