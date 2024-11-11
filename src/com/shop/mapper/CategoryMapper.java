package com.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shop.po.Category;
import com.shop.po.CategoryExample;

public interface CategoryMapper {
	int countByExample(CategoryExample example);

	int deleteByExample(CategoryExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(Category record);

	int insertSelective(Category record);

	List<Category> findCategoryAndSecondcategory();

	List<Category> selectByExample(CategoryExample example);

	Category selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") Category record,
			@Param("example") CategoryExample example);

	int updateByExample(@Param("record") Category record,
			@Param("example") CategoryExample example);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

}