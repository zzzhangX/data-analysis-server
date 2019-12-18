package com.zhangwo.service;

import com.zhangwo.entity.JwtUser;
import com.zhangwo.entity.User;
//import com.zhangwo.respose.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userService.findByUsername(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JwtUser(user);
    }
}
