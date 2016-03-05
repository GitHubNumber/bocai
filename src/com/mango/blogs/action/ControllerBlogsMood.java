package com.mango.blogs.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.bean.BlogsMood;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.blogs.service.IServiceBlogsMood;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客mood管理
 * 
 * @author lyy
 */
@Controller
@Scope("prototype")
public class ControllerBlogsMood extends ActionSupport {
	
	
	private final int moodType = 2;
	//每页显示的mood条数
	public static final int pageSize = 10;
	private static final long serialVersionUID = 1L;
	/**
	 * mood管理action
	 */
	@Resource(name = "serviceblogscomment")
	private IServiceBlogsComment serviceBlogsComment = null;
	@Resource(name = "serviceblogsmood")
	private IServiceBlogsMood serviceblogsmood = null;
	
	private User user;
	
	private BlogsMood blogsMood = null;
	private List<BlogsMood> list = null;
	//提示类...
	private PromptResult pr = null;
	// 分页的num
	private int pageNum;
	// 最大页数
	private int maxPageNum;
	// 总记录数
	private int allNum;
	
	
	/**
	 * 分页查询mood
	 * 一次查询10条
	 * @return
	 */
	public String getFenYe() {
		QueryResult<BlogsMood> qr = this.getLogList();
		this.setAllNum((int) qr.getTotalrecord());
		this.list = qr.getResultlist();
		return "list";
	}
	
	/**
	 * 获取分页的list,只在本类内使用
	 * 
	 * @return
	 */
	private QueryResult<BlogsMood> getLogList() {
		//第二个参数为user_id
		QueryResult<BlogsMood> qr = serviceblogsmood.selectMood(this.LogPageNum(),user.getId());
		this.setList(qr.getResultlist());
		//查询所有留言的评论-->第二个参数是user_id
				for(BlogsMood bm:this.getList()){
					//查询出来的指定留言的所对应的评论
					//第二个参数为user_id
					 List<BlogsComment>  bmlist = serviceBlogsComment.selectAllFromTable(moodType, user.getId() ,bm.getId()).getResultlist(); 
					bm.setBlogsComment(bmlist);
				}
		
		this.list = qr.getResultlist();
		return qr;
	}

	
	/**
	 * 获取分页查询的pageNum
	 * 如果当前页的num大约maxNum则当前页num=maxnum
	 */
	private int LogPageNum() {
		this.setPageNum(this.getPageNum() > this.getMaxPageNum() ? this
				.getMaxPageNum() : this.getPageNum());
		return (this.getPageNum() - 1) * pageSize;
	}

	/**
	 * 添加新的心情
	 * @param log
	 * @return
	 */
	public String addMood() {
		//添加所需要的user_id写在了在jspform表单里
		//设置user_id
		
		
		
		
		//这里填写的是当前登录用户的id
		this.getBlogsMood().setUser_id(1);
		if (serviceblogsmood.addMood(blogsMood)) {
			this.getLogList();
			return "list";
		} else {
			return "error";
		}

	}

	/**
	 * 按logId删除指定的日志
	 * 
	 * @return
	 */
	public String delectMoodById() {
		//user_id写在了在jsp form表单里
		if (serviceblogsmood.delectMood(blogsMood) == 1) {
			// 添加完log后返回日志列表,使用个头getFenYe方法
			this.getFenYe();
			return "list";
		} else {
			return "error";
		}
	}

	/**
	 * 跳转到addMood.jsp
	 * 
	 * @return
	 */
	public String togoAdd() {
		return "addLog";
	}
	
	public IServiceBlogsMood getServiceblogsmood() {
		return serviceblogsmood;
	}
	public void setServiceblogsmood(IServiceBlogsMood serviceblogsmood) {
		this.serviceblogsmood = serviceblogsmood;
	}
	public BlogsMood getBlogsMood() {
		return blogsMood;
	}
	public void setBlogsMood(BlogsMood blogsMood) {
		this.blogsMood = blogsMood;
	}
	public List<BlogsMood> getList() {
		return list;
	}
	public void setList(List<BlogsMood> list) {
		this.list = list;
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
		//第二个参数是user_id
		QueryResult<BlogsMood> qr = serviceblogsmood.selectMood(0,user.getId());
		int i=(int)qr.getTotalrecord();
		return (i%pageSize==0?i/pageSize:i/pageSize+1);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
