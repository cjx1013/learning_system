package com.chen.service;

import com.chen.entity.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User login(String username,String password);

    List<User> findAll();

    void delete(String id);

    User find(String id);

    void update(User user);
}
