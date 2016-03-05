package com.mango.shop.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.dao.base.DaoSupport;
import com.mango.shop.bean.Commodity;
import com.mango.shop.bean.Indent;
import com.mango.shop.service.IShopService;
import com.mango.user.bean.User;
@Transactional
@Service("shopService")
public class ShopService extends DaoSupport implements IShopService{
	/**
	 * 根据id得到一条数据
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	 public Commodity selCommodity(int id){
		 return em.find(Commodity.class, id);
	  }
	 /**
	  * 根据类型查询首页显示的商品
	  */
	 public List<Commodity> selAllCommodity(int type){
		 Query query=(Query) em.createQuery("from Commodity where type=:type");
		 query.setParameter("type", type);
		 query.setFirstResult(0);
		 query.setMaxResults(8);
		 return query.getResultList();
		 
	 }
	 /**
	  *查询所有商品售出数量前五的商品
	  */
	 public List<Commodity> selSellSumMore(){
		 Query query=(Query) em.createQuery("from Commodity order by sellsum desc");
		 query.setFirstResult(0);
		 query.setMaxResults(5);
		 return query.getResultList();
	 }
	 /**
	  *根据类型查询最新前五商品
	  */
	 public List<Commodity> selTypeMaxNew(int type){
		 Query query=(Query) em.createQuery("from Commodity where type=:type order by id desc");
		 query.setParameter("type", type);
		 query.setFirstResult(0);
		 query.setMaxResults(5);
		 return query.getResultList();
	 }
	 /**
	  *查出全部商品的指定几条数据 
	  */
	 public List<Commodity> selAllShop(int startnum){
		 Query query=(Query) em.createQuery("from Commodity");
		 query.setFirstResult(startnum);
		 query.setMaxResults(16);
		 return query.getResultList();
	 }
	 /**
	  * 查出全部头像的指定几条数据 
	  */
	 public List<Commodity> selTypeShop(int startnum,int type){
		 Query query=(Query) em.createQuery("from Commodity where type=:type");
		 query.setParameter("type", type);
		 query.setFirstResult(startnum);
		 query.setMaxResults(16);
		 return query.getResultList();
	 }
	 /**
	  * 查询所有精品前五的商品 
	  */
	 public List<Commodity> selMaxGood(){
		 Query query=(Query) em.createQuery("from Commodity where showmain=1 order by id desc");
		 query.setFirstResult(0);
		 query.setMaxResults(5);
		 return query.getResultList();
	 }
	 /**
	  * 查询所有商品的记录数 
	  */
	 
	 public int selAllCount(){
		 Query query=(Query) em.createQuery("select count(*) from Commodity");
		 String count=String.valueOf((Long)query.getSingleResult());
		 return Integer.parseInt(count);
	 }
	 /**
	  * 查询所有分类的记录数 
	  */
	 public int selTypeCount(int type){
		 Query query=(Query) em.createQuery("select count(*) from Commodity where type=:type");
		 query.setParameter("type", type);
		 String count=String.valueOf((Long)query.getSingleResult());
		 return Integer.parseInt(count);
	 }
	 /**
	  * 添加一个商品
	  */
	 public void inserShop(Commodity comm){
		 em.persist(comm);
	 }
	 /**
	  * 删除一个商品
	  */
	 public void delShop(int id){
		 em.remove(id);
	 }
	 /**
	  * 修改一个商品的信息
	  */
	 public void updateCommodity(Commodity commo){
		 em.merge(commo);
	 }
	 /**
	  * 根据id查出商品卖出数量 
	  */
	 public int getShopSellsum(int id){
		 Query query=(Query) em.createQuery("select sellsum from Commodity where id=:id");
		 query.setParameter("id", id);
		 String count=String.valueOf((Long)query.getSingleResult());
		 return Integer.parseInt(count);
	 }
	 /**
	  * 添加交易记录
	  */
	 public void addBuyRecord(Indent indent){
		 em.persist(indent);
	 }
	 /**
	  * 订单分页查询
	  */
	 public List<Indent> getAllRecord(int userid,int startnum){
		 Query query=(Query) em.createQuery("from Indent where userid=:userid order by id desc");
		 query.setParameter("userid", userid);
		 query.setFirstResult(startnum);
		 query.setMaxResults(10);
		 return (List<Indent>) query.getResultList();
	 }
	 /**
	  *查询所有交易的记录数 
	  */
	 public int selBuyCount(int userid){
		 Query query=(Query) em.createQuery("select count(*) from Indent where userid=:userid");
		 query.setParameter("userid", userid);
		 String count=String.valueOf((Long)query.getSingleResult());
		 return Integer.parseInt(count);
	 }
	 /**
	  * 查询今日的交易额 查询今日的交易额
	  */
	 public String sumTodaySale(String time){
		 Query query=(Query) em.createQuery("select sum(pricesum) from Indent where buytime like ':time'");
		 query.setParameter("time", time);
		 String count=String.valueOf((Long)query.getSingleResult());
		 return count;
	 }
	
	 
}
