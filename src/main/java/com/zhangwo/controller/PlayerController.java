package com.zhangwo.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhangwo.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerServiceImpl playerService;

    @PostMapping("/registerDayCount")
    public JSONObject registerSum(@RequestBody JSONObject jsonObject) throws SQLException {
        String datetime = jsonObject.getString("datetime");
        return playerService.dayRegister(datetime);
    }

    @PostMapping("/registerAllCount")
    public JSONObject registerSum() throws SQLException {
        JSONObject s = playerService.countPlayer();
        return s;
    }

    @PostMapping("/registerCount")
    public String registerDetail(@RequestBody JSONObject jsonObject) throws SQLException {
        String startTime = jsonObject.get("startTime").toString();
        String toTime = jsonObject.get("toTime").toString();
        String s = playerService.everyDayPlayerRegister(startTime, toTime);
        return s;
    }

    @PostMapping("/countLogin")
    public String everyDayPlayerLogin(@RequestBody JSONObject jsonObject) throws SQLException {
        String startTime = jsonObject.get("startTime").toString();
        String toTime = jsonObject.get("toTime").toString();
        String s = playerService.everyDayPlayerLogin(startTime, toTime);
        return s;
    }

    /**
     * 今日登录人次
     */
    @PostMapping("/dayLoginPersonTime")
    public JSONObject dayLoginPersonTime(@RequestBody JSONObject jsonObject) throws SQLException {
        String datetime = jsonObject.getString("datetime");
        return playerService.dayLoginPersonTime(datetime);
    }

    @PostMapping("/onLinePlayer")
    public JSONObject onLinePlayer(@RequestBody JSONObject jsonObject) throws SQLException {
        String datetime = jsonObject.getString("datetime");
        return playerService.onLinePlayer(datetime);
    }
}
