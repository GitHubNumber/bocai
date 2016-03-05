package com.mango.bbs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
public class TopicComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 回复id
	 */
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 回复的帖子主题id
	 */
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="topictitleid")
	private TopicTitle topictitleid;
	/**
	 * 帖子中对评论的回复
	 */
	@Column(length=10,nullable=false)
	private Integer contentid=0;
	/**
	 * 回复用户id
	 */
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},optional=false)
	@JoinColumn(name="user_id")
	private User userid;
	/**
	 * 回复时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date answertime=new Date();
	/**
	 * 回复内容
	 */
	@Lob
	private String commentContent;
	/**
	 * 回复权限
	 */
	@Column(length=2,nullable=false)
	private Integer contentrank=0;
	/**
	 * 赞一个
	 */
	@Column(length=10,nullable=false)
	private Integer praise=0;
	/**
	 * 踩一个
	 */
	@Column(length=10,nullable=false)
	private Integer bad=0;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TopicTitle getTopictitleid() {
		return topictitleid;
	}
	public void setTopictitleid(TopicTitle topictitleid) {
		this.topictitleid = topictitleid;
	}
	public Integer getContentid() {
		return contentid;
	}
	public void setContentid(Integer contentid) {
		this.contentid = contentid;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	public Date getAnswertime() {
		return answertime;
	}
	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getContentrank() {
		return contentrank;
	}
	public void setContentrank(Integer contentrank) {
		this.contentrank = contentrank;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Integer getBad() {
		return bad;
	}
	public void setBad(Integer bad) {
		this.bad = bad;
	}
	
}
