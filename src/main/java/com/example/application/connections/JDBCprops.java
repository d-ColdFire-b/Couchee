package com.example.application.connections;

import com.example.application.entity.Prop;
import com.example.application.form.Masterform;
import com.example.application.form.Priceform;
import com.example.application.form.Propform;
import com.example.application.form.QuantityForm;
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
                    prop.setTypename(resultSet.getString(2));
                    prop.setId(resultSet.getInt(3));
                    prop.setNumberof(resultSet.getInt(4));
                    prop.setPrice(resultSet.getInt(5));
                    list.add(prop);
                }
            } catch (Exception e){
                throw e;
            }
            connection.close();
        return list;
    }

    public void newprop(Propform propform) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.NEW_PROP);
        callableStatement.setString(1, propform.getName());
        callableStatement.setInt(2,propform.getMasterid());
        callableStatement.setInt(3,propform.getTypeid());
        callableStatement.setInt(4,propform.getPrice());
        callableStatement.execute();
        connection.close();
    }

    public void  addpropnumber(QuantityForm quantityForm) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.ADD_NUMBER_OF_PROPS);
        callableStatement.setInt(1,quantityForm.getNumberof());
        callableStatement.setInt(2,quantityForm.getPropid());
        callableStatement.execute();
        connection.close();

    }

    public void addnewhistorycraft(QuantityForm quantityForm) throws SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.ADD_NEW_CRAFT_ROW);
        callableStatement.setInt(1,quantityForm.getMasterid());
        callableStatement.setInt(2,quantityForm.getPropid());
        callableStatement.setInt(3,quantityForm.getNumberof());
        callableStatement.execute();
        connection.close();

    }

    public void updatepropprice(Priceform priceform) throws  SQLException{
        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall(ProcedurList.UPDATE_PROP_PRICE);
        callableStatement.setInt(1, priceform.getPrice());
        callableStatement.setInt(2,priceform.getPropid());
        callableStatement.execute();
        connection.close();

    }


}
