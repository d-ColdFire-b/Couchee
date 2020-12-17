package com.example.application.connections;

import com.example.application.entity.Client;
import com.example.application.form.Clientform;
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
public class JDBCCLient {

    @Autowired
    public DataSource dataSource;

    public List<Client> getClients () throws SQLException{
        List<Client> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement  callableStatement = connection.prepareCall(ProcedurList.GET_ALL_CLIENT);
        try(ResultSet resultSet = callableStatement.executeQuery()){
            while(resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setName(resultSet.getString(2));
                client.setDiscount(resultSet.getInt(3));
                list.add(client);
            }
        }    catch (Exception e){throw e;}
           connection.close();
        return list;
    }

    public void newClient (Clientform clientform) throws SQLException{
            Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_CLIENT);
            callableStatement.setString(1,clientform.getName());
            callableStatement.setInt(2,clientform.getDiscount());
            callableStatement.execute();
            connection.close();

    }
}
