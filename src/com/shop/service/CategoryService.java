package com.shop.service;

import java.util.List;

import com.shop.po.Category;


public interface CategoryService {
//	查询一级目录
	public List<Category> findCategory() throws Exception;
//	增加一级目录
	public void addCategory(Category addCategory) throws Exception;
	
	public List<Category> adminbFindCategory() throws Exception;
//	根据cid查询一级分类
	public Category findCategory(int cid) throws Exception;
//	更新一级分类
	public void adminCategory_update(Category category);
//	根据cid删除一级分类
	public void deleteCategoryByCid(int cid) throws Exception;
}
