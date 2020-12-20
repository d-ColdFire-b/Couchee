package com.example.application.connections;

import com.example.application.entity.*;
import com.example.application.form.Clientform;
import com.example.application.form.Logerform;
import com.example.application.form.Masterform;
import com.example.application.form.Typeform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JDBCLogerAndReports {

    @Autowired
    public DataSource dataSource;



    public String GetDateTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return format.format(date);
    }

    public void newLogertext(String text) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_LOGER_TEXT);
        callableStatement.setString(1,text);
        callableStatement.execute();
        connection.close();

    }

    public List<Report> clientReport (Clientform clientform) throws SQLException {
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.CLIENT_REPORT);
        callableStatement.setInt(1,clientform.getId());
        try (ResultSet resultSet = callableStatement.executeQuery()){
            while(resultSet.next()){
                Report report = new Report();
                report.setWaybillid(resultSet.getInt(1));
                report.setWaybillfate(resultSet.getDate(2));

            }
        } catch (Exception e){throw e;}
        connection.close();
        return list;
    }

    public List<Prop> masterreport(Masterform masterform) throws SQLException{
        List<Prop> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.MASTER_REPORT);
        callableStatement.setInt(1,masterform.getId());
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Prop prop = new Prop();
                prop.setId(resultSet.getInt(1));
                prop.setName(resultSet.getString(2));
                list.add(prop);
            }
        }catch (Exception e) {throw e;}
        connection.close();
        return list;
    }

    public List<Prop> propsreport(Typeform typeform) throws SQLException{
        List<Prop> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.PROPS_REPORT);
        callableStatement.setInt(1,typeform.getId());
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Prop prop = new Prop();
                prop.setName(resultSet.getString(1));
                list.add(prop);
            }
        } catch (Exception e){throw  e;}
        return list;
    }

    public List<Prop> clientpropreport(Clientform clientform) throws SQLException{
        List<Prop> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.CLIENT_PROP_REPORT);
        callableStatement.setInt(1,clientform.getId());
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Prop prop = new Prop();
                prop.setName(callableStatement.getString(1));
                list.add(prop);
            }
        } catch (Exception e){throw  e;}
        return list;
    }
}
