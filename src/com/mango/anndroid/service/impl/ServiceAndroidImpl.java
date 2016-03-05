package com.mango.anndroid.service.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mango.android.service.IServiceAndroid;
import com.mango.bbs.bean.TopicComment;
import com.mango.bbs.bean.TopicTitle;
import com.mango.bean.QueryResult;
import com.mango.dao.base.DaoSupport;
/**
 * 
 * @author CrazyMango
 *
 */
@Transactional
@Service("serviceAndroid")
public class ServiceAndroidImpl extends DaoSupport implements IServiceAndroid {

	public boolean addTopic(TopicTitle topicTitle, TopicComment topicComment) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addTopicComment(TopicComment topicComment) {
		// TODO Auto-generated method stub
		return false;
	}

	public QueryResult<TopicTitle> findTopicTitle(int lastid) {
		QueryResult<TopicTitle> queryResult=new QueryResult<TopicTitle>();
		Query query=em.createQuery("select topicTitle from TopicTitle topicTitle  order by topicTitle.id desc");
		query.setFirstResult(lastid);
		query.setMaxResults(20);
		queryResult.setResultlist(query.getResultList());
		
		return queryResult;
	}

	public QueryResult<TopicComment> findComment(int topicTitleid,
			int startnumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
