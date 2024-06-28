package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleClassDAOImpl implements VehicleClassDAO {
    public  String getVehicleClass(String vehicleClassId) throws SQLException {
        String vehicleClass=null;
        ResultSet resultSet = null;
        /*String sql = "select * from vehicleClass where vehicle_class_id = ?";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);
            ptsm.setObject(1,vehicleClassId);*/
            resultSet = SQLUtil.execute("select * from vehicleClass where vehicle_class_id = ?",vehicleClassId);


        if (resultSet.next()) {
            vehicleClass =resultSet.getString("vehicle_class");
        }
        return vehicleClass;

    }

    public  List<String> getAllVehicleClass() throws SQLException {
       /* String sql = "select * from vehicleClass";

        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtil.execute("select * from vehicleClass");

        List<String> classList = new ArrayList<>();
        while (resultSet.next()){
            classList.add(resultSet.getString("vehicle_class"));
        }
        return classList;
    }

    public  String getId(String vehicleClass) throws SQLException {
        String id =null;
        /*String sql = "select * from vehicleClass where vehicle_class =?";

            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1,vehicleClass);*/
            ResultSet resultSet =SQLUtil.execute("select * from vehicleClass where vehicle_class =?",vehicleClass);
            if (resultSet.next()){
                id = resultSet.getString("vehicle_class_id");
            }

        return id;
    }

    public  String getFee(String vehicleClassId) throws SQLException {

        String fee = "0";
       /* String sql = "select * from vehicleClass where vehicle_class_id = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);
            ptsm.setObject(1,vehicleClassId);*/
            ResultSet resultSet = SQLUtil.execute("select * from vehicleClass where vehicle_class_id = ?",vehicleClassId);
            if (resultSet.next()){
                fee = resultSet.getString("fee");
            }
            return fee;

    }
}
