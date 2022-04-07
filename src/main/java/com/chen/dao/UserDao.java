package com.chen.dao;

import com.chen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void save(User user);
    //@Param:绑定mapper中xml文件对应的参数
    User login(@Param("username") String username, @Param("password")String password);

    List<User> findAll();

    void delete(String id);

    User find(String id);

    void update(User user);

    //分页

}
