package com.mango.user.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
/**
 * 
 * @author CrazyMango
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 用户邮箱、登录账号
	 */
	@Column(length=40,nullable=false,unique=true)
	private String mailbox;
	/**
	 * 用户密码
	 */
	@Column(length=12,nullable=false)
	private String password;
	/**
	 * 再次输入密码
	 */
	@Transient
	private String againpassword;
	/**
	 * 用户昵称
	 */
	@Column(length=10,nullable=false)
	private String nickname;
	/**
	 * 用户的生日
	 */
	@Column(length=19)
	private String birthday;
	/**
	 * 性别
	 */
	@Column(length=1)
	private Integer sex=0;
	/**
	 * 注册时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date registertime=new Date();
	/**
	 * 积分
	 */
	@Column(length=10)
	private Integer integral=0;
	/**
	 * 贡献度
	 */
	@Column(length=10)
	private Integer contribute=0;
	/**
	 * 在线时长
	 */
	@Column(length=10)
	private Integer onlinetime=1;
	/**
	 * 权限
	 */
	@Column(length=2)
	private Integer power=0;
	/**
	 * 魅力
	 */
	@Column(length=5)
	private Integer charm=0;
	/**
	 * 等级
	 */
	@Column(length=2)
	private Integer grade=0;
	/**
	 * 关注值
	 */
	@Column(length=3)
	private Integer attention=0;
	/**
	 * 个性签名
	 */
	@Column(length=100)
	private String autograph;
	/**
	 * 头像
	 */
	@Column(length=25)
	private String headpic;
	/**
	 * 验证码
	 */
	@Transient
	private String checking;
	/**
	 * 登录时IP
	 */
	@Transient
	private String IP;
	/**
	 * 登录时间
	 */
	@Transient
	private String langingtime;
	
	public User(){}
	public User(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getContribute() {
		return contribute;
	}
	public void setContribute(Integer contribute) {
		this.contribute = contribute;
	}
	public Integer getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(Integer onlinetime) {
		this.onlinetime = onlinetime;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getCharm() {
		return charm;
	}
	public void setCharm(Integer charm) {
		this.charm = charm;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getAttention() {
		return attention;
	}
	public void setAttention(Integer attention) {
		this.attention = attention;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	public String getAgainpassword() {
		return againpassword;
	}
	public void setAgainpassword(String againpassword) {
		this.againpassword = againpassword;
	}
	public String getChecking() {
		return checking;
	}
	public void setChecking(String checking) {
		this.checking = checking;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getLangingtime() {
		return langingtime;
	}
	public void setLangingtime(String langingtime) {
		this.langingtime = langingtime;
	}
	
	
}
