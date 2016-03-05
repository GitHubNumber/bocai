package com.mango.blogs.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 博客评论表
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsComment implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**
	 *用户id -->给谁发的评论
	 */
	@Column(length=11,nullable=false)
	private Integer user_id;
	/**
	 *留言类型
	 *在这里规定1为日志类型
	 *2为说说类型
	 *3为照片类型
	 *4为 留言类型
	 */
	@Column(length=1,nullable=false)
	private Integer type=0;
	/**
	 * 评论 longtext类型
	 * length默认长度为255
	 */
	@Column(nullable=false)
	private String comment;
	/**
	 * table_id
	 * 比如log_id的id
	 */
	@Column(nullable=false)
	private Integer table_id;
	
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getTable_id() {
		return table_id;
	}
	public void setTable_id(Integer table_id) {
		this.table_id = table_id;
	}
}