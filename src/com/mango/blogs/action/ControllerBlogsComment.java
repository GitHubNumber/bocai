package com.mango.blogs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.blogs.service.IServiceBlogsLog;
import com.mango.blogs.service.IServiceBlogsPhoto;
import com.mango.blogs.service.IserviceMessages;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客评论管理
 * 
 * @author lyy
 */
@Controller()
@Scope("prototype")
public class ControllerBlogsComment extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private final int logType = 1;
	private final int moodType = 2;
	private final int photoType = 3;
	private final int messageType = 4;
	/**
	 * 日志管理action
	 */
	@Resource(name="serviceblogsmessages")
	private IserviceMessages iservisMessages;
	@Resource(name = "serviceblogscomment")
	private IServiceBlogsComment serviceBlogsComment = null;
	@Resource(name = "serviceblogsphoto")
	private IServiceBlogsPhoto serviceblogsphoto = null;
	@Resource(name = "serviceblogslog")
	private IServiceBlogsLog serviceblogslog = null;
	
	private User user;
	
	private BlogsComment blogsComment = null;
	private BlogsPhoto blogsphoto = null;
	private BlogsLog blogsLog = null;
	private List<BlogsLog> list = null;
	private List<BlogsComment> clist = null;
	private List<BlogsMessage> mlist = null;
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
	public String getComment() {
		//第二个参数是user_id
		QueryResult<BlogsComment> qr = serviceBlogsComment.selectAll( logType,user.getId(),this.getBlogsComment().getTable_id());
		this.setClist(qr.getResultlist());
		return "list";
	}

	/**
	 * 添加到日志的评论
	 * @param log
	 * @return
	 */
	public String addLog() {
		//第三个参数是user_id
		//方法返回一个布尔类型,以示添加是否成功
		boolean b = serviceBlogsComment.addComment(this.getBlogsComment(),logType,user.getId());
		QueryResult<BlogsLog> qr = serviceblogslog.selectById(this.getBlogsLog().getId());
		this.setBlogsLog(qr.getResultlist().get(0));
		//第二个参数是userid
		QueryResult<BlogsComment> qr2= serviceBlogsComment.selectAll(logType, user.getId(), this.getBlogsLog().getId());
		this.setClist(qr2.getResultlist());
		
		
		
		
		
		return "log";
	}
	/**
	 * 添加到心情的评论
	 * @param log
	 * @return
	 */
	public String addMood() {
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			System.out.println("请先登录然后才能发表心情");
			return "error";
		}
		//最后一个参数是userid,重定向方式跳转到指定页面
		boolean b = serviceBlogsComment.addComment(this.getBlogsComment(),moodType,landinguser.getId());
			return "mood";
	}
	/**
	 * 添加到日志的评论
	 * @param log
	 * @return
	 */
	public String addPhoto() {
		//最后一个参数是userid,
		boolean b = serviceBlogsComment.addComment(this.getBlogsComment(),photoType,user.getId());
		//通过blogsphoto.id查询出指定photo
		QueryResult<BlogsPhoto> qr = serviceblogsphoto.selectPhotoById("id = "+ blogsphoto.getId()+" and user_id = "+ user.getId());
		this.setBlogsphoto(qr.getResultlist().get(0));
		//通过blogsphoto.id查询出该相片的所有comment
		QueryResult<BlogsComment> qr2 = serviceBlogsComment.selectAll(photoType, user.getId(), blogsphoto.getId());
		this.setClist(qr2.getResultlist());
			return "photo";
	}
	/**
	 * 添加到留言的评论
	 * @param log
	 * @return
	 */
	public String addMessage() {
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			System.out.println("请先登录然后才能留言");
			return "error";
		}
		//最后一个参数是user_id
		boolean b = serviceBlogsComment.addComment(this.getBlogsComment(),messageType,landinguser.getId());
			return "message";
	}
	/**
	 * 跳转到addLog.jsp
	 * 
	 * @return
	 */
	public String togoAdd() {
		return "addLog";
	}

	public PromptResult getPr() {
		return pr;
	}

	public void setPr(PromptResult pr) {
		this.pr = pr;
	}

	public int getPageNum() {
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

	public BlogsComment getBlogsComment() {
		return blogsComment;
	}

	public void setBlogsComment(BlogsComment blogsComment) {
		this.blogsComment = blogsComment;
	}
	
	
	public List<BlogsLog> getList() {
		return list;
	}

	public void setList(List<BlogsLog> list) {
		this.list = list;
	}

	public List<BlogsComment> getClist() {
		return clist;
	}

	public void setClist(List<BlogsComment> clist) {
		this.clist = clist;
	}

	public BlogsPhoto getBlogsphoto() {
		return blogsphoto;
	}

	public void setBlogsphoto(BlogsPhoto blogsphoto) {
		this.blogsphoto = blogsphoto;
	}

	public BlogsLog getBlogsLog() {
		return blogsLog;
	}

	public void setBlogsLog(BlogsLog blogsLog) {
		this.blogsLog = blogsLog;
	}

	public List<BlogsMessage> getMlist() {
		return mlist;
	}

	public void setMlist(List<BlogsMessage> mlist) {
		this.mlist = mlist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
