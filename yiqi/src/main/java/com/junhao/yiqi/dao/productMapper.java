package com.junhao.yiqi.dao;

import com.github.pagehelper.Page;
import com.junhao.yiqi.entity.productEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface productMapper {

    productEntity selectByPrimaryKey(Integer id);

    public Page<productEntity> gettp(productEntity productEntity);

    int updateByPrimaryKey(productEntity productEntity);

    //根据品牌名查找符合条件的所有商品名
    List<String> allproductname(String brand);

    int checkbrand(String brand);
}
