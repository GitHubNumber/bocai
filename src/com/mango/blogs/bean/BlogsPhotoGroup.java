package com.mango.blogs.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class BlogsPhotoGroup implements Serializable {
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
	 * 照片分组信息
	 */
	private String photoGroup;
	/**
	 * 封面 
	 */
	private String cover;
	
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

	public String getPhotoGroup() {
		return photoGroup;
	}

	public void setPhotoGroup(String photoGroup) {
		this.photoGroup = photoGroup;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}
