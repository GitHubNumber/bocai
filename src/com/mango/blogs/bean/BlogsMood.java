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
 * 博客心情表
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsMood implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**
	 *用户id 
	 */
	@Column(length=11,nullable=false)
	private Integer user_id;
	/**
	 *mood发表的心情
	 */
	@Column(nullable=false,columnDefinition = "text")
	private String mood;
	/**
	 *记录被人赞的数目
	 */
	@Column(length=11,nullable=true)
	private Integer praise;
	/**
	 *心情发表的时间
	 */
	@Column(length=11,nullable=true)
	private Date date = new Date(System.currentTimeMillis()) ;
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
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<BlogsComment> getBlogsComment() {
		return blogsComment;
	}
	public void setBlogsComment(List<BlogsComment> blogsComment) {
		this.blogsComment = blogsComment;
	}
}
