package com.zhangwo.dao;

import com.alibaba.fastjson.JSONObject;
import com.zhangwo.utils.GetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class IncomeFlowDao {

    private static final Logger logger = LoggerFactory.getLogger(IncomeFlowDao.class);

    @Autowired
    @Qualifier("mysqlDruidDataSource")
    DataSource mysqlDruidDataSource;

    @Autowired
    @Qualifier("impalaDruidDataSource")
    DataSource impalaDruidDataSource;

    /**
     * 查看总收入
     */
    public JSONObject inComeFlowSum() throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT SUM(sumIncome) as sumIncome \n" +
                "FROM t_incomefolw";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject json = getResult.getObj(resultSet,connection,statement);
        return json;
    }

    /**
     * 查看一段时间内的流水总收入
     *
     * @param startTime
     * @param toTime
     */
    public JSONObject inComeFlowSum(String startTime, String toTime) throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT SUM(sumIncome) as sumIncome \n" +
                "FROM t_incomefolw\n" +
                "WHERE `datetime` BETWEEN '"+startTime+"' AND '"+toTime+"'\n";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject json = getResult.getObj(resultSet,connection,statement);
        return json;
    }

    /**
     * 查看一段时间内的流水总收入
     *
     * @param startTime
     * @param toTime
     */
    public String inComeFlowDetail(String startTime,String toTime) throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT  `datetime` as x,sumIncome as y \n" +
                "FROM t_incomefolw\n" +
                "WHERE `datetime` BETWEEN '"+startTime+"' AND '"+toTime+"'\n";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        String json = getResult.getJson(resultSet,connection,statement);
        return json;
    }


    /**
     * 查看某天的流水总收入!!!!!!!!!!!!!!!!!sql为测试
     *
     * @param datetime
     */
    public JSONObject inComeFlowToday(String datetime) throws SQLException {
        Connection connection = impalaDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String beforeSql = "REFRESH playerregister";
        logger.info("Running:" + beforeSql);
        statement.execute(beforeSql);

        String sql = "SELECT count(1) todayIncome from playerregister WHERE `datetime`='"+datetime+"'";

        logger.info("Running:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);

        GetResult getResult = new GetResult();
        JSONObject json = getResult.getObj(resultSet,connection,statement);
        return json;
    }
}
