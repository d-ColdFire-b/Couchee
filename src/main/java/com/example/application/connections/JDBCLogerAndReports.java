package com.example.application.connections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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

    public 
}
