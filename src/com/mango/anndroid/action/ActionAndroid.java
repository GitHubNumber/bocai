package com.mango.anndroid.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.android.service.IServiceAndroid;
import com.mango.bbs.bean.TopicTitle;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author CrazyMango
 *
 */
@Controller("android")@Scope("prototype")
public class ActionAndroid extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="serviceAndroid")private IServiceAndroid iServiceAndroid;
	private List<TopicTitle> topicList;
	public String getTopicTitle(){
		
		topicList=iServiceAndroid.findTopicTitle(0).getResultlist();
		
		return "success";
	}
	public List<TopicTitle> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<TopicTitle> topicList) {
		this.topicList = topicList;
	}
	
}
