package com.mango.blogs.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 博客留言表
 * 
 * @author Administrator
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(length = 11, nullable = false)
	private Integer user_id;
	/**
	 * 留言时间
	 */
	@Column(nullable = false)
	private Date date = new Date(System.currentTimeMillis());
	/**
	 * 留言者的id 是谁为正在使用中的user用户留的言
	 */
	@Column(length = 11, nullable = false)
	private Integer message_user_id;
	/**
	 * 留言的内容
	 */
	@Column(nullable = false,columnDefinition = "text")
	private String message;
	/**
	 * 评论List
	 */
	@Transient
	private List<BlogsComment> blogsComment;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMessage_user_id() {
		return message_user_id;
	}

	public void setMessage_user_id(Integer message_user_id) {
		this.message_user_id = message_user_id;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BlogsComment> getBlogsComment() {
		return blogsComment;
	}

	public void setBlogsComment(List<BlogsComment> blogsComment) {
		this.blogsComment = blogsComment;
	}
	@Override
	public String toString() {
		return "BlogsMessage [id=" + id + ", user_id=" + user_id + ", date="
				+ date + ", message_user_id=" + message_user_id + ", message="
				+ message + "]";
	}
}
