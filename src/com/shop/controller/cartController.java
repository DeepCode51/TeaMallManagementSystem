package com.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.po.Cart;
import com.shop.po.CartItem;
import com.shop.po.Product;
import com.shop.po.User;
import com.shop.service.ProductService;


@Controller
public class cartController {
	@Autowired
	private ProductService productService;
//	添加到购买茶叶车
	@RequestMapping("/addCart")
	public String addCart(HttpServletRequest request,@RequestParam int pid,@RequestParam int count ,Model model){
		Product product = productService.finbProductByPid(pid);
//		存进一个购买茶叶项
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		cartItem.setSubtotle(count*product.getShopPrice());
//		存进购买茶叶车
		Cart cart=getCart(request);
		cart.addCart(cartItem);
		return "cart";
	}
//	查看我的购买茶叶车
	@RequestMapping("/myCart")
	public String myCart(HttpServletRequest request,Model model){
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			model.addAttribute("message","您还没有登录！");
			return "msg";
		}
		return "cart";
	}
	
//	清空购买茶叶车
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request){
		Cart cart=getCart(request);
		cart.clearCart();
		return "redirect:myCart.action";
	}
//	删除某个购买茶叶项
	@RequestMapping("/delProFromCart")
	public String delProFromCart(@RequestParam int pid,HttpServletRequest request){
		Cart cart=getCart(request);
		cart.delProFromCart(pid);
		return "redirect:myCart.action";
	}
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
