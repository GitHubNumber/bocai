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
 * 订单类
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Indent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(length=10,nullable=false)
	private Integer shopid;
	@Column(length=10,nullable=false)
	private Integer userid;
	@Column(length=225,nullable=false)
	private String shopname;
	@Column(length=10,nullable=false)
	private Integer shopnum;
	@Column(length=10,nullable=false)
	private Integer pricesum;
	@Column(length=225,nullable=false)
	private String buytime;
	@Column(length=10,nullable=false)
	private Integer shopprice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public int getShopnum() {
		return shopnum;
	}
	public void setShopnum(int shopnum) {
		this.shopnum = shopnum;
	}
	public int getPricesum() {
		return pricesum;
	}
	public void setPricesum(int pricesum) {
		this.pricesum = pricesum;
	}
	public String getBuytime() {
		return buytime;
	}
	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	public int getShopprice() {
		return shopprice;
	}
	public void setShopprice(int shopprice) {
		this.shopprice = shopprice;
	}
}
