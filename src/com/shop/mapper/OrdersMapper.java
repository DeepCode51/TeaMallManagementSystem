package com.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shop.po.Orders;
import com.shop.po.OrdersExample;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	int countOrdersByUid(Integer uid);
	//根据订单状态，查询符合条件的记录条数
	int countOrdersByState(Integer state);
	//全部订单记录数
	int countAllOrders();

	List<Orders> findOrderByUidAndPage(Integer uid, int page, int limitPage);
	
	List<Orders> findAllOrderByPage(int page, int limitPage);
	
	List<Orders> findAllOrderByStateAndPage(int state,int page, int limitPage);

}