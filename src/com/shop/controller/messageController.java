package com.shop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Utils.PageBean;
import com.shop.mapper.GonggaoMapper;
import com.shop.mapper.UserMapper;
import com.shop.po.Gonggao;
import com.shop.po.GonggaoExample;
import com.shop.po.GonggaoExample.Criteria;
import com.shop.po.Message;
import com.shop.po.User;
import com.shop.service.MessageService;


@Controller
public class messageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private GonggaoMapper gonggaoMapper;
	@Autowired
	private UserMapper userMapper;



	@RequestMapping("/saveMessage")
	public String saveMessage(@RequestParam String messageinfo,HttpServletRequest request,Model model) throws Exception {
		Message Message = new Message();

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			model.addAttribute("message", "对不起您还没有登录");
			return "msg";
		}

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Message.setMessage(messageinfo);
		Message.setUid(loginUser.getUid());
		Message.setMessagedate(sdf.format(new Date()));

		messageService.insertMessage(Message);

		request.getSession().setAttribute("Message", Message);
		return "redirect:/messageList.action?page=1";
	}


	// 显示留言板全部留言
	@RequestMapping("/messageList")
	public String messageList(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {
		PageBean<Message> messages = messageService.findAllMessageByPage(page);
		model.addAttribute("messages", messages);
		
		return "messageList";
	} 

	@RequestMapping("/gonggaoList")
	public String gonggaoList(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {

		PageBean<Gonggao> gonggaos = new PageBean<>();
		//		设置这是第几页
		gonggaos.setPage(page);
		//		设置4个
		int limitPage =4;
		gonggaos.setLimitPage(limitPage);
		//		设置一共多少页
		int totlePage = 0;
		//		查询一共有多少页
		;
		totlePage = (int) gonggaoMapper.countByExample(new GonggaoExample());
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		gonggaos.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		GonggaoExample example = new GonggaoExample();
		example.setLimit(limitPage);
		if(beginPage<0)beginPage=0;
		example.setOffset(beginPage);
		//茶叶集合
		List<Gonggao> list = gonggaoMapper.selectByExample(example) ;
		gonggaos.setList(list);
		model.addAttribute("gonggaos", gonggaos);
		return "gonggaoList";
	} 
 

	@RequestMapping("/changepwd")
	public String changpwd(Model model,
			HttpServletRequest request) throws Exception {
		return "changePwd";
	} 
	
	@RequestMapping("/changeedpwd")
	public String changeedpwd(@RequestParam String password,@RequestParam String opassword,@RequestParam String repassword,Model model,
			HttpServletRequest request) throws Exception {
		if(repassword == null||repassword == null||opassword==null) {
			model.addAttribute("msg", "<script>alert('请填写完整！');</script>");
			return "changePwd";
		}
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			model.addAttribute("msg", "<script>alert('对不起您还没有登录！');</script>");
			return "changePwd";
 		}
		if(!loginUser.getPassword().equals(opassword)) {
			model.addAttribute("msg", "<script>alert('旧密码不对！');</script>");
			return "changePwd";
		}
		if(password.equals(repassword)) {
			loginUser.setPassword(password);
			userMapper.updateByPrimaryKeySelective(loginUser);
			request.getSession().setAttribute("loginUser", loginUser);
			model.addAttribute("msg", "<script>alert('修改成功！');</script>");
			return "changePwd";
		}else {
			model.addAttribute("msg", "<script>alert('2次密码不一致！');</script>");
			return "changePwd";
		}
 	} 

}
