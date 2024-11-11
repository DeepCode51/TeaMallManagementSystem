package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shop.po.Adminuser;

@Controller
public class PrivilageInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		String uri = request.getRequestURI();
		if(uri.indexOf("admin.action")>=0){
			return true;
		}
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin!=null){
			return true ;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/admin/index.jsp").forward(request, response);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
}
