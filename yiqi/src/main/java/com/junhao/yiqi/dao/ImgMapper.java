package com.junhao.yiqi.dao;

import com.github.pagehelper.Page;
import com.junhao.yiqi.entity.ImgEntity;
import com.junhao.yiqi.entity.productEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgMapper {


    @Select("select * from image")
    public List<ImgEntity> gettt();

    @Select("select * from product")
    public List<productEntity> gettp();

    //我熟悉的分页类型
    //@Select("select * from product ")
    public Page<productEntity> gettp2(productEntity productEntity);

    @Select("select * from product")
    public Page<productEntity> gettp3();
    //Page<Device_warning_record_his> findByPage(Device_warning_record_his record);


}
