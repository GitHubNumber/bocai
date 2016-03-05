package com.mango.user.service;

import com.mango.user.bean.User;
/**
 * 
 * @author CrazyMango
 *
 */
public interface IServiceUser {
	/**
	 * 
	 * 提供登陆方法
	 * @param user
	 * @return
	 */
	public abstract User login(User langinguser);
	/**
	 * 
	 * 提供注册方法
	 * @throws Exception 
	 */
	public abstract boolean register(User user);
	/**
	 * 检测账号是否存在
	 * 没找到为false
	 * @return
	 */
	public abstract boolean checkMailbox(String mailbox);
	/**
	  * 修改用户信息
	  */
	 public abstract void updUserInfo(User user);
	 /**
	  * 查询用户信息
	  */
	 public abstract User selUserInfo(int userid);
}