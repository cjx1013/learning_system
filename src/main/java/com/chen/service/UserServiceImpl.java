package com.chen.service;

import com.chen.dao.UserDao;
import com.chen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
       user.setId(UUID.randomUUID().toString());
        userDao.save(user);

    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

//    Spring 的事务传播机制中 Propagation.SUPPORTS 级别的意义是，如果当前环境有事务，就加入到当前事务；如果没有事务，就以非事务的方式执行
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User find(String id) {
        return userDao.find(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
