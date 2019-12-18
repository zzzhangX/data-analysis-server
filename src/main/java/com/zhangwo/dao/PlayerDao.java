package com.zhangwo.dao;

import com.alibaba.fastjson.JSONObject;
import com.zhangwo.utils.GetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PlayerDao {
    private static final Logger logger = LoggerFactory.getLogger(IncomeFlowDao.class);

    @Autowired
    @Qualifier("mysqlDruidDataSource")
    DataSource mysqlDruidDataSource;

    @Autowired
    @Qualifier("impalaDruidDataSource")
    DataSource impalaDruidDataSource;

    /**
     * 今日注册
     */
    public JSONObject dayRegister(String datetime) throws SQLException {
        Connection connection = impalaDruidDataSource.getConnection();
        Statement statement = connection.createStatement();

        String beforeSql = "REFRESH playerregister";
        logger.info("Running:" + beforeSql);
        statement.execute(beforeSql);

        String sql = "select count(t1.vopenid) newUsers from \n" +
                "(SELECT DISTINCT(vopenid) from playerregister WHERE `datetime`='" + datetime + "') t1 left join \n" +
                "t_register t2\n" +
                "on t1.vopenid=t2.vopenid\n" +
                "where t2.vopenid is null";
        logger.info("Running:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject obj = getResult.getObj(resultSet, connection, statement);
        return obj;
    }

    /**
     * 总注册量
     */
    public JSONObject countPlayer() throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT COUNT(vopenid) sumUsers FROM t_register";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject obj = getResult.getObj(resultSet, connection, statement);
        return obj;
    }

    /**
     * 每日注册量
     *
     * @param fromTime
     * @param toTime
     */
    public String everyDayPlayerRegister(String fromTime, String toTime) throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT `datetime` as x,COUNT(1) as y \n" +
                "FROM t_register \n" +
                "WHERE `datetime` BETWEEN '" + fromTime + "' AND '" + toTime + "'\n" +
                "GROUP BY `datetime` ";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        String json = getResult.getJson(resultSet, connection, statement);
        return json;
    }

    /**
     * 每日登陆人数
     *
     * @param fromTime
     * @param toTime
     */
    public String everyDayPlayerLogin(String fromTime, String toTime) throws SQLException {
        Connection connection = mysqlDruidDataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT `datetime` as x,countlogin as y \n" +
                "FROM t_logincount\n" +
                "WHERE `datetime` BETWEEN '" + fromTime + "' AND '" + toTime + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        String json = getResult.getJson(resultSet, connection, statement);
        return json;
    }

    /**
     * 今日登录人次
     */
    public JSONObject dayPersonTime(String datetime) throws SQLException {
        Connection connection = impalaDruidDataSource.getConnection();
        Statement statement = connection.createStatement();

        String beforeSql = "REFRESH playerlogin";
        logger.info("Running:" + beforeSql);
        statement.execute(beforeSql);

        String sql = "SELECT count(1) dayLoginTimes from playerlogin WHERE `datetime`='"+datetime+"'";
        logger.info("Running:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject obj = getResult.getObj(resultSet, connection, statement);
        return obj;
    }

    /**
     * 当前在线人数
     */
    public JSONObject onLinePlayer(String datetime) throws SQLException {
        Connection connection = impalaDruidDataSource.getConnection();
        Statement statement = connection.createStatement();

        String beforeSql = "REFRESH playerlogin";
        String beforeSql1 = "REFRESH playerlogout";
        logger.info("Running:" + beforeSql);
        statement.execute(beforeSql);
        statement.execute(beforeSql1);

        String sql = "SELECT t1.c1 - t2.c2 as onlineplayer from \n" +
                "(SELECT count(*) c1 FROM playerlogin WHERE `datetime`= '2019-12-18') t1,\n" +
                "(SELECT count(*) c2 FROM playerlogout WHERE `datetime`= '2019-12-18') t2";
        logger.info("Running:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        GetResult getResult = new GetResult();
        JSONObject obj = getResult.getObj(resultSet, connection, statement);
        return obj;
    }
}
