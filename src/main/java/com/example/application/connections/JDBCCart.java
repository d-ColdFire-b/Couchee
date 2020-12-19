package com.example.application.connections;

import com.example.application.entity.Cart;
import com.example.application.form.Cartform;
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

    public List<Cart> getCart(Cartform cartform) throws SQLException{
        List<Cart> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.GET_CART);
        callableStatement.setInt(1,cartform.getId());
        callableStatement.execute();
        try(ResultSet resultSet = callableStatement.getResultSet()) {
            while (resultSet.next()){
                Cart cart = new Cart();
                cart.setPropname(callableStatement.getString(1));
                cart.setPrice(callableStatement.getInt(2));
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
        callableStatement.setInt(3,cartform.getPrice());
        callableStatement.execute();
        connection.close();

    }
}
