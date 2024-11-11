package com.shop.service;

import com.shop.Utils.PageBean;
import com.shop.po.Message;

public interface MessageService {
	public void insertMessage(Message messages) throws Exception;
	
	public void deleteMessage(int messageid) throws Exception;
//	所有留言
	public PageBean<Message> findAllMessageByPage(int page) throws Exception;
	 
}
