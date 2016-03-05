package com.mango.blogs.service;

import java.util.List;


import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsLog;
import com.mango.blogs.bean.BlogsMessage;
import com.mango.blogs.bean.BlogsMood;

public interface IserviceMessages {
	public QueryResult<BlogsMessage> sele(int index,int userId);
	public boolean addMessages(BlogsMessage blmg);
	public int delectMessages(BlogsMessage blmg);
	public QueryResult<BlogsMessage> selectMessagesLast(int userId);
}