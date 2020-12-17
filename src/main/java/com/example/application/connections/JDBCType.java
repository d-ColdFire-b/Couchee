package com.example.application.connections;

import com.example.application.entity.Type;
import com.example.application.form.Typeform;
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
public class JDBCType {

    @Autowired
    public DataSource dataSource;

    public List<Type> getalltypes() throws SQLException {
        List<Type> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_ALL_TYPES);
        try(ResultSet resultSet = callableStatement.executeQuery()){
            while (resultSet.next()){
               Type type = new Type();
               type.setId(resultSet.getInt(1));
               type.setName(resultSet.getString(2));
               list.add(type);
            }

        }catch (Exception e){throw e;}

         connection.close();
        return  list;
    }

    public void newtype(Typeform typeform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_TYPE);
        callableStatement.setString(1,typeform.getName());
        callableStatement.execute();
        connection.close();

    }


}
