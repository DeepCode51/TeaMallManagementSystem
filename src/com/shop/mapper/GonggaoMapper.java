package com.shop.mapper;

import com.shop.po.Gonggao;
import com.shop.po.GonggaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GonggaoMapper {
    long countByExample(GonggaoExample example);

    int deleteByExample(GonggaoExample example);

    int insert(Gonggao record);

    int insertSelective(Gonggao record);

    List<Gonggao> selectByExample(GonggaoExample example);

    int updateByExampleSelective(@Param("record") Gonggao record, @Param("example") GonggaoExample example);

    int updateByExample(@Param("record") Gonggao record, @Param("example") GonggaoExample example);
}