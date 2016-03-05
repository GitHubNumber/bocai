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
 * 博客照片表
 * 
 * @author Administrator
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsPhoto implements Serializable {

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
	 * 照片 只用来存放路径
	 */
	@Column(nullable = false)
	private String photo;
	/**
	 * group 照片的分组id
	 */
	@Column(length = 11, nullable = false)
	private Integer group_id;
	/**
	 * 照片发布时间
	 */
	@Column(nullable = false)
	private Date date = new Date(System.currentTimeMillis());
	/**
	 * 照片简介
	 */
	@Column(nullable = false)
	private String photoInfo;
	
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhotoInfo() {
		return photoInfo;
	}

	public void setPhotoInfo(String photoInfo) {
		this.photoInfo = photoInfo;
	}

}
