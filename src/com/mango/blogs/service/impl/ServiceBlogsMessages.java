package com.mango.blogs.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;


import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.bean.BlogsMood;
import com.mango.blogs.service.IserviceMessages;
import com.mango.dao.base.DaoSupport;

/**
 * 留言板service
 * @author dinghao
 *
 */
@Service("serviceblogsmessages")
@Transactional
public class ServiceBlogsMessages extends DaoSupport implements IserviceMessages {
	
	/**
	 * 
	 * 留言分页
	 * */
	public QueryResult<BlogsMessage> sele(int index,int userId) {
		QueryResult<BlogsMessage> qr= this.getScrollData(BlogsMessage.class, index, 10," user_id = "+userId,null);
		return qr;
	}
	/**
	 * 获取最新的messages
	 * @return
	 */
	public QueryResult<BlogsMessage> selectMessagesLast(int userId) {
		LinkedHashMap<String, String> lhm = new  LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		QueryResult<BlogsMessage> qr = this.getScrollData(BlogsMessage.class, 0, 2, " user_id = "+userId, lhm);
		return qr;
	}


	/**
	 * 添加留言
	 */
	public boolean addMessages(BlogsMessage blmg) {
		try {
			this.save(blmg);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 删除留言
	 */
	public int delectMessages(BlogsMessage blmg) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 获取分页查询的pageNum
	 */
	public int MessagePageNum(int PageNum,int MaxPageNum) {
		return ((PageNum >MaxPageNum ?  MaxPageNum: PageNum)- 1)*10;
	}
/*	public QueryResult<BlogsMessage> getMessageList(int PageNum,int MaxPageNum) {
	QueryResult<BlogsMessage> qr = this.sele(this.MessagePageNum(PageNum, PageNum));
	
	return qr;
	}*/


	
}
