package com.mango.blogs.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.blogs.action.ControllerBlogsLog;
import com.mango.blogs.action.ControllerBlogsPhoto;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.service.IServiceBlogsLog;
import com.mango.blogs.service.IServiceBlogsPhoto;
import com.mango.dao.base.DaoSupport;


@Transactional
@Service("serviceblogsphoto")
public class ServiceBlogsPhoto extends DaoSupport implements IServiceBlogsPhoto{
	
	/**
	 * 获取指定photo分组里的photoList
	 * @return
	 */
	public QueryResult<BlogsPhoto> selectPhoto(int index,String condition) {
		QueryResult<BlogsPhoto> qr = this.getScrollData(BlogsPhoto.class, index,ControllerBlogsPhoto.pageSize, condition, null);
		//获取到的log个数
		return qr;
	}
	/**
	 * 获取指定photo分组里的photoList
	 * @return
	 */
	public QueryResult<BlogsPhoto> selectPhotoById(String condition) {
		QueryResult<BlogsPhoto> qr = this.getScrollData(BlogsPhoto.class, 0,2, condition, null);
		//获取到的log个数
		return qr;
	}
	
	/**
	 * 添加新photo
	 */
	public boolean addPhoto(BlogsPhoto blogsPhoto){
		try {
			em.persist(blogsPhoto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 删除指定id的 photo
	 */
	public int delectPhoto(BlogsPhoto blogsPhoto) {
		try {
			this.delete(BlogsPhoto.class, blogsPhoto.getId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
}
