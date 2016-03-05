package com.mango.bbs.service;

import com.mango.bbs.bean.TopicComment;
import com.mango.bbs.bean.TopicTitle;
import com.mango.bean.QueryResult;
/**
 * 
 * @author CrazyMango
 *
 */
public interface IServiceTopic {

	/**
	 * 发表一个主题
	 * @param topicTitle 主题实体
	 * @param topicComment 评论实体
	 * @return
	 */
	public boolean addTopic(TopicTitle topicTitle,TopicComment topicComment);
	/**
	 * 发表评论
	 * @param topicComment 评论实体
	 * @return
	 */
	public boolean addTopicComment(TopicComment topicComment);
	/**
	 * 查找主题最后回复的25
	 * @param Startnumber 页数
	 * @return
	 */
	public QueryResult<TopicComment> findTopicTitle(int startnumber);
	/**
	 * 查找主题的评论
	 * @param topicTitleid 主题id
	 * @param startnumber 开始页数
	 * @return
	 */
	public QueryResult<TopicComment> findComment(int topicTitleid,int startnumber);
}
