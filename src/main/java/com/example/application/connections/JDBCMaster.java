package com.example.application.connections;

import com.example.application.entity.Master;
import com.example.application.form.Masterform;
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
public class JDBCMaster {

    @Autowired
    public DataSource dataSource;

    public List<Master> getMasters() throws SQLException{
        List<Master> list = new ArrayList<>();
        Connection connection =    dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_ALL_MASTERS);
        try(ResultSet resultSet = callableStatement.executeQuery() ) {
            while (resultSet.next()){
                Master master = new Master();
                master.setId(resultSet.getInt(1));
                master.setName(resultSet.getString(2));
                list.add(master);
            }

        }catch (Exception e){throw  e;}
        connection.close();
        return list;
    }

    public void newmaster(Masterform masteform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_MASTER);
        callableStatement.setString(1, masteform.getName());
        callableStatement.execute();
        connection.close();

    }
}
