package com.mango.web.interceptor;

import javax.annotation.Resource;

import com.mango.bean.PromptResult;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 
 * @author CrazyMango
 *
 */
public class PermissionInterceptor extends AbstractInterceptor {

	/**
	 * 权限拦截
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="promptResult") private PromptResult promptResult;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		User landinguser= (User) ActionContext.getContext().getSession().get("landinguser");
		System.out.println(landinguser.getPower());
		if(landinguser.getPower()>=10){
			return invocation.invoke();
		}
		promptResult.setTitle("权限错误");
		promptResult.setMessage("对不起，你的权限不足...");
		ActionContext.getContext().put("promptResult", promptResult);
		return "message";
	}

}
