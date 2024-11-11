package com.shop.service;

import com.shop.Utils.PageBean;
import com.shop.po.Orderitem;
import com.shop.po.Orders;

public interface OrderService {
//toOrder
	public void toOrder(Orders orders) throws Exception;
//toOrderItem
	public void toOrderItem(Orderitem orderitem) throws Exception;
//	payOrder
	public void payOrder(Orders orders)throws Exception;
//	myOrder
	public PageBean<Orders> findOrderByUidAndPage(int page, Integer uid) throws Exception;
//	payOrderAganin
	public Orders findOrderByOid(int oid);
//	后台：所有订单
	public PageBean<Orders> findAllOrderByStateAndPage(int page) throws Exception;
	//更新订单状态
	public void updateOrderStatus(int oid, int status) throws Exception;
	//根据订单查看订单
	public PageBean<Orders> findAllOrderByStateAndPage(int state,int page) throws Exception;
}
