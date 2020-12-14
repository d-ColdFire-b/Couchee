package com.example.application.connections;

import com.example.application.entity.Cart;
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

    public List<Cart> getCart(Integer waybillid) throws SQLException{
        List<Cart> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_CART);
        callableStatement.setInt(1,waybillid);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()){
                Cart cart = new Cart();
                cart.setPropname(callableStatement.getString(1));
                list.add(cart);
            }

        }catch (Exception e){throw e;}
        connection.close();
        return list;

    }

    public void addCartmember(Integer waybillid, Integer propid, Integer price) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_CART_POS);
        callableStatement.setInt(1,waybillid);
        callableStatement.setInt(2,propid);
        callableStatement.setInt(3,price);
        callableStatement.execute();
        connection.close();

    }
}