package com.junit;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mango.bbs.bean.TopicComment;
import com.mango.bbs.service.IServiceTopic;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsPhotoUpload;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;

public class TestOfJpa extends TestCase {

	private IServiceUser serviceuser; 

	public void testJpa(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("mango");
	}
	public void testSpring(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
//		serviceuser= (ServiceUser) ctx.getBean("serviceuser");
//		User user=new User();
//		user.setMailbox("351828195@qq.com");
//		user.setPassword("111");
		//		user.setNickname("aaa");
		//		serviceuser.login(user);
	}
	public void addManyUser(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		serviceuser= (IServiceUser) ctx.getBean("serviceuser");
		for(int i=0;i<10;i++){
			User user=new User();
			user.setMailbox(i+"@qq.com");
			user.setPassword(i+"121314");
			user.setNickname(i+"呵呵");
			try {
				serviceuser.register(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void testlogin(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		serviceuser= (IServiceUser) ctx.getBean("serviceuser");
		User Landuser=new User();
		Landuser.setMailbox("0@qq.m");
		Landuser.setPassword("0121314");
		User landuser= serviceuser.login(Landuser);
		if(landuser!=null){
			System.out.println("getuser----"+landuser.getMailbox()+"-----"+landuser.getId());
		}
		System.out.println("失败");
	}
	public void testgetTopicTitle(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic ServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
		
		QueryResult<TopicComment> topicComment=ServiceTopic.findTopicTitle(1);
//		for(TopicComment topic:topicComment.getResultlist()){
//			System.out.println("-------发帖人："+topic.getTopictitleid().getUserid().getNickname()+"发帖时间："+topic.getTopictitleid().getReleaseTime()+"主题："+topic.getTopictitleid().getTopic()+
//					"最后回复："+topic.getAnswertime()+"最后回复人："+topic.getUserid().getNickname()
//			);
//		}
		System.out.println(new BlogsPhotoUpload().getSavePath());
	}
}
