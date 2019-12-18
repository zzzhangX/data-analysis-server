package com.zhangwo.service;

import com.zhangwo.entity.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {
    /**
     * 注册
     */
    boolean registerUser(Map<String,String> registerUser) throws SQLException;

    User findByUsername(String username) throws SQLException;
}
