package com.shop.service;

import java.util.List;

import com.shop.po.Adminuser;
import com.shop.po.User;


public interface UserService {
	
	public User loginFindByid(String username) throws Exception;

	public void saveUser(User user) throws Exception;
	
	public User findByCode(String code)throws Exception;
	
	public void activeUser(User activeUser)throws Exception;

	public User loginFindByUnameAndPwd(String username, String password)throws Exception;
	
	public Adminuser adminUser_login(String username, String password) throws Exception;
	
	public List<User> admin_findAll() throws Exception;
}
