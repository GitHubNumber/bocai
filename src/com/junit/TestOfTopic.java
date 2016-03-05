package com.junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mango.bbs.bean.TopicComment;
import com.mango.bbs.bean.TopicTitle;
import com.mango.bbs.service.IServiceTopic;
import com.mango.bean.QueryResult;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;

import junit.framework.TestCase;

public class TestOfTopic extends TestCase{

	private IServiceUser serviceuser; 
	public void addManyUser(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		/*添加用户*/
		serviceuser= (IServiceUser) ctx.getBean("serviceuser");
//		for(int i=1;i<50;i++){
//			User user=new User();
//			user.setMailbox(i+"@qq.com");
//			user.setPassword(i+"121314");
//			user.setNickname(i+"呵呵");
//			System.out.println(serviceuser.register(user));
//		}
		User user=new User();
		user.setMailbox("admin");
		user.setPassword("admin");
		user.setNickname("CrazyMango");
		System.out.println(serviceuser.register(user));
	}
	public void addtopic(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic iServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
		/*添加主题*/
		User user=new User();

		user.setId(2);
		for(int i=0;i<300;i++){
			TopicTitle topictitle=new TopicTitle();
			TopicComment topicComment=new TopicComment();
			topicComment.setCommentContent("内容");
			topicComment.setUserid(user);
			topictitle.setTopic("测试第"+i+"帖");
			topictitle.setUserid(user);
			iServiceTopic.addTopic(topictitle,topicComment);
		}
	}
	public void addcomment(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic iServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
		
		/*添加回复*/
		User use=new User();
		use.setId(1);
		TopicTitle topictitle=new TopicTitle();
		topictitle.setId(1);
		for(int i=11;i<50;i++){
			
			TopicComment topicComment=new TopicComment();
			topicComment.setCommentContent("第"+i+"个评论");
			topicComment.setUserid(use);
			topicComment.setTopictitleid(topictitle);
			iServiceTopic.addTopicComment(topicComment);
		}
	}
	public void deletetopic(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic iServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
	}
	public void findtopic(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic iServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
//		QueryResult<TopicComment> query=iServiceTopic.findTopicTitle(50);
//		for(TopicComment topicComment:query.getResultlist()){
//			System.out.println(topicComment.getTopictitleid().getId()+" 主题："+topicComment.getTopictitleid().getTopic());
//		}
		iServiceTopic.findComment(1001,1);
	}
	public void checkmailbox(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceUser iServiceUser=(IServiceUser) ctx.getBean("serviceuser");
		System.out.println(iServiceUser.checkMailbox("351895@qq.com"));
	}
	public void findcomment(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceTopic ServiceTopic=(IServiceTopic) ctx.getBean("serviceTopic");
		
		QueryResult<TopicComment> topicComment=ServiceTopic.findComment(50,1);
		System.out.println(" $ "+topicComment.getResultlist().get(0).getTopictitleid().getChickNum());
		for(TopicComment topic:topicComment.getResultlist()){
			System.out.println(topic.getTopictitleid());
		}
		System.out.println(topicComment.getNowpage()+"  "+topicComment.getTotalrecord());
	}
}