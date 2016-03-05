package com.mango.blogs.action;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.bean.BlogsMood;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.blogs.service.IServiceBlogsLog;
import com.mango.blogs.service.IServiceBlogsMood;
import com.mango.blogs.service.IserviceMessages;
import com.mango.blogs.service.impl.ServiceBlogsLog;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客日志管理
 * 
 * @author lyy
 */
@Controller
@Scope("prototype")
public class ControllerBlogsLog extends ActionSupport {
	public static final int pageSize = 10;
	private final int logType = 1;
	private static final long serialVersionUID = 1L;
	/**
	 * 日志管理action
	 */
	@Resource(name="serviceuser") private IServiceUser serviceuser;
	@Resource(name = "serviceblogslog")
	private IServiceBlogsLog serviceblogslog = null;
	@Resource(name = "serviceblogsmood")
	private IServiceBlogsMood serviceblogsmood = null;
	@Resource(name = "serviceblogscomment")
	private IServiceBlogsComment serviceblogscomment = null;
	@Resource(name = "serviceblogsmessages")
	private IserviceMessages serviceMessages = null;
	@PersistenceContext protected EntityManager em;
	
	private User user = null;
	
	private BlogsLog blogsLog = null;
	private List<BlogsLog> list = null;
	private List<BlogsMood> mlist = null;
	private List<BlogsComment> clist = null;
	private List<BlogsMessage> meslist = null;
	//提示类...
	private PromptResult pr = null;
	// 分页的num
	private int pageNum;
	// 最大页数
	private int maxPageNum;
	// 总记录数
	private int allNum;

	/**
	 * 分页查询日志
	 * 
	 * @return
	 */
	public String getFenYe() {
		QueryResult<BlogsLog> qr = getLogList();
		this.setAllNum((int) qr.getTotalrecord());
		return "list";
	}

	/**
	 * 获取分页的list,只在本类内使用
	 * 
	 * @return
	 */
	private QueryResult<BlogsLog> getLogList() {
		//第二个参数是userid
		user = serviceuser.selUserInfo(user.getId());
		QueryResult<BlogsLog> qr = serviceblogslog.sele(this.LogPageNum(),user.getId());
		this.list = qr.getResultlist();
		return qr;
	}

	/**
	 * 获取分页查询的pageNum
	 */
	private int LogPageNum() {
		this.setPageNum(this.getPageNum() > this.getMaxPageNum() ? this
				.getMaxPageNum() : this.getPageNum());
		return (this.getPageNum() - 1) * this.pageSize;
	}
	
	/**
	 * 添加日志
	 * @param log
	 * @return
	 */
	public String addLog() {
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			return "error";
		}
		this.getBlogsLog().setUser_id(landinguser.getId());
		this.getBlogsLog().setDate(new Date(System.currentTimeMillis()));
		this.getBlogsLog().setPraise(0);
		if("".equals(this.getBlogsLog().getLog_title())){
			this.getBlogsLog().setLog_title("未命名日志");
		}
		if (serviceblogslog.addLog(blogsLog)) {
			User u = new User();
			u.setId(landinguser.getId());
			this.setUser(u);
			this.getLogList();
			return "list";
		} else {
			return "error";
		}
	}
	/**
	 * 
	 * @return
	 */
	public String myLog() {
		QueryResult<BlogsLog> qr = serviceblogslog.selectById(this.getBlogsLog().getId());
		this.setBlogsLog(qr.getResultlist().get(0));
		//第二个参数是user_id
		QueryResult<BlogsComment> qr2= serviceblogscomment.selectAll(logType, user.getId(), this.getBlogsLog().getId());
		this.setClist(qr2.getResultlist());
		return "mylog";
	}
	/**
	 * 按logId删除指定的日志
	 * 
	 * @return
	 */
	public String delectLogById() {
		//删除指定id的log类
		if (serviceblogslog.delectLog(blogsLog) == 1) {
			// 添加完log后返回日志列表,使用个头getFenYe方法
			this.getFenYe();
			return "list";
		} else {
			return "error";
		}
	}
	/**
	 * 主页action方法写在这里
	 */
	@Override
	public String execute() throws Exception {
		user = serviceuser.selUserInfo(user.getId());
		//参数为user_id
		QueryResult<BlogsLog> qr = serviceblogslog.selectLast(user.getId());
		this.setList(qr.getResultlist());
		//参数为user_id
		QueryResult<BlogsMood> qr2 = serviceblogsmood.selectMoodLast(user.getId());
		this.setMlist(qr2.getResultlist());
		//参数为user_id
		QueryResult<BlogsMessage> qr3 = serviceMessages.selectMessagesLast(user.getId());
		this.setMeslist(qr3.getResultlist());
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 跳转到addLog.jsp
	 * 
	 * @return
	 */
	public String togoAdd() {
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			return "error";
		}
		
		return "addLog";
	}

	public List<BlogsLog> getList() {
		return list;
	}

	public void setList(List<BlogsLog> list) {
		this.list = list;
	}

	public IServiceBlogsLog getServiceblogslog() {
		return serviceblogslog;
	}

	public void setServiceblogslog(IServiceBlogsLog serviceblogslog) {
		this.serviceblogslog = serviceblogslog;
	}

	public BlogsLog getBlogsLog() {
		return blogsLog;
	}

	public void setBlogsLog(BlogsLog blogsLog) {
		this.blogsLog = blogsLog;
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
		return (this.getAllNum()%pageSize==0?this.getAllNum()/pageSize:this.getAllNum()/pageSize+1);
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


	public List<BlogsMood> getMlist() {
		return mlist;
	}

	public void setMlist(List<BlogsMood> mlist) {
		this.mlist = mlist;
	}

	public List<BlogsComment> getClist() {
		return clist;
	}

	public void setClist(List<BlogsComment> clist) {
		this.clist = clist;
	}

	public List<BlogsMessage> getMeslist() {
		return meslist;
	}

	public void setMeslist(List<BlogsMessage> meslist) {
		this.meslist = meslist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
