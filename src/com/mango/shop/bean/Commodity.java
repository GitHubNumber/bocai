package com.mango.shop.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 商品类
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Commodity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(length=225,nullable=false)
	private String name;
	@Column(length=10,nullable=false)
	private Integer type;
	@Column(length=225,nullable=false)
	private String introduce;
	@Column(length=10,nullable=false)
	private Integer price;
	@Column(length=10,nullable=false)
	private Integer charm;
	@Column(length=225,nullable=false)
	private String imgpath;
	@Column(length=10,nullable=false)
	private Integer showmain;
	@Column(length=10,nullable=false)
	private Integer sellsum;
	
	public int getShowmain() {
		return showmain;
	}
	public void setShowmain(int showmain) {
		this.showmain = showmain;
	}
	public int getSellsum() {
		return sellsum;
	}
	public void setSellsum(int sellsum) {
		this.sellsum = sellsum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCharm() {
		return charm;
	}
	public void setCharm(int charm) {
		this.charm = charm;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
}
