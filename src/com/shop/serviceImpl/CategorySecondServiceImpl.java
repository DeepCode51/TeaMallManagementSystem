package com.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.Utils.PageBean;
import com.shop.mapper.CategorysecondMapper;
import com.shop.po.Categorysecond;
import com.shop.po.CategorysecondExample;
import com.shop.po.CategorysecondExample.Criteria;
import com.shop.service.CategorySecondService;


public class CategorySecondServiceImpl implements CategorySecondService {
	@Autowired
	private CategorysecondMapper categorysecondMapper;

	@Override
	public PageBean<Categorysecond> adminCategorySecond_findAllByPage(int page) {
		PageBean<Categorysecond> pageBean = new PageBean<>();
		// 设置这是第几页
		pageBean.setPage(page);
		// 设置10个
		int limitPage = 10;
		pageBean.setLimitPage(limitPage);
		// 设置一共多少页
		int totlePage = 0;
		// 查询一共有多少页
		CategorysecondExample example = new CategorysecondExample();
		totlePage = categorysecondMapper.countByExample(example);
		if (Math.ceil(totlePage % limitPage) == 0) {
			totlePage = totlePage / limitPage;
		} else {
			totlePage = totlePage / limitPage + 1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		// 集合 分页查询
		CategorysecondExample csexample = new CategorysecondExample();
		csexample.setBeginPage(beginPage);
		csexample.setLimitPage(limitPage);
		List<Categorysecond> list = categorysecondMapper
				.selectByExample(csexample);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void adminCategorySecond_save(Categorysecond categorysecond)
			throws Exception {
		categorysecondMapper.insert(categorysecond);
	}

	@Override
	public Categorysecond findByCsid(int csid) throws Exception {
		return categorysecondMapper.selectByPrimaryKey(csid);
	}

	@Override
	public void adminCategorySecond_update(Categorysecond categorysecond) {
		categorysecondMapper.updateByPrimaryKey(categorysecond);
	}

	@Override
	public void adminCategorySecond_delete(int csid) {
		categorysecondMapper.deleteByPrimaryKey(csid);
	}

	@Override
	public List<Categorysecond> findAll() throws Exception {
		return categorysecondMapper.selectByExample1();
	}

	@Override
	public void adminCategorySecond_deleteByCid(int cid) throws Exception {
		CategorysecondExample example = new CategorysecondExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		categorysecondMapper.deleteByExample(example);
	}
}
