package com.mango.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 
 * @author CrazyMango
 *
 */
public class UTF8Filter implements Filter  {
	public void doFilter(ServletRequest srequest,ServletResponse sresponse,FilterChain chain) throws IOException, ServletException {
		srequest.setCharacterEncoding("utf-8");
		chain.doFilter(srequest, sresponse);
	}
	public void init(FilterConfig arg0) throws ServletException {}
	public void destroy() {}
}
