package com.junhao.yiqi.dao;

import com.junhao.yiqi.entity.otherImgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface otherImgMapper {
    Integer insert(otherImgEntity otherImgEntity);

    List<String> selectProductFuImg(@Param("brandName") String brandName,@Param("productName") String productName);
}
