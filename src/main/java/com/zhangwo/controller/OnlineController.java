package com.zhangwo.controller;

import com.zhangwo.utils.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/impala")
public class OnlineController {

    @Autowired
    @Qualifier("impalaDruidDataSource")
    DataSource impalaDruidDataSource;

    @PostMapping("/show")
    public String show() throws SQLException {
        Connection connection = impalaDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "show tables;";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        String json = getResult.getJson(resultSet,connection,statement);
        return json;
    }
}
