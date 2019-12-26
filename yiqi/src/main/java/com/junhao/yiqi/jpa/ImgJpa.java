package com.junhao.yiqi.jpa;

import com.junhao.yiqi.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional   //事物
public interface ImgJpa extends JpaRepository<ImgEntity,Integer> {

    @Query(value = "insert into User value(?,?,?)", nativeQuery = true)
    @Modifying
    public int countUserBy(@Param("id")Integer id, @Param("name") String name, @Param("password") String password);
}
