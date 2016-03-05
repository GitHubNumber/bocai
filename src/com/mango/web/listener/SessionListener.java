package com.mango.web.listener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.*;

import com.mango.user.bean.User;
/**
 * 
 * @author CrazyMango
 *
 */
public class SessionListener implements HttpSessionListener,ServletRequestListener
{
	private static HashMap<String, String> hUserName = new HashMap<String, String>();
	private static MySessionContext sessionContext= MySessionContext.getInstance();
	private HttpServletRequest  request;
	private HttpSession firstSession;
	public void requestDestroyed(ServletRequestEvent arg0) {
	}
	public void requestInitialized(ServletRequestEvent arg0) {
		request=(HttpServletRequest)arg0.getServletRequest();
	}
	public void sessionCreated(HttpSessionEvent se){
    	firstSession=se.getSession();
    	User user=new User();
    	user.setIP(request.getLocalAddr());
    	user.setLangingtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(System.currentTimeMillis())).toString());
 		firstSession.setAttribute("landinguser", user);
    	sessionContext.AddSession(firstSession);
     }
	public void sessionDestroyed(HttpSessionEvent arg0) {
		 hUserName.remove(arg0.getSession().getId());
		 sessionContext.DelSession(arg0.getSession());
	}
	/**
	 * 检测map里是否有mailbox,如果没有就在map里key=sessionid value=mailbox
	 * @param session
	 * @param user
	 * @return
	 */
	public static boolean isAlreadyEnter(HttpSession session,User landinguser){
		boolean flag ;
		//遍历Map中是否有用户的账号
		if(hUserName.containsValue(landinguser.getMailbox())){
			//通过key删除value
			Iterator<Entry<String, String>> iter = hUserName.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if( ( (String)val ).equals(landinguser.getMailbox()) ){
					//该方法同时移除
					romoreSession(key.toString());
				}
			}
			flag = false;
		}
		else{
			//用户第一次登陆
			flag = true;
			hUserName.put(session.getId(),landinguser.getMailbox());
		}
		return flag;
	}
	/**
	 * 注销用户
	 * @param key
	 * @return
	 */
	public static boolean romoreSession(String key){
		/*false表示注销失败*/
		boolean bool=true;
		try{
		sessionContext.getSession(key.toString()).setAttribute("landinguser",new User() );
		hUserName.remove(key);
		}catch(Exception e){
			bool=false;
		}
		return bool;
	}
}