package com.zhangwo.service;

import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;

public interface PlayerService {

    /**
     * 今日注册
     */
    JSONObject dayRegister(String datetime) throws SQLException;

    /**
     * 总注册量
     */
    JSONObject countPlayer() throws SQLException;

    /**
     * 查看一段时间内的注册人数
     *
     * @param fromTime
     * @param toTime
     */
    String everyDayPlayerRegister(String fromTime,String toTime) throws SQLException;

    /**
     * 每日登陆人数
     *
     * @param fromTime
     * @param toTime
     */
    String everyDayPlayerLogin(String fromTime,String toTime) throws SQLException;

    /**
     * 今日登录人次
     *
     * @param datetime
     */
    JSONObject dayLoginPersonTime(String datetime) throws SQLException;


    /**
     * 当前在线人数
     */
    JSONObject onLinePlayer(String datetime) throws SQLException;
}
