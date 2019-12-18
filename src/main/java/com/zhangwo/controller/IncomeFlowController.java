package com.zhangwo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangwo.service.impl.IncomeFlowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/income")
public class IncomeFlowController {
    @Autowired
    private IncomeFlowServiceImpl incomeFlowService;

    @RequestMapping("/allSum")
    public JSONObject inComeFlowSum() throws SQLException {
        JSONObject s = incomeFlowService.inComeFlowSum();
        return s;
    }

    @RequestMapping("/sum")
    public JSONObject inComeFlowSum(@RequestBody JSONObject jsonObject) throws SQLException {
        String startTime = jsonObject.get("startTime").toString();
        String toTime = jsonObject.get("toTime").toString();
        JSONObject s = incomeFlowService.inComeFlowSum(startTime,toTime);
        return s;
    }

    @RequestMapping("/detail")
    public String inComeFlowDetail(@RequestBody JSONObject jsonObject) throws SQLException {
        String startTime = jsonObject.get("startTime").toString();
        String toTime = jsonObject.get("toTime").toString();
        String s = incomeFlowService.inComeFlowDetail(startTime,toTime);
        return s;
    }

    @RequestMapping("/today")
    public JSONObject inComeFlowToday(@RequestBody JSONObject jsonObject) throws SQLException {
        String datetime = jsonObject.get("datetime").toString();
        JSONObject s = incomeFlowService.inComeFlowToday(datetime);
        return s;
    }
}
