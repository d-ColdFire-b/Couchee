package com.example.application.connections;

import com.example.application.entity.Report;
import com.example.application.form.Logerform;
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

    public void newLogertext(Logerform logerform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_LOGER_TEXT);
        callableStatement.setString(1,logerform.getText());
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

    public List<Report> masterreport(Integer masterid) throws SQLException{
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.MASTER_REPORT);
        callableStatement.setInt(1,masterid);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Report report = new Report();
                report.setPropid(callableStatement.getInt(1));
                report.setPropname(callableStatement.getString(2));
                list.add(report);
            }
        }catch (Exception e) {throw e;}
        connection.close();
        return list;
    }

    public List<Report> propsreport(Integer typeid) throws SQLException{
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.PROPS_REPORT);
        callableStatement.setInt(1,typeid);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Report report = new Report();
                report.setPropname(callableStatement.getString(1));
                list.add(report);
            }
        } catch (Exception e){throw  e;}
        return list;
    }

    public List<Report> clientpropreport(Integer clientid) throws SQLException{
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.CLIENT_PROP_REPORT);
        callableStatement.setInt(1,clientid);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Report report = new Report();
                report.setPropname(callableStatement.getString(1));
                list.add(report);
            }
        } catch (Exception e){throw  e;}
        return list;
    }
}
