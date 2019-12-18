package com.zhangwo.dao;

import com.zhangwo.utils.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ImpalaDao {

    @Autowired
    @Qualifier("impalaDruidDataSource")
    DataSource impalaDruidDataSource;

//    public int onlineNumber(String datetime) throws SQLException {
//        Statement statement = impalaDruidDataSource.getConnection().createStatement();
//        String sql = "SELECT t1.c1 - t2.c2 from \n" +
//                "(SELECT count(*) c1 FROM playerlogin WHERE `datetime` BETWEEN '2019-12-11' and '2019-12-12') t1,\n" +
//                "(SELECT count(*) c2 FROM playerlogin WHERE `datetime` BETWEEN '2019-12-11' and '2019-12-12') t2";
//        ResultSet resultSet = statement.executeQuery(sql);
//        GetResult getResult = new GetResult();
//        String json = getResult.getJson(resultSet);
//        System.out.println(json);
//        return 0;
//    }
}
