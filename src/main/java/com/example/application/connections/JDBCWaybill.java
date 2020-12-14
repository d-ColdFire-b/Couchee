package com.example.application.connections;

import com.example.application.entity.Waybill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JDBCWaybill {

    @Autowired
    public DataSource dataSource;

    public List<Waybill> getWaybills() throws SQLException{
        List<Waybill> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_ALL_WAYBILLS);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Waybill waybill = new Waybill();
                waybill.setId(resultSet.getInt(1));
                waybill.setClientid(resultSet.getInt(2));
                waybill.setDate(resultSet.getDate(3));
                list.add(waybill);
            }

        }
        catch (Exception e){throw  e;}
        connection.close();
        return list;
    }

    public List<Waybill> getnamedWaybills() throws SQLException{
        List<Waybill> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_WAYBILLS_WITH_NAMES);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Waybill waybill = new Waybill();
                waybill.setId(resultSet.getInt(1));
                waybill.setClientname(resultSet.getString(2));
                waybill.setDate(resultSet.getDate(3));
                list.add(waybill);
            }

        }
        catch (Exception e){throw  e;}
        connection.close();
        return list;
    }

    public void newWaybill(String name, Date date) throws SQLException {
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_WAYBILL);
        callableStatement.setString(1,name);
        callableStatement.setDate(2, (java.sql.Date) date);
        callableStatement.execute();
        connection.close();

    }


}