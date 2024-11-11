package com.shop.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.Utils.PageBean;
import com.shop.mapper.ProductMapper;
import com.shop.po.Category;
import com.shop.po.CategoryExample;
import com.shop.po.CategorysecondExample;
import com.shop.po.Product;
import com.shop.po.ProductExample;
import com.shop.po.ProductExample.Criteria;
import com.shop.service.ProductService;


public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
//	查询热门茶叶 带分页的查询
	public List<Product> findHotProduct() throws Exception {
		 ProductExample example = new ProductExample();
		 ProductExample.Criteria criteria = example.createCriteria();
		 criteria.andIsHotEqualTo(1);
		 example.setOrderByClause("pdate DESC");
		 example.setBeginPage(0);
		 example.setEnd(4);		 
		 List<Product> list = productMapper.selectByExample(example);
		 /*for (Product product : list) {
			System.out.println(product.getPname());
		}*/
		 if(list!=null && list.size()>0){
			 return list;
		 }
		 return null;
	}
	@Override
	public List<Product> findNewProduct() throws Exception {
		 ProductExample example = new ProductExample();
		 ProductExample.Criteria criteria = example.createCriteria();
		 example.setOrderByClause("pdate DESC");
		 example.setBeginPage(0);
		 example.setEnd(8);		 
		 List<Product> list = productMapper.selectByExample(example);
		 /*for (Product product : list) {
			System.out.println(product.getPname());
		}*/
		 if(list!=null && list.size()>0){
			 return list;
		 }
		 return null;
	}
//	根据id查找茶叶
	public Product productFindByPid(int pid) throws Exception {
		return productMapper.selectByPrimaryKey(pid);
	}
//	根据cid查找茶叶
	public PageBean<Product> findProductyBycid(int cid, int page)
			throws Exception {
		PageBean<Product> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置10个
		int limitPage =10;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = productMapper.countProducyByCid(cid);
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
//		茶叶集合
		List<Product> list = productMapper.findProductByCid(cid,beginPage,limitPage);
		pageBean.setList(list);
		
		return pageBean;
	}
//	根据csid查找茶叶
	public PageBean<Product> finbProductByCsid(int csid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);
//		设置10个
		int limitPage =10;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = productMapper.countProducyByCsid(csid);
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
//		茶叶集合
		List<Product> list = productMapper.findProductBycsid(csid,beginPage,limitPage);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public Product finbProductByPid(int pid) {
		return productMapper.selectByPrimaryKey(pid);
	}
	@Override
	public PageBean<Product> findAllProduct(int page) throws Exception {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);
//		设置10个
		int limitPage =10;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		ProductExample example = new ProductExample();
		totlePage = productMapper.countByExample(example);
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
//		茶叶集合
		List<Product> list = productMapper.findAllProduct(beginPage,limitPage);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void adminProduct_save(Product product) throws Exception {
		productMapper.insert(product);
	}
	@Override
	public void adminProduct_deletecs(int pid) throws Exception {
		productMapper.deleteByPrimaryKey(pid);
	}
	@Override
	public void adminProduct_update(Product product) throws Exception {
		productMapper.updateByPrimaryKey(product);
	}
	@Override
	public List<Product> searchProduct(String condition) throws Exception {
		 	 
		 List<Product> list = productMapper.searchProduct(condition) ;
		  
		 if(list!=null && list.size()>0){
			 return list;
		 }
		 return null;
	}
}
