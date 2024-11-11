package com.shop.controller;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Utils.PageBean;
import com.shop.po.Cart;
import com.shop.po.CartItem;
import com.shop.po.Orderitem;
import com.shop.po.Orders;
import com.shop.po.User;
import com.shop.service.OrderService;


@Controller
public class orderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/toOrder")
	public String toOrder(HttpServletRequest request,Model model) throws Exception {
		Orders orders = new Orders();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			model.addAttribute("message", "对不起您还没有登录");
			return "msg";
		}
		//		0表示没有付款、1表示已付款即将发货 2表示确认收货 3表示交易成功
		orders.setState(0);
		orders.setOrderTime(new Date());
		orders.setUid(loginUser.getUid());
		orders.setMoney(cart.getTotale());

		orderService.toOrder(orders);

		Map<Integer, CartItem> cartItems = cart.getCartItem();
		for (Entry<Integer, CartItem> entry : cartItems.entrySet()) {
			CartItem cartItem = entry.getValue();
			Orderitem orderitem = new Orderitem();
			orderitem.setProduct(cartItem.getProduct());
			orderitem.setCount(cartItem.getCount());
			orderitem.setPid(cartItem.getProduct().getPid());
			orderitem.setSubtotal(cartItem.getSubtotle());
			orderitem.setOid(orders.getOid());
			orders.getOiList().add(orderitem);
			orderService.toOrderItem(orderitem);
		}
		cart.clearCart();
		request.getSession().setAttribute("orders", orders);
		return "order";
	}

	// 为定单付款
	@RequestMapping("/payOrder")
	public String payOrder(Orders orders,@RequestParam String receiveInfo,@RequestParam String phoNum,@RequestParam String accepter) throws Exception {
		orders.setReceiveinfo(receiveInfo);
		orders.setPhonum(phoNum);
		orders.setAccepter(accepter);
		orderService.payOrder(orders);
		return "redirect:myOrder.action?page=1";
	}
	//payOrderAganin
	@RequestMapping("/payOrderAganin")
	public String payOrderAganin(@RequestParam int oid,HttpServletRequest request){
		Orders noPayOrder = orderService.findOrderByOid(oid);
		request.getSession().setAttribute("orders", noPayOrder);
		return "order";
	}
	// 查询myOrder
	@RequestMapping("/myOrder")
	public String myOrder(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		PageBean<Orders> pageBean = orderService.findOrderByUidAndPage(page,loginUser.getUid());
		model.addAttribute("pageBean", pageBean);
		return "orderList";
	}

	// 确认收货
	@RequestMapping("/updateState")
	public String updateState(@RequestParam int oid ) throws Exception {
		orderService.updateOrderStatus(oid, 3);
		return "redirect:myOrder.action?page=1";
	}

	// 确认收货
	@RequestMapping("/backState")
	public String backState(@RequestParam int oid ) throws Exception {
		orderService.updateOrderStatus(oid, 4);
		return "redirect:myOrder.action?page=1";
	}
}
