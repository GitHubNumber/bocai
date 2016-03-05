package com.mango.user.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mango.dao.base.DaoSupport;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;
/**
 * 
 * @author CrazyMango
 *
 */
@Service("serviceuser")
@Transactional
public class ServiceUserImpl extends DaoSupport implements IServiceUser {
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public User login(User landinguser) {
		LinkedHashMap<String,String> linkedhashmap=new LinkedHashMap<String,String>();
		linkedhashmap.put("mailbox",landinguser.getMailbox());
		linkedhashmap.put("password",landinguser.getPassword());
		List<User> list=this.find(User.class, linkedhashmap);
		if(list.size()==1){
			User us= list.get(0);
			landinguser.setNickname(us.getNickname());
			landinguser.setPower(us.getPower());
			landinguser.setId(us.getId());
			return landinguser;
		}
		return null;
	}
	public boolean register(User user){
		boolean flag=false;
		if(user!=null){
			this.save(user);
			flag=true;
		}
		return flag;
	}
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public boolean checkMailbox(String mailbox) {
		Query query= em.createQuery("select o from User o where o.mailbox=:mailbox");
		query.setParameter("mailbox", mailbox);
		try{
			query.getSingleResult();
		}catch(Exception e){
			if(e.getMessage().equals("No entity found for query")){
				return false;
			}
		}
		return true;
	}
	/**
	  * 修改用户信息
	  */
	 public void updUserInfo(User user){
		 em.merge(user);
	 }
	 /**
	  * 查询用户信息
	  */
	 public User selUserInfo(int userid){
		 return (User)em.find(User.class, userid);
	 }
}
