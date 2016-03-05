package com.junit;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mango.android.service.IServiceAndroid;
import com.mango.bbs.bean.TopicTitle;
import com.mango.bean.QueryResult;

import junit.framework.TestCase;

public class TestOfAndroid extends TestCase {
	
	public void testAndroid(){
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext/applicationContext.xml");
		IServiceAndroid iServiceAndroid= (IServiceAndroid) ctx.getBean("serviceAndroid");
		QueryResult<TopicTitle>  queryResult= iServiceAndroid.findTopicTitle(0);
		List<TopicTitle> list=queryResult.getResultlist();
		for(TopicTitle topicTitle:list){
			System.out.println(topicTitle.toString());
		}
	}
}
