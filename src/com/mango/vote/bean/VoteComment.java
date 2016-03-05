package com.mango.vote.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 投票内容bean
 * @author ck
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class VoteComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 投票ID
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer voteoptionid;
	/**
	 * 投票选项名字
	 */
	@Column(length=40,nullable=false)
	private String voteoptionname;
	/**
	 * 标题ID
	 */
	@Column(length=12,nullable=false)
	private Integer voteid;
	/**
	 * 投票人数
	 */
	@Column(length=12,nullable=false)
	private Integer voteoptionsum;
	
	
	
	public String getVoteoptionname() {
		return voteoptionname;
	}
	public void setVoteoptionname(String voteoptionname) {
		this.voteoptionname = voteoptionname;
	}
	public Integer getVoteoptionid() {
		return voteoptionid;
	}
	
	public void setVoteoptionid(Integer voteoptionid) {
		this.voteoptionid = voteoptionid;
	}

	public Integer getVoteid() {
		return voteid;
	}
	public void setVoteid(Integer voteid) {
		this.voteid = voteid;
	}
	public Integer getVoteoptionsum() {
		return voteoptionsum;
	}
	public void setVoteoptionsum(Integer voteoptionsum) {
		this.voteoptionsum = voteoptionsum;
	}
	
}
