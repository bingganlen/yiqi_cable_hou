package com.junhao.yiqi.jpa;

import com.junhao.yiqi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：恒宇少年
 * Date：2017/4/14
 * Time：23:08
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 */

@Transactional   //事物
public interface UserJPA extends JpaRepository<UserEntity,Long>
{
    //@Query  自定义SQL

    //查询大于20岁的用户
    @Query(value = "select * from t_user where t_age > ?1",nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);

    //根据用户名、密码删除一条数据
    @Modifying
    @Query(value = "delete from t_user where t_name = ?1 and t_pwd = ?2",nativeQuery = true)
    public void deleteQuery(String name, String pwd);
}


//以前是这样
//  public interface UserJPA extends
//        JpaRepository<UserEntity, Long>,
//        JpaSpecificationExecutor<UserEntity>,
//        Serializable{}