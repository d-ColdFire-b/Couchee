package com.example.application.connections;

import com.example.application.entity.Cart;
import com.example.application.form.Cartform;
import com.example.application.form.QuantityForm;
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
public class JDBCCart {

    @Autowired
    public DataSource dataSource;

    public List<Cart> getcart(Cartform cartform) throws SQLException{
        List<Cart> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_CART);
        callableStatement.setInt(1,cartform.getId());
        try(ResultSet resultSet = callableStatement.executeQuery()){
            while (resultSet.next()){
                Cart cart = new Cart();
                cart.setPropname(resultSet.getString(1));
                cart.setPrice(resultSet.getInt(2));
                cart.setNumberof(resultSet.getInt(3));
                list.add(cart);
            }
        }catch (Exception e){throw e;}
        connection.close();
        return list;
    }

    public void addCartmember(Cartform cartform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_CART_POS);
        callableStatement.setInt(1,cartform.getWaybillid());
        callableStatement.setInt(2,cartform.getPropid());
        callableStatement.setInt(3,cartform.getNumberof());
        callableStatement.execute();
        connection.close();

    }

    public void  removepropnumber(Cartform cartform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.REMOVE_NUMBER_OF_PROPS);
        callableStatement.setInt(1,cartform.getNumberof());
        callableStatement.setInt(2,cartform.getPropid());
        callableStatement.execute();
        connection.close();

    }
}
