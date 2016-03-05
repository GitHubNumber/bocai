package com.mango.blogs.service.impl;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.blogs.action.ControllerBlogsLog;
import com.mango.blogs.action.ControllerBlogsPhotoGroup;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.bean.BlogsPhotoGroup;
import com.mango.blogs.service.IServiceBlogsPhotoGroup;
import com.mango.dao.base.DaoSupport;

/**
 * 
 * 相册service
 * @author lyy
 *
 */
@Service("serviceblogsphotogroup")
@Transactional
public class ServiceBlogsPhotoGroup extends DaoSupport implements IServiceBlogsPhotoGroup{
	/**
	 * 获取photoGroup 的分组
	 * 使用的话从这个qr里获取list和总分组数在jsp里遍历photoGroup兵把
	 * @return
	 */
	public QueryResult<BlogsPhotoGroup> selectPhotoGroup(int index,int userId) {
		//从数据库中查询6个自定义相册分组
		//将user_id放入到查询条件中
		QueryResult<BlogsPhotoGroup> qr = this.getScrollData(BlogsPhotoGroup.class,index,ControllerBlogsPhotoGroup.pageSize,"user_id="+userId,null);
		return qr;
	}
	/**
	 * 根据photoGroup里的Group_Id 内容查询指定的photo内容信息
	 * 此方法未使用
	 */
	public QueryResult<BlogsPhoto> selectPhoto(QueryResult<BlogsPhotoGroup> qrpg,int index){
		LinkedHashMap<String, String> link =new LinkedHashMap<String, String>();
		link.put("date", "asc");
		QueryResult<BlogsPhoto> qr = this.getScrollData(BlogsPhoto.class, index, 12, "group_id="+qrpg.getResultlist().get(1).getPhotoGroup() ,link);
		return  qr;
	}
	/**
	 * 添加照片到数据库,其实是添加照片上传到的文件夹路径和文件名,文件名最好重命名,以user表的id为开头
	 * @param blogsPhoto
	 * @return
	 */
	public boolean addGroup(BlogsPhotoGroup blogsPhotoGroup){
		this.save(blogsPhotoGroup);
		return true;
	}
	/**
	 * 删除文件-->照片,要执行两步,一是在数据库中删除照片的路径,二是删除存放照片文件夹里的照片
	 * @param blogsPhoto
	 * @return
	 */
	public int delectPhoto(BlogsPhoto blogsPhoto) {
		return 0;
	}
	
	
}
