package com.mango.dao.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mango.bean.QueryResult;
import com.mango.dao.DAO;
/**
 * 
 * @author CrazyMango
 *
 */
@Transactional
public abstract class DaoSupport implements DAO {

	@PersistenceContext protected EntityManager em;
	public void save(Object entity){
		em.persist(entity);
	}
	public void update(Object entity) {
		em.merge(entity);
	}
	public <T> void delete(Class<T> entityClass, Object entityId) {
		delete(entityClass,new Object[]{entityId});
	}

	public <T> void delete(Class<T> entityClass, Object[] entitys) {
		for(Object id:entitys){
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityId) {
		return em.find(entityClass, entityId);
	}
	/**
	 * 得到一个实例,通过  key=mailbox,value=3123@qq.com就是mailbox=3123@qq.com的条件
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> List<T> find(Class<T> entityClass,LinkedHashMap<String,String> term) {
		List<T> list=new ArrayList<T>();
		String entityname= getEntityName(entityClass);
		Query query=em.createQuery("select o from "+entityname+" o "+"where "+buildAnd(term));
		list=query.getResultList();
		return list;
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult){
		return getScrollData(entityClass,firstindex,maxresult,null,null);
	}
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult,String whereSql){
		return getScrollData(entityClass,firstindex,maxresult,whereSql,null);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
		int firstindex, int maxresult,String whereSql,LinkedHashMap<String,String> orderby){
		QueryResult<T> qr=new QueryResult<T>();
		String entityname=getEntityName(entityClass);
		//select * from user order by onlinetime desc,id asc;
		Query query=em.createQuery("select o from "+entityname+" o "+(whereSql==null? "":" where o."+whereSql)+buildOrderby(orderby));
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setResultlist(query.getResultList());
		query=em.createQuery("select count(o) from "+entityname+" o "+(whereSql==null? "":" where o."+whereSql));
		qr.setTotalrecord((Long)query.getSingleResult());
		
		return qr;
	}
	/**
	 * 组装and语句
	 * @param term
	 * @returnh
	 */
	protected  String buildAnd(LinkedHashMap<String,String> term){
		StringBuffer orderbyql=new StringBuffer();
		if(term!=null&&term.size()>0){
			for(String key:term.keySet()){
				orderbyql.append(key).append("=").append("'").append(term.get(key)).append("'").append(" and ");
			}
			orderbyql.setLength(orderbyql.length()-5);
		}
		return orderbyql.toString();
	}
	
	/**
	 * 组装Order by语句
	 * @param orderby
	 * @return
	 */
	protected String buildOrderby(LinkedHashMap<String,String> orderby){
		StringBuffer orderbyql=new StringBuffer();
		if(orderby!=null&&orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key:orderby.keySet()){
				orderbyql.append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取类的简单名称
	 * @param entityClass
	 * @return
	 */
	protected <T> String getEntityName(Class<T> entityClass){
		String entityname=entityClass.getSimpleName();
		Entity entity=entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null&&!"".equals(entity.name())){
			entityname=entity.name();
		}
		return entityname;
	}
}