package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.po.User;
import com.shop.service.UserService;


@Controller
public class ajaxController {
	@Autowired
	private UserService userService;

	@RequestMapping("/loginFindByid")
	public void loginFindByid(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			User user = userService.loginFindByid(username);
			if (user!=null) {
				response.getWriter().write("可以登录");
			}else{
				response.getWriter().write("不可以登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/registFindByid")
	public void registFindByid(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			User user = userService.loginFindByid(username);
			if (user!=null) {
				response.getWriter().write("已经被注册");
			}else{
				response.getWriter().write("可以注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
