package com.zhangwo.service.impl;

import com.zhangwo.dao.UserDao;
import com.zhangwo.entity.User;
import com.zhangwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean registerUser(Map<String,String> registerUser) throws SQLException {
        System.out.println("service");
        Boolean aBoolean = null;
        aBoolean = userDao.registerUser(registerUser);
        return  aBoolean;
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        User user = userDao.findByUsername(username);
        return user;
    }
}
