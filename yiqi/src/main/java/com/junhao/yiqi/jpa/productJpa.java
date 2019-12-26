package com.junhao.yiqi.jpa;

import com.junhao.yiqi.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional   //事物
public interface productJpa extends JpaRepository<productEntity,Integer> {

    //没有nativeQuery = true时，就不是原生sql，而其中的select * from 的xxx中xxx也不是数据库对应的真正的表名，
    // 而是对应的实体名，并且sql中的字段名也不是数据库中真正的字段名，而是实体的字段名
    @Query(value ="DELETE FROM product WHERE brandName = ?1",nativeQuery = true)
    int delByBrand(String brandName);
}
