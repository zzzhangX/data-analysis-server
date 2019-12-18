package com.zhangwo.dao;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangwo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    @Qualifier("mysqlDruidDataSource")
    DataSource mysqlDruidDataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Boolean registerUser(Map<String,String> registerUser) throws SQLException {
        String username = registerUser.get("username");
        String password = bCryptPasswordEncoder.encode(registerUser.get("password"));
//        String password = registerUser.get("password");
        String role = registerUser.get("role");
//        Statement statement = mysqlDruidDataSource.getConnection().createStatement();
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into t_user values(null,'"+username+"','"+password+"','"+role+"')";
        int i = statement.executeUpdate(sql);
        statement.close();
        connection.close();
        return true;
    }

    public User findByUsername(String username) throws SQLException {
//        Statement statement = mysqlDruidDataSource.getConnection().createStatement();
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM t_user WHERE username = '"+username+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return user;
    }
}
