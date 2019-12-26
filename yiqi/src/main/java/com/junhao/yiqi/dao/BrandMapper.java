package com.junhao.yiqi.dao;

import com.github.pagehelper.Page;
import com.junhao.yiqi.entity.ImgEntity;
import com.junhao.yiqi.entity.productEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandMapper {
    @Select("select brand from brand")
    public List<String> getAllBrand();
}