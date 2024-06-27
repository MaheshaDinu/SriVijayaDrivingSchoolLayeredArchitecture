package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
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
        String sql = "select * from vehicleClass where vehicle_class_id = ?";

        try {
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);
            ptsm.setObject(1,vehicleClassId);
            resultSet = ptsm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (resultSet.next()) {
            vehicleClass =resultSet.getString("vehicle_class");
        }
        return vehicleClass;

    }

    public  List<String> getAllVehicleClass() throws SQLException {
        String sql = "select * from vehicleClass";

        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<String> classList = new ArrayList<>();
        while (resultSet.next()){
            classList.add(resultSet.getString("vehicle_class"));
        }
        return classList;
    }

    public  String getId(String vehicleClass) {
        String id =null;
        String sql = "select * from vehicleClass where vehicle_class =?";
        try {
            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1,vehicleClass);
            ResultSet resultSet =pstm.executeQuery();
            if (resultSet.next()){
                id = resultSet.getString("vehicle_class_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public  String getFee(String vehicleClassId) {
        String sql = "select * from vehicleClass where vehicle_class_id = ?";
        String fee = "0";
        try {
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);
            ptsm.setObject(1,vehicleClassId);
            ResultSet resultSet = ptsm.executeQuery();
            if (resultSet.next()){
                fee = resultSet.getString("fee");
            }
            return fee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
