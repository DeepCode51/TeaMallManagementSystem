package com.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shop.po.Product;
import com.shop.po.ProductExample;

public interface ProductMapper {
	int countProducyByCid(int cid);
	
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
	List<Product> findProductByCid(int cid,int beginPage,int end);

	int countProducyByCsid(int csid);

	List<Product> findProductBycsid(int csid, int page, int limitPage);

	List<Product> findAllProduct(int page, int limitPage);
	
	List<Product> searchProduct(String condition);
}