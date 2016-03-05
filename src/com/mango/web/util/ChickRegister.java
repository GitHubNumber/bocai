package com.mango.web.util;

import java.util.regex.Pattern;
import javax.annotation.Resource;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;
/**
 * 
 * @author CrazyMango
 *
 */
public class ChickRegister {

	private final static String mailboxCheck="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	private final static String nicknameCheck="[\u4e00-\u9fa5]+";//判断是否是中文
	@Resource(name="serviceuser") IServiceUser serviceUser;
	/**
	 * 检测是否为空
	 * 不为空返回true
	 * @param str
	 * @return boolean
	 */
	public static boolean ChickEmpty(String str){
		Boolean boo=false;
		if(!str.trim().equals("")){
			boo=true;
		}
		return boo;
	}
	/**
	 * 检测邮箱格式是否正确
	 * 正确为true
	 * @param str
	 * @return boolean
	 */
	public static boolean ChickMail(String str){
		Boolean boo=false;
		Pattern regex=Pattern.compile(mailboxCheck);
		if(regex.matcher(str).matches()){
			boo=true;
		}
		return boo;
	}
	/**
	 * 检测是否为汉字,并且在1到6之间
	 * 为汉字就为true
	 * @param str
	 * @return boolean
	 */
	public static  boolean ChickChinaChar(String str){
		Boolean boo=false;
		Pattern regex=Pattern.compile(nicknameCheck);
		if(regex.matcher(str).matches()&&str.length()>=1&&str.length()<=6){
			boo=true;
		}
		return boo;
	}
	/**
	 * 检测密码是否8<=pass<=14个字符
	 * 正确为true
	 * @return 
	 */
	public static  boolean ChickPassword(String str){
		Boolean boo=false;
		if(8<=str.length()&&str.length()<=14){
			boo=true;
		}
		return boo;
	}
	/**
	 * 检测注册是否为空 false不为空
	 * @param mail
	 * @param password
	 * @param agpassword
	 * @param nickname
	 * @return
	 */
	public static  boolean ChickUser(User user){
		Boolean boo=false;
		if(ChickMail(user.getMailbox())){
			if(ChickEmpty(user.getPassword())&&user.getPassword().equals(user.getAgainpassword())&&ChickChinaChar(user.getNickname())){
				boo=true;
			}
		}
		return boo;
	}
	public static void main(String[] args) {
		User user=new User();
		user.setMailbox("123@qq.com");
		user.setPassword("123456789");
		user.setAgainpassword("123456789");
		user.setNickname("大");
		user.setChecking("asd");
		System.out.println(new ChickRegister().ChickUser(user));
//		System.out.println(new ChickRegister().ChickChinaChar(user.getNickname()));
	}
}
