package com.mango.user.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;
import com.mango.web.listener.MySessionContext;
import com.mango.web.listener.SessionListener;
import com.mango.web.util.ChickRegister;
import com.mango.web.util.DateTimeFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户处理
 * @author CrazyMango
 *
 */
@Controller("actionUser")@Scope("prototype")
public class ActionUser extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="serviceuser") private IServiceUser serviceuser;
	@Resource(name="promptResult")private PromptResult promptResult;
	private HttpSession  session;
	private User user;
	protected static final Logger logger = Logger.getLogger(ActionUser.class.getName());
	/**
	 * 为用户提供
	 * @return
	 */
	public String login(){
		User landuser=serviceuser.login(user);
		
		if(landuser==null){
			promptResult.setMessage("用户名或密码错误");
			logger.debug("用户【"+landuser.getMailbox()+"】 时间:"+DateTimeFormat.formatDateTime(new Date())+",异常：用户名或者密码错误！");
		}else{
			MySessionContext mySessionContext=MySessionContext.getInstance();
			session=mySessionContext.getSession(ServletActionContext.getRequest().getSession().getId());
			User landeduser= (User) session.getAttribute("landinguser");
			landuser.setIP(landeduser.getIP());
			landuser.setLangingtime(landeduser.getLangingtime());
			session.setAttribute("landinguser", landuser);
			boolean flag=SessionListener.isAlreadyEnter(session, landuser);
			/**
			 * 待实现
			 */
			if(!flag){
				promptResult.setMessage("你的账号异常登录，请修改密码！<meta http-equiv='refresh' content='3;>");
			}
		}
		
		return "message";
	}
	/**
	 * 为用户提供注销
	 * @return
	 */
	public String loginOut(){
		Boolean bool=SessionListener.romoreSession(ServletActionContext.getRequest().getSession().getId());
		if(bool){
			promptResult.setImagename(6);
			promptResult.setTitle("成功");
			promptResult.setMessage("注销成功");
		}else{
			promptResult.setImagename(4);
			promptResult.setTitle("错误");
			promptResult.setMessage("注销失败");
		}
		return "message";
	}
	public String register(){
		String userchecking=user.getChecking();
		String checking=(String) ActionContext.getContext().getSession().get("checkCode");
		if(userchecking.trim().equalsIgnoreCase(checking)&&ChickRegister.ChickUser(user)&&serviceuser.register(user)){
			
			promptResult.setImagename(6);
			promptResult.setTitle("恭喜你,注册成功");
			promptResult.setMessage("为了你的账号安全,请验证邮箱");
		}else{
			promptResult.setImagename(4);
			promptResult.setTitle("错误");
			promptResult.setMessage("你的信息有误,请核证后注册");
		}
		
		return "message";
	}
	/**
	 * 得到验证码
	 * @throws IOException
	 */
	public void getvalidate() throws IOException{
		Random random = new Random();
		int width=80;
		int height=20;
		char[] num = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        String str="";
        BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        ServletContext context=ServletActionContext.getServletContext();
        String path=context.getRealPath("/")+"images\\common\\chickcode2.png";
        String path1=path.replace("\\", "/");
        g.drawImage(new ImageIcon(path1).getImage(), 0, 0, null);
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(font);
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            String tem = String.valueOf(num[random.nextInt(62)]);
            str += tem;
            g.drawString(tem, 15 * i + 16, random.nextInt(5)+15);
        }
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(2);
            int y1 = random.nextInt(2);
            g.drawLine(x, y, x + x1, y + y1);
        }
        ActionContext.getContext().getSession().put("checkCode", str);
        ServletOutputStream sos=ServletActionContext.getResponse().getOutputStream();
		ImageIO.write(bi, "jpeg", sos);
		sos.close();
	}
	/**
	 * ajax检测邮箱是否可用
	 * @return
	 * @throws SQLException
	 */
	public String checkmailbox(){
		String mailbox=user.getMailbox();
		if(!ChickRegister.ChickMail(mailbox)){
			promptResult.setImagename(13);
		}else{
			if(!serviceuser.checkMailbox(mailbox)){
			promptResult.setImagename(12);
			}else{
			promptResult.setImagename(13);
			promptResult.setMessage("该邮箱已注册");
			}
		}
			return "success";
	}
	/**
	 * ajax检测验证码
	 * @return
	 */
	public String checkChecking(){
		String Chicking=user.getChecking();
		
		String serverValidate=(String) ActionContext.getContext().getSession().get("checkCode");
		if(serverValidate.equalsIgnoreCase(Chicking)){
			promptResult.setImagename(12);
		}else{
			promptResult.setImagename(13);
		}
		return "success";
	}
	/**
	 * ajax得到用户信息
	 * @return
	 */
	public String ajaxUser(){
		user= serviceuser.selUserInfo(user.getId());
		user.setPassword("");
		return "success";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PromptResult getPromptResult() {
		return promptResult;
	}
	public void setPromptResult(PromptResult promptResult) {
		this.promptResult = promptResult;
	}
	
}