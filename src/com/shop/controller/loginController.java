package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.exception.zdyException;
import com.shop.po.Adminuser;
import com.shop.po.User;
import com.shop.service.UserService;

@Controller
public class loginController {

	@Autowired
	private UserService userService;

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	// 用户登录
	@RequestMapping("/userLogin")
	public String userLogin(HttpServletRequest request,
			@RequestParam String checkImg, User user, Model model)
			throws zdyException, Exception {
		String sessionCode = (String) request.getSession().getAttribute(
				"checkcode");
		if (!sessionCode.equalsIgnoreCase(checkImg)) {
			 //model.addAttribute("message", "验证码输入错误请重新注册");
			//throw new zdyException("验证码输入错误请重新注册");
			request.getSession().setAttribute("message", "验证码输入错误请重新注册");
			 return "redirect:login.action";
		}
		User loginUser = userService.loginFindByUnameAndPwd(user.getUsername(),
				user.getPassword());
		if (loginUser == null) {
			 //model.addAttribute("message", "密码输入错误请重新登陆");
			 request.getSession().setAttribute("message", "密码输入错误请重新登陆");
			//throw new zdyException("密码输入错误请重新登陆");
			 return "redirect:login.action";
		}
		request.getSession().setAttribute("loginUser", loginUser);
		request.getSession().removeAttribute("message");
		return "redirect:index.action";
	}

	// 用户退出
	@RequestMapping("userLogout")
	public String userLogout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		request.getSession().removeAttribute("loginUser");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setHeader("refresh", "5;url=/shop/index.action");
		model.addAttribute("message", "退出成功，系统将在5秒之后跳转到首页......");
		return "msg";
	}

	//进入管理员页面
	@RequestMapping("/admin")
	public String AdminIndex(){
		return "admin/index";
	}
	//admin/adminUser_login.action
    //adminUser_login管理员登录
	@RequestMapping("/admin/adminUser_login")
	public String adminUser_login(HttpServletRequest request,Model model,@RequestParam String username,@RequestParam String password) throws Exception{
		Adminuser adminuserLogin = userService.adminUser_login(username,password);
		System.out.println(username);
		System.out.println(password);
		if(adminuserLogin == null){
			model.addAttribute("message", "改用户未被授予管理员身份！");
			return "admin/index";
		}
		request.getSession().setAttribute("adminuserLogin", adminuserLogin);
//		model.addAttribute("adminuserLogin", adminuserLogin);
		return "admin/home";
	}
	
}
