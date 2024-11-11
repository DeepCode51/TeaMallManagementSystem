package com.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.Utils.PageBean;
import com.shop.mapper.OrderitemMapper;
import com.shop.mapper.OrdersMapper;
import com.shop.po.Orderitem;
import com.shop.po.Orders;
import com.shop.service.OrderService;


public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderitemMapper orderitemMapper;
	public void toOrder(Orders orders) throws Exception {
		ordersMapper.insert(orders);
	}

	@Override
	public void toOrderItem(Orderitem orderitem) throws Exception {
		orderitemMapper.insert(orderitem);
	}

	@Override
	public void payOrder(Orders orders) throws Exception {
		Orders payOrder = ordersMapper.selectByPrimaryKey(orders.getOid());
		if(orders.getReceiveinfo()!=null && orders.getPhonum()!=null){
			payOrder.setPhonum(orders.getPhonum());
			payOrder.setReceiveinfo(orders.getReceiveinfo());
			payOrder.setAccepter(orders.getAccepter());
			payOrder.setState(1);
		}
		ordersMapper.updateByPrimaryKeySelective(payOrder);
	}

	@Override
	public PageBean<Orders> findOrderByUidAndPage(int page, Integer uid)
			throws Exception {
		PageBean<Orders> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置10个
		int limitPage =4;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = ordersMapper.countOrdersByUid(uid);
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
//		茶叶集合
		List<Orders> list = ordersMapper.findOrderByUidAndPage(uid,beginPage,limitPage);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Orders findOrderByOid(int oid) {
		return ordersMapper.selectByPrimaryKey(oid);
	}

	@Override
	public PageBean<Orders> findAllOrderByStateAndPage(int page)
			throws Exception {
		PageBean<Orders> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置10个
		int limitPage = 4;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = ordersMapper.countAllOrders();
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		//茶叶集合
		List<Orders> list = ordersMapper.findAllOrderByPage(beginPage,limitPage);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void updateOrderStatus(int oid, int status) throws Exception {
		Orders payOrder = ordersMapper.selectByPrimaryKey(oid);
		payOrder.setState(status); 
		ordersMapper.updateByPrimaryKeySelective(payOrder);
	}
	
	@Override
	public PageBean<Orders> findAllOrderByStateAndPage(int state,int page)
			throws Exception {
		PageBean<Orders> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置10个
		int limitPage =4;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = ordersMapper.countOrdersByState(state);
		
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		//茶叶集合
		List<Orders> list = ordersMapper.findAllOrderByStateAndPage(state, beginPage, limitPage) ;
		pageBean.setList(list);
		return pageBean;
	}
}
