package com.zhangwo.controller;

import com.zhangwo.service.UserService;
import com.zhangwo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("mysqlDruidDataSource")
    DataSource mysqlDruidDataSource;

    @PostMapping("/register")
    public boolean registerUser(@RequestBody Map<String,String> registerUser) throws SQLException {
        System.out.println("controller");
        boolean b = userService.registerUser(registerUser);
        return b;
    }
}
