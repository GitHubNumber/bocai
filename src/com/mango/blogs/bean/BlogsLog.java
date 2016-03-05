package com.mango.blogs.bean;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 博客日志实体表
 * 
 * @author Administrator
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsLog implements Serializable {

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
	 * 日志内容
	 */
	@Column(columnDefinition = "Text")
	private String log;
	/**
	 * 日志标题
	 */
	private String log_title;
	/**
	 * 发表日期
	 */
	private Date date;
	/**
	 * 日志赞的次数
	 */
	@Column(length = 11,  columnDefinition = "")
	private Integer praise;
	/**
	 * 转载自谁的日志
	 */
	@Column(length = 255, columnDefinition = "")
	private String transshipment;
	/**
	 * 被转载的次数
	 */
	@Column(length = 11 )
	private Integer transshipment_num;
	/**
	 * 日志类型
	 */
	private String logType;
	
	
	
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

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public String getTransshipment() {
		return transshipment;
	}

	public void setTransshipment(String transshipment) {
		this.transshipment = transshipment;
	}

	public Integer getTransshipment_num() {
		return transshipment_num;
	}

	public void setTransshipment_num(Integer transshipment_num) {
		this.transshipment_num = transshipment_num;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLog_title() {
		return log_title;
	}

	public void setLog_title(String log_title) {
		this.log_title = log_title;
	}
	
}