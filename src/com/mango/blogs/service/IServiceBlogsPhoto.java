package com.mango.blogs.service;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsPhoto;

public interface IServiceBlogsPhoto {

	/**
	 *  获取指定photo分组里的photoList
	 * @param index 从第几条开始查询
	 * @param condition where 条件语句  直接写条件 ,不写where
	 * @return
	 */
	public abstract QueryResult<BlogsPhoto> selectPhoto(int index , String condition);

	/**
	 * 添加新photo
	 */
	public abstract boolean addPhoto(BlogsPhoto blogsPhoto);

	/**
	 * 删除指定photo
	 */
	public abstract int delectPhoto(BlogsPhoto blogsPhoto);
	/**
	 * 通过条件查询一条数据的上一条或者下一条
	 * @param condition
	 * @return
	 */
	public QueryResult<BlogsPhoto> selectPhotoById(String condition);
}