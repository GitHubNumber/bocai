package com.mango.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author CrazyMango
 *
 * @param <T>
 */
public class QueryResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 结果集
	 */
	private List<T> resultlist;
	/**
	 * 总记录数
	 */
	private long totalrecord;
	/**
	 * 当前页
	 * @return
	 */
	private int nowpage=0;
	/**
	 * 当前评论id
	 * @return
	 */
	private int commentid;
	
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	
}
