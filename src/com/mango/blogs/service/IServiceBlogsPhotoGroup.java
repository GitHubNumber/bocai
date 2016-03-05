package com.mango.blogs.service;

import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.bean.BlogsPhotoGroup;

public interface IServiceBlogsPhotoGroup {

	/**
	 * 获取photoGroup 的分组
	 * 使用的话从这个qr里获取list和总分组数在jsp里遍历photoGroup兵把
	 * @return
	 */
	public abstract QueryResult<BlogsPhotoGroup> selectPhotoGroup(int index,int userId);

	/**
	 * 根据photoGroup里的Group_Id 内容查询指定的photo内容信息
	 */
	public abstract QueryResult<BlogsPhoto> selectPhoto(
			QueryResult<BlogsPhotoGroup> qrpg, int index);

	/**
	 * 添加照片到数据库,其实是添加照片上传到的文件夹路径和文件名,文件名最好重命名,以user表的id为开头
	 * @param blogsPhoto
	 * @return
	 */
	public abstract boolean addGroup(BlogsPhotoGroup blogsPhotoGroup);

	/**
	 * 删除文件-->照片,要执行两步,一是在数据库中删除照片的路径,二是删除存放照片文件夹里的照片
	 * @param blogsPhoto
	 * @return
	 */
	public abstract int delectPhoto(BlogsPhoto blogsPhoto);

}