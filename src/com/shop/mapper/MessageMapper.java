package com.shop.mapper;

import java.util.List;

import com.shop.po.Message;

public interface MessageMapper {
   
    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    int insertSelective(Message record);
    
	//全部记录数
	int countAllMessage();

	List<Message> findAllMessageByPage(int page, int limitPage);

}