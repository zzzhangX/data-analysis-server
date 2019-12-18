package com.zhangwo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class GetResult {

    public JSONObject getObj(ResultSet res, Connection connection, Statement statement)throws SQLException {
        ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();
        JSONObject jsonObj = new JSONObject();
        while (res.next()) {
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = res.getString(columnName);
                jsonObj.put(columnName, value);
            }
        }
        res.close();
        statement.close();
        connection.close();
        return jsonObj;
    }

    public String getJson(ResultSet res, Connection connection, Statement statement) throws SQLException {
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (res.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = res.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        res.close();
        statement.close();
        connection.close();
        return array.toString();
    }

}
