package com.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.exception.zdyException;
import com.shop.po.User;
import com.shop.service.UserService;


@Controller
public class registController {
	@Autowired
	private UserService userService;

	@RequestMapping("regist")
	public String regist() {
		return "regist";
	}

	@RequestMapping("/userRegist")
	public String userRegist(Model model, HttpServletRequest request,
			@Validated User user, BindingResult bindingResult,
			@RequestParam String checkImg) throws Exception {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors =bindingResult.getAllErrors();
			List<String> list = new ArrayList<>();
			for (ObjectError objectError : errors) {
				String str = new String(objectError.getDefaultMessage()
						);
				list.add(str);
			}
			model.addAttribute("errors", list);
			return "regist";
		}
		// 查看验证码
		String sessionCode = (String) request.getSession().getAttribute(
				"checkcode");
//		System.out.println("adadadad" + sessionCode);
		if (!sessionCode.equalsIgnoreCase(checkImg)) {
//			model.addAttribute("message", "验证码错误请重新注册");
			throw new zdyException("验证码错误请重新注册");
		}
		// 开始写入数据库
		userService.saveUser(user);
		//model.addAttribute("message", "注册成功请去邮箱激活");
		model.addAttribute("message", "注册成功请登录");
		return "msg";
	}

	@RequestMapping("/activeUser")
	public String activeUser(@RequestParam String code,Model model) throws zdyException,Exception  {
		/*
		 * 根据传递激活码进行用户查询. 如果用户不为空: 修改用户状态 改为1 如果用户为空: 激活码被篡改了.
		 */
			User activeUser = userService.findByCode(code);
			if(activeUser==null){
//				model.addAttribute("message", "激活码被篡改了,请重新注册");
				throw new zdyException("激活码被篡改了,请重新注册");
			}
			activeUser.setState(1);
			activeUser.setCode(null);
			userService.activeUser(activeUser);
			model.addAttribute("message", "激活码成功");
		return "msg";
	}
}
