package com.mango.web.listener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
/**
 * 
 * @author CrazyMango
 *
 */
public class MySessionContext {
	private static MySessionContext instance;
	private HashMap<String,HttpSession> mymap;
	private MySessionContext() {
		mymap = new HashMap<String, HttpSession>();
	}
	public static MySessionContext getInstance() {
		if (instance == null) {
			instance = new MySessionContext();
		}
		return instance;
	}
	/*
	 * 添加session
	 * */
	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}
	/*根据session删除session
	 * */
	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}
	/**
	 * 根据sessionid的到一个Session
	 * */
	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null) return null;
		return (HttpSession) mymap.get(session_id);
	}
	/**
	 * 得到在线人员的Map集合
	 * @return HashMap
	 */
	public HashMap<String, HttpSession> getMymap() {
		return mymap;
	}

	public void setMymap(HashMap<String, HttpSession> mymap) {
		this.mymap = mymap;
	}
}