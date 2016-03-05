package com.mango.blogs.action;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;

import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.blogs.service.IserviceMessages;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客留言板
 * 
 * @author dinghao
 * 
 */
@Controller("controllerBlogsMessages")
@Scope("prototype")
public class ControllerBlogsMessages extends ActionSupport {

	/**
	 * 
	 */
	private final int messageType = 4;
	
	private static final long serialVersionUID = 1L;
	@Resource(name = "serviceblogscomment")
	private IServiceBlogsComment serviceBlogsComment = null;
	@Resource(name="serviceblogsmessages")
	private IserviceMessages iservisMessages;
	
	private User user;
	
	private BlogsMessage blogsMessage = null;
	private BlogsComment blogsComment = null;
	private List<BlogsMessage> mlist = null;
	private PromptResult pr = null;
	private int pageNum;// 分页的num
	private int maxPageNum;// 最大页数
	private int allNum;// 总记录数
	/**
	 * 
	 * 获取内容
	 * */
	public String getPaging(){
		QueryResult<BlogsMessage> qr=this.getMessagesList();
		this.setAllNum((int) qr.getTotalrecord());
		this.setMaxPageNum(this.getAllNum() / 10 + 1);
		return "PageSuccess";
	}
	
	/**
	 * 从第几页查到的内容
	 * @return
	 */
	public QueryResult<BlogsMessage> getMessagesList() {
		//分页查询留言,最后参数为user_id
		QueryResult<BlogsMessage> qr = iservisMessages.sele(this.MessagesPageNum(),user.getId());
		this.setMlist(qr.getResultlist());
		//查询所有留言的评论-->第二个参数是user_id
		for(BlogsMessage bm:this.getMlist()){
			//查询出来的指定留言的所对应的评论
			//第二个参数是user_id
			 List<BlogsComment>  bclist = serviceBlogsComment.selectAllFromTable(messageType, user.getId() ,bm.getId()).getResultlist(); 
			bm.setBlogsComment(bclist);
		}
		return qr;
	}
	
	/**
	 * 获取分页查询的pageNum数据
	 */
	private int MessagesPageNum() {
	
		if(this.getPageNum()>this.getMaxPageNum()){
			this.setPageNum(this.getMaxPageNum());
		}
	if(this.getPageNum()<=0){
		this.setPageNum(1);
	}
		return (this.getPageNum()-1)* 10;
	}
	
	/**
	 * 添加留言
	 */
	public String addMessages()
	{
		//添加留言，以前未注明id
		this.getBlogsMessage().setDate(new Date(System.currentTimeMillis()));
		this.getBlogsMessage().setUser_id(user.getId());
	
		
		if(iservisMessages.addMessages(this.getBlogsMessage())){
			return "addMessages";
		}else{
			return "error";
		}
	}
	/**
	 *  一共有多少页
	 * */
	public int getMaxPageNum() {
		//第二个参数是user_id
		//maxPageNum是在查询message之前需要用到的
		QueryResult<BlogsMessage> qr= iservisMessages.sele(0,user.getId());
		int i=(int)qr.getTotalrecord();
		return (i%10==0?i/10:i/10+1);
	}
	
	
	/**
	 * 按MessagesId删除指定的日志
	 * 
	 * @return
	 */


			
	
	public String delectMessageById() {
		if (iservisMessages.delectMessages(blogsMessage) == 1) {
			// 添加完log后返回日志列表,使用个头getFenYe方法
		
			return "list";
		} else {
			return "error";
		}
	}

	public IserviceMessages getIservisMessages() {
		return iservisMessages;
	}

	public void setIservisMessages(IserviceMessages iservisMessages) {
		this.iservisMessages = iservisMessages;
	}

	
	public List<BlogsMessage> getMlist() {
		return mlist;
	}
	public void setMlist(List<BlogsMessage> mlist) {
		this.mlist = mlist;
	}
	public PromptResult getPr() {
		return pr;
	}

	public void setPr(PromptResult pr) {
		this.pr = pr;
	}
	public BlogsMessage getBlogsMessage() {
		return blogsMessage;
	}
	public void setBlogsMessage(BlogsMessage blogsMessage) {
		this.blogsMessage = blogsMessage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
