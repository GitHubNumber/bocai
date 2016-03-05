package com.mango.bbs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.mango.user.bean.User;
/**
 * 
 * @author CrazyMango
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class TopicTitle implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 帖子的ID
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 帖子的主题,最多能为80个汉字
	 */
	@Column(length=80,nullable=false)
	private String topic;
	/**
	 * 发帖人的ID,映射到User
	 */
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="user_id")
	private User userid;
	/**
	 * 帖子发表时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseTime=new Date();
	/**
	 * 帖子等级
	 */
	@Column(length=2,nullable=false)
	private Integer topicRank=10;
	/**
	 * 帖子的点击量
	 */
	@Column(length=10,nullable=false)
	private Integer chickNum=1;
	/**
	 * 帖子的回复量
	 */
	@Column(length=10,nullable=false)
	private Integer answerNum=1;
	/**
	 * 悬赏金币数
	 */
	@Column(length=3,nullable=false)
	private Integer gold=0;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getTopicRank() {
		return topicRank;
	}
	public void setTopicRank(Integer topicRank) {
		this.topicRank = topicRank;
	}
	public Integer getChickNum() {
		return chickNum;
	}
	public void setChickNum(Integer chickNum) {
		this.chickNum = chickNum;
	}
	public Integer getAnswerNum() {
		return answerNum;
	}
	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}

	@Override
	public String toString() {
		return "TopicTitle [id=" + id + ", topic=" + topic + ", userid="
				+ userid.getNickname()+ ", releaseTime=" + releaseTime + " answerNum=" + answerNum;
	}
}
