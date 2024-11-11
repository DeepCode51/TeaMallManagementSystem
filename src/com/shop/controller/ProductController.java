package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.po.Product;
import com.shop.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService prodcutService;

	@RequestMapping("/productFindByPid")
	public String productFindByPid(@RequestParam int pid,Model model) throws Exception {
			Product product = prodcutService.productFindByPid(pid);
			model.addAttribute("product", product);
		return "product";
	}
}
