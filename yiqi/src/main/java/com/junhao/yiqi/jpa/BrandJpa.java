package com.junhao.yiqi.jpa;

import com.junhao.yiqi.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional   //事物
public interface BrandJpa extends JpaRepository<BrandEntity,Integer> {

    //
    @Query(value = "select brand from brand where id = ?1",nativeQuery = true)
    public String nativeQuerybrand(Integer id);

    @Query(value = "select brand from brand",nativeQuery = true)
    public List nativeQueryfindAllbrandName();

//    @Query(value = "select brand from brand where id = ?1",nativeQuery = true)
//    public String QuerybrandName(Integer id);

//    @Modifying //@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作
//    @Query("update brand m set m.brand=?2 where  m.id=?1")
//    public void updateStateByMoney(Integer id,String brand);


    /*
     * 报错了
     * */
   /* @Modifying
    @Transactional
    @Query("delete from brand u where u.id=:id")
    public int deleteBrandById(@Param("id") Integer id);*/

    //@Query  自定义SQL
/*
    //查询大于20岁的用户
    @Query(value = "select * from t_user where t_age > ?1",nativeQuery = true)
    public List<BrandEntity> nativeQuery(int age);

    //根据用户名、密码删除一条数据
    @Modifying
    @Query(value = "delete from t_user where t_name = ?1 and t_pwd = ?2",nativeQuery = true)
    public void deleteQuery(String name, String pwd);*/
}
