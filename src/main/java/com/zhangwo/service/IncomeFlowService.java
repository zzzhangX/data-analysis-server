package com.zhangwo.service;

import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;

public interface IncomeFlowService {


    /**
     * 查看一段时间内的总流水
     */
    JSONObject inComeFlowSum() throws SQLException;

    /**
     * 查看一段时间内的总流水
     *
     * @param fromTime
     * @param toTime
     */
    JSONObject inComeFlowSum(String fromTime, String toTime) throws SQLException;

    /**
     * 查看一段时间内每天的流水
     *
     * @param fromTime
     * @param toTime
     */
    String inComeFlowDetail(String fromTime, String toTime) throws SQLException;

    /**
     * 查看某天的流水总收入
     *
     * @param datetime
     * @return
     */
    JSONObject inComeFlowToday(String datetime) throws SQLException;
}
