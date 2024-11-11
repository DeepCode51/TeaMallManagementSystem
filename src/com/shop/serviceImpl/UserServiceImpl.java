package com.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.Utils.MailUtils;
import com.shop.Utils.UUIDUtiils;
import com.shop.mapper.AdminuserMapper;
import com.shop.mapper.UserMapper;
import com.shop.po.Adminuser;
import com.shop.po.AdminuserExample;
import com.shop.po.User;
import com.shop.po.UserExample;
import com.shop.po.AdminuserExample.Criteria;
import com.shop.service.UserService;

//@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdminuserMapper adminuserMapper;
	@Override
	public User loginFindByid(String username) throws Exception {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveUser(User user) throws Exception {
		user.setState(0);
		user.setCode(UUIDUtiils.getUUID());
		user.setState(1);
		user.setCode(null);
		int i = userMapper.insert(user);
//		System.out.println(i);
//		String emailMsg = "请激活<a href='/ssm_chaye_shop/activeUser.action?code="
//				+ user.getCode() + "'>激活</a>激活码是： + user.getCode()";
//		try {
//			MailUtils.sendMail(user.getEmail(), emailMsg);
//		} catch (Exception e) {
//		} 
	}

	@Override
	public User findByCode(String code) throws Exception {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void activeUser(User activeUser) throws Exception {
		userMapper.updateByPrimaryKeySelective(activeUser);
	}

	@Override
	public User loginFindByUnameAndPwd(String username, String password)
			throws Exception {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Adminuser adminUser_login(String username, String password)
			throws Exception {
		AdminuserExample example = new AdminuserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		createCriteria.andPasswordEqualTo(password);
		List<Adminuser> list = adminuserMapper.selectByExample(example);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> admin_findAll() throws Exception {
		UserExample example = new UserExample();
		return userMapper.selectByExample(example);
	}
}
