package com.mango.blogs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.bean.BlogsPhotoGroup;
import com.mango.blogs.bean.BlogsPhotoUpload;
import com.mango.blogs.service.IServiceBlogsPhotoGroup;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客相册管理
 * 
 * @author lyy
 */
@Controller
@Scope("prototype")
public class ControllerBlogsPhotoGroup extends ActionSupport {
	//一次查询几个相册组
	public static final int pageSize = 8;
	private static final long serialVersionUID = 1L;
	/**
	 * 相册管理action
	 */

	@Resource(name = "serviceblogsphotogroup")
	private IServiceBlogsPhotoGroup serviceblogsphotogroup = null;
	
	private User user;
	
	private BlogsPhotoGroup blogsphotogroup = null;
	private List<BlogsPhotoGroup> list = null;
	//提示类...
	private PromptResult pr = null;
	// 分页的num
	private int pageNum;
	// 最大页数
	private int maxPageNum;
	// 总记录数
	private int allNum;
	//上传的文件
	BlogsPhotoUpload blogsPhotoUpload = null;
	/**
	 * 分页查询相册
	 * 
	 * @return
	 */
	public String getFenYe() {
		//最后一个参数是user_id
		QueryResult<BlogsPhotoGroup> qr = serviceblogsphotogroup.selectPhotoGroup(this.photoPageNum(),user.getId());
		this.setList(qr.getResultlist());
		
		return "list";
	}

	/**
	 * 获取分页的list,只在本类内使用
	 * 
	 * @return
	 */
	private QueryResult<BlogsLog> getLogList() {
		return null;
	}

	/**
	 * 获取分页查询的pageNum
	 */
	private int photoPageNum() {
		//第二个值是userId
		QueryResult<BlogsPhotoGroup> qr = serviceblogsphotogroup.selectPhotoGroup(1,user.getId());
		this.setMaxPageNum(((int)qr.getTotalrecord())/this.pageSize==0?((int)qr.getTotalrecord())/this.pageSize:((int)qr.getTotalrecord())/this.pageSize+1);
		if(this.getPageNum()>=this.getMaxPageNum()){
			this.setPageNum(this.getMaxPageNum());
		}
		return (this.getPageNum() - 1) * this.pageSize;
	}

	/**
	 * 添加分組
	 * 
	 * @param log
	 * @return
	 * @throws IOException 
	 */
	public String addGroup(){
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			return "error";
		}
		
		FileOutputStream fos = null;
		FileInputStream fis = null;
		//实例化输出流,输出到哪里去.
			try {
				blogsPhotoUpload.setUploadFileName(landinguser.getId().toString()+"_"+blogsPhotoUpload.getUploadFileName());
				fos = new FileOutputStream(blogsPhotoUpload.getSavePath()+"\\"+blogsPhotoUpload.getUploadFileName());
				fis=new FileInputStream(blogsPhotoUpload.getUpload());
			byte[] buffer=new byte[1024];
			int len = 0;
				while((len=fis.read(buffer))>0){
					fos.write(buffer, 0, len);
}
			fos.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BlogsPhotoGroup bpg = new BlogsPhotoGroup();
		bpg.setPhotoGroup(blogsPhotoUpload.getInfo());
		bpg.setUser_id(landinguser.getId());
		bpg.setCover(blogsPhotoUpload.getUploadFileName());
		//写入到数据库
		serviceblogsphotogroup.addGroup(bpg);
		User u = new User();
		u.setId(landinguser.getId());
		this.setUser(u);
		this.getFenYe();
			return "list";
//		}catch (IOException e) {
//			return "error";
//		}
	}

	/**
	 * 按logId删除指定的photogroup
	 * 
	 * @return
	 */
	public String delectPhotoGroupById() {
			return "list";
	}

	/**
	 * 跳转到addLog.jsp
	 * 
	 * @return
	 */
	public String togoAdd() {
		
		return "togoAdd";
	}



	public PromptResult getPr() {
		return pr;
	}

	public void setPr(PromptResult pr) {
		this.pr = pr;
	}

	public int getPageNum() {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getMaxPageNum() {

		return maxPageNum;
	}

	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}


	public BlogsPhotoGroup getBlogsphotogroup() {
		return blogsphotogroup;
	}

	public void setBlogsphotogroup(BlogsPhotoGroup blogsphotogroup) {
		this.blogsphotogroup = blogsphotogroup;
	}

	public List<BlogsPhotoGroup> getList() {
		return list;
	}

	public void setList(List<BlogsPhotoGroup> list) {
		this.list = list;
	}

	public BlogsPhotoUpload getBlogsPhotoUpload() {
		return blogsPhotoUpload;
	}

	public void setBlogsPhotoUpload(BlogsPhotoUpload blogsPhotoUpload) {
		this.blogsPhotoUpload = blogsPhotoUpload;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
