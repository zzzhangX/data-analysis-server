package com.zhangwo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangwo.dao.IncomeFlowDao;
import com.zhangwo.service.IncomeFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class IncomeFlowServiceImpl implements IncomeFlowService {


    @Autowired
    IncomeFlowDao incomeFlowDao;

    @Override
    public JSONObject inComeFlowSum() throws SQLException {
        JSONObject s = incomeFlowDao.inComeFlowSum();
        return s;
    }

    @Override
    public JSONObject inComeFlowSum(String fromTime, String toTime) throws SQLException {
        JSONObject s = incomeFlowDao.inComeFlowSum(fromTime, toTime);
        return  s;
    }

    @Override
    public String inComeFlowDetail(String fromTime, String toTime) throws SQLException {
        String s = incomeFlowDao.inComeFlowDetail(fromTime, toTime);
        return s;
    }

    @Override
    public JSONObject inComeFlowToday(String datetime) throws SQLException {
        JSONObject s = incomeFlowDao.inComeFlowToday(datetime);
        return s;
    }
}
