package com.mango.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 提示类
 * @author CrazyMango
 *
 */
@Component("promptResult")@Scope("prototype")
public class PromptResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 友好提示内容
	 */
	private String message="";
	/**
	 * 友好提示标题
	 */
	private String title=null;
	/**
	 * 友好提示图片
	 */
	private int imagename;
	
	private String togo;
	
	public String getMessage() {
		return message;
	}
	/**
	 * 提示内容
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	/**
	 * 添加提示标题 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public int getImagename() {
		return imagename;
	}
	/**
	 * 添加一个图片的索引 
	 * @param imagename
	 */
	public void setImagename(int imagename) {
		this.imagename = imagename;
	}
	public String getTogo() {
		return togo;
	}
	/**
	 * 要去的页面
	 * @param togo
	 */
	public void setTogo(String togo) {
		this.togo = togo;
	}
	
	
}
