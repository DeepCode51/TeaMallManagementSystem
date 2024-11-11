package com.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Utils.PageBean;
import com.shop.po.Product;
import com.shop.service.ProductService;

@Controller
public class CategorySecondController {
	@Autowired
	private ProductService productService;
//根据一级目录查找二级目录下面的茶叶(使用延迟加载但是这里不知为啥使用起来出错了)
	@RequestMapping("/findCategorySecond")
	public String findCategorySecond(HttpServletRequest request,@RequestParam int cid,Model model,@RequestParam int page) throws Exception {
		request.getSession().setAttribute("cid",cid);
		PageBean<Product> proPageBean = productService.findProductyBycid(cid,page);
		model.addAttribute("pageBean",proPageBean);
		return "category";
	}
	
//	根据csid来分页查询茶叶
	@RequestMapping("/findCategorySecond1")
	public String findCategorySecond1(@RequestParam int csid,Model model,@RequestParam int page){
		PageBean<Product> proPageBean = productService.finbProductByCsid(csid,page);
		model.addAttribute("pageBean",proPageBean);
		return "category";
	}
}
