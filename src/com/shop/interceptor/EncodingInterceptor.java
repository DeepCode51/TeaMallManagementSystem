package com.shop.interceptor;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class EncodingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		/*String method = request.getMethod();
		if (method.equalsIgnoreCase("post")) {
			request.setCharacterEncoding("utf-8");
		} else {
//			new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
			Iterator iter = request.getParameterMap().values().iterator();
			while (iter.hasNext()) {
				String[] parames = (String[]) iter.next();
				for (int i = 0; i < parames.length; i++) {
					parames[i] = new String(parames[i].getBytes("iso8859-1"),
							"utf-8");// 此处utf-8与页面编码一样
				}
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
}
