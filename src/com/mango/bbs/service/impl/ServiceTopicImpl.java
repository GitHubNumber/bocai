package com.mango.bbs.service.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mango.bbs.bean.TopicComment;
import com.mango.bbs.bean.TopicTitle;
import com.mango.bbs.service.IServiceTopic;
import com.mango.bean.QueryResult;
import com.mango.dao.base.DaoSupport;
import com.mango.user.bean.User;
/**
 * 
 * @author CrazyMango
 *
 */
@Transactional
@Service("serviceTopic")
public class ServiceTopicImpl extends DaoSupport implements IServiceTopic {

	public boolean addTopic(TopicTitle topicTitle,TopicComment topicComment) {
		User user= em.find(User.class, topicTitle.getUserid().getId());
		user.setIntegral(user.getIntegral()+1);
		user.setContribute(user.getContribute()+5);
		em.persist(topicTitle);
		topicComment.setTopictitleid(topicTitle);
		em.persist(topicComment);
		return true;
	}
	public boolean addTopicComment(TopicComment topicComment) {
		try{
			User user=em.find(User.class, topicComment.getUserid().getId());
			TopicTitle topicTitle=em.find(TopicTitle.class, topicComment.getTopictitleid().getId());
			//添加贡献度
			//添加回复+1
			topicTitle.setAnswerNum(topicTitle.getAnswerNum()+1);
			user.setContribute(user.getContribute()+2);
			this.save(topicComment);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<TopicComment> findTopicTitle(int startnumber) {
		QueryResult<TopicComment> queryResult=new QueryResult<TopicComment>();
		Query query=em.createQuery("select topiccomment from TopicComment topiccomment where topiccomment.answertime in (select max(topiccomment.answertime)from topiccomment group by topiccomment.topictitleid) ORDER BY topiccomment.answertime desc");
		query.setFirstResult((startnumber-1)*25);
		query.setMaxResults(25);
		queryResult.setResultlist(query.getResultList());
		query=em.createQuery("select count(o) from TopicTitle o where o.topicRank>=10");
		queryResult.setTotalrecord((Long) query.getSingleResult());
		queryResult.setNowpage(startnumber);
		return queryResult;
	}
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<TopicComment> findComment(int topicTitleid,int startnumber) {
		QueryResult<TopicComment> queryResult=new QueryResult<TopicComment>();
		Query query=em.createQuery("select topiccomment from TopicComment topiccomment where topictitleid="+topicTitleid+" ORDER BY topiccomment.answertime asc");

		query.setFirstResult((startnumber-1)*10);
		query.setMaxResults(10);
		queryResult.setResultlist(query.getResultList());
		query=em.createQuery("select count(o) from TopicComment o where o.topictitleid="+topicTitleid);
		queryResult.setTotalrecord((Long) query.getSingleResult());
		queryResult.setCommentid(topicTitleid);
		queryResult.setNowpage(startnumber);
		return queryResult;
	}
}
