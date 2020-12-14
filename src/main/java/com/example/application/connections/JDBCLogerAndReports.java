package com.example.application.connections;

import com.example.application.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCLogerAndReports {

    @Autowired
    public DataSource dataSource;

    public void newLogertext(String text) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_LOGER_TEXT);
        callableStatement.setString(1,text);
        callableStatement.execute();
        connection.close();

    }

    public List<Report> clientReport (Integer clientid) throws SQLException {
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.CLIENT_REPORT);
        callableStatement.setInt(1,clientid);
        try (ResultSet resultSet = callableStatement.executeQuery()){
            while(resultSet.next()){
                Report report = new Report();
                report.setWaybillid(callableStatement.getInt(1));
                report.setWaybillfate(callableStatement.getDate(2));

            }
        } catch (Exception e){throw e;}
        connection.close();
        return list;
    }
}
