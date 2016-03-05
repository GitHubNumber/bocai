package com.mango.dao;

import java.util.LinkedHashMap;
import java.util.List;
import com.mango.bean.QueryResult;
/**
 * 
 * @author CrazyMango
 *
 */
public interface DAO {

	/**
	 * 保存实体
	 * @param entity 实体
	 * @throws Exception 
	 */
	public void save(Object entity) throws Exception;
	/**
	 * 更新实体
	 * @param entity 实体
	 */
	public void update(Object entity);
	/**
	 * 删除实体
	 * @param entityClass 实体类型
	 * @param entityId 实体数组
	 */
	public <T> void delete(Class<T> entityClass,Object entityId);
	/**
	 * 删除多个实体
	 * @param entityClass 实体类型
	 * @param entitys 实体id数组
	 */
	public <T> void delete(Class<T> entityClass,Object[] entitys);
	/**
	 * 获取实体
	 * @param entityClass 实体类型
	 * @param entityId 实体id
	 * @return
	 */
	public <T> T find(Class<T> entityClass,Object entityId);
	/**
	 * 根据实体的条件来查询数据
	 * @param entityClass
	 * @param term
	 * @return
	 */
	public <T> List<T> find(Class<T> entityClass,LinkedHashMap<String,String> term);
	/**
	 * 获取分页数据
	 * @param entityClass 实体类
	 * @param firstindex 开始索引
	 * @param maxresult 需要获取的记录数
	 * @param LinkedHashMap 查找实现排序 根据key(实体属性)  和value(ASC/DESC) 
	 * @return
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex,int maxresult,String whereSql,LinkedHashMap<String,String> orderby);
}
