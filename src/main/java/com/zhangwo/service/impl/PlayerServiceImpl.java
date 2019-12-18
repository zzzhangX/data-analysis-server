package com.zhangwo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangwo.dao.PlayerDao;
import com.zhangwo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PlayerServiceImpl implements PlayerService {

   @Autowired
    PlayerDao playerDao;

    @Override
    public JSONObject dayRegister(String datetime) throws SQLException {
        return playerDao.dayRegister(datetime);
    }

    @Override
    public JSONObject countPlayer() throws SQLException {
        JSONObject jsonObject = playerDao.countPlayer();
        return  jsonObject;
    }

    @Override
    public String everyDayPlayerRegister(String fromTime, String toTime) throws SQLException {
        String playerCount = playerDao.everyDayPlayerRegister(fromTime, toTime);
        return playerCount;
    }

    @Override
    public String everyDayPlayerLogin(String fromTime, String toTime) throws SQLException {
        String everyDayPlayerLogin = playerDao.everyDayPlayerLogin(fromTime, toTime);
        return everyDayPlayerLogin;
    }

    @Override
    public JSONObject dayLoginPersonTime(String datetime) throws SQLException {
        return playerDao.dayPersonTime(datetime);
    }

    @Override
    public JSONObject onLinePlayer(String datetime) throws SQLException {
        return playerDao.onLinePlayer(datetime);
    }
}
