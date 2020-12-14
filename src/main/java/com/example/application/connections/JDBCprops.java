package com.example.application.connections;

import com.example.application.entity.Prop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCprops {

    @Autowired
    public DataSource dataSource;

    public List<Prop> getProps() throws SQLException {
        List<Prop> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_ALL_PROPS);
                try(ResultSet resultSet = callableStatement.executeQuery()){
                    while (resultSet.next()){
                        Prop prop = new Prop();
                        prop.setId(resultSet.getInt(1));
                        prop.setName(resultSet.getString(2));
                        prop.setMasterid(resultSet.getInt(3));
                        prop.setTypeid(resultSet.getInt(4));
                        list.add(prop);
                    }
                } catch (Exception e){
                    throw e;
                }
        connection.close();
        return list ;
    }

    public List<Prop> getnamedProps() throws SQLException{
        List<Prop> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_PROPS_WITH_NAMES);
            try(ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()){
                    Prop prop = new Prop();
                    prop.setName(resultSet.getString(1));
                    prop.setMastername(resultSet.getString(2));
                    prop.setTypename(resultSet.getString(3));
                    list.add(prop);
                }
            } catch (Exception e){
                throw e;
            }
            connection.close();
        return list;
    }

    public void createprop(String name,Integer masterid,Integer typeid) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_PROP);
        callableStatement.setString(1,name);
        callableStatement.setInt(2,masterid);
        callableStatement.setInt(3,typeid);
        callableStatement.execute();
        connection.close();
    }
}
