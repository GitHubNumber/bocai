package com.mango.shop.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import com.mango.shop.bean.Commodity;
import com.mango.shop.bean.Indent;

public interface IShopService {
	/**
	 * 根据id得到一条数据
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	 public abstract Commodity selCommodity(int id);
	 /**
	  * 根据类型查询首页显示的商品
	  */
	 public abstract List<Commodity> selAllCommodity(int type);
	 /**
	  *查询所有商品售出数量前五的商品
	  */
	 public abstract List<Commodity> selSellSumMore();
	 /**
	  *根据类型查询最新前五商品
	  */
	 public abstract List<Commodity> selTypeMaxNew(int type);
	 /**
	  *查出全部商品的指定几条数据 
	  */
	 public abstract List<Commodity> selAllShop(int startnum);
	 /**
	  * 查出全部头像的指定几条数据 
	  */
	 public abstract List<Commodity> selTypeShop(int startnum,int type);
	 /**
	  * 查询所有精品前五的商品 
	  */
	 public abstract List<Commodity> selMaxGood();
	 /**
	  * 查询所有商品的记录数 
	  */
	 public abstract int selAllCount();
	 /**
	  * 查询所有分类的记录数 
	  */
	 public abstract int selTypeCount(int type);
	 /**
	  * 添加一个商品
	  */
	 public abstract void inserShop(Commodity comm);
	 /**
	  * 删除一个商品
	  */
	 public abstract void delShop(int id);
	 /**
	  * 修改一个商品的信息
	  */
	 public abstract void updateCommodity(Commodity commo);
	 /**
	  * 根据id查出商品卖出数量 
	  */
	 public abstract int getShopSellsum(int id);
	 /**
	  * 添加交易记录
	  */
	 public abstract void addBuyRecord(Indent indent);
	 /**
	  * 订单分页查询
	  */
	 public abstract List<Indent> getAllRecord(int userid,int startnum);
	 /**
	  *查询所有交易的记录数 
	  */
	 public abstract int selBuyCount(int userid);
	 /**
	  * 查询今日的交易额 查询今日的交易额
	  */
	 public abstract String sumTodaySale(String time);
}
