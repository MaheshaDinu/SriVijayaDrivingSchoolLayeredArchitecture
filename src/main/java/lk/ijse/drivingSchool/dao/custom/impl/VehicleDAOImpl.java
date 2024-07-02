package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.VehicleDAO;
import lk.ijse.drivingSchool.entity.Vehicle;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    public  List<String> getVehicleList() throws SQLException {
        /*String sql = "select * from vehicle";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtil.execute("select * from vehicle");
        List<String> vehicleList = new ArrayList<>();
        while (resultSet.next()){
            vehicleList.add(resultSet.getString("type"));

        }
        return vehicleList;
    }

    public  String getId(String vehicle) throws SQLException {
        String id =null;
        /*String sql = "select * from vehicle where type =?";

            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1,vehicle);*/
            ResultSet resultSet = SQLUtil.execute("select * from vehicle where type =?",vehicle);
            if (resultSet.next()){
                 id = resultSet.getString("vehicle_id");
            }

        return id;
    }

    public  boolean save(Vehicle vehicle) throws SQLException {
        /*String sql = "INSERT INTO vehicle (license_plate,type, manufacturer, model, year, colour, transmission_type, fuel_type, availability) VALUES(?,?,?,?,?,?,?,?,?)";
        Connection connection =DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,vehicle.getLicensePlate());
        pstm.setObject(2,vehicle.getType());
        pstm.setObject(3,vehicle.getManufacturer());
        pstm.setObject(4,vehicle.getModel());
        pstm.setObject(5,vehicle.getYear());
        pstm.setObject(6,vehicle.getColour());
        pstm.setObject(7,vehicle.getTransmissionType());
        pstm.setObject(8,vehicle.getFuelType());
        pstm.setObject(9,vehicle.getAvailability());*/

        return SQLUtil.execute("INSERT INTO vehicle (license_plate,type, manufacturer, model, year, colour, transmission_type, fuel_type, availability) VALUES(?,?,?,?,?,?,?,?,?)",vehicle.getLicensePlate(),vehicle.getType(),vehicle.getManufacturer(),vehicle.getModel(),vehicle.getYear(),vehicle.getColour(),vehicle.getTransmissionType(),vehicle.getFuelType(),vehicle.getAvailability());
    }

    public  String getVehicle(String vehicleId) throws SQLException {
        String vehicle= null;
        /*String sql ="select * from vehicle where vehicle_id = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,vehicleId);*/
            ResultSet resultSet = SQLUtil.execute("select * from vehicle where vehicle_id = ?",vehicleId);
            if (resultSet.next()){
                vehicle = resultSet.getString("type");
            }
            return vehicle;


    }

    public  ArrayList<Vehicle> getAll() throws SQLException {
        /*String sql = "select * from vehicle";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);*/
            ResultSet resultSet = SQLUtil.execute("select * from vehicle");
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getString("license_plate"),resultSet.getString("type"),resultSet.getString("manufacturer"),resultSet.getString("model"),resultSet.getString("year"),resultSet.getString("colour"),resultSet.getString("transmission_type"),resultSet.getString("fuel_type"),resultSet.getString("availability"));
                vehicles.add(vehicle);
            }
            return vehicles;

    }

    public  boolean delete(String license) throws SQLException {
        /*String sql = "delete from vehicle where license_number =?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,license);*/
            return SQLUtil.execute("delete from vehicle where license_number =?",license);

    }

    public  Vehicle get(String license) throws SQLException {
        Vehicle vehicle =null;
        /*String sql = "select * from vehicle where license_plate = ?";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,license);*/
            ResultSet resultSet = SQLUtil.execute("select * from vehicle where license_plate = ?",license);
            if (resultSet.next()){
                vehicle = new Vehicle(resultSet.getString("license_plate"),resultSet.getString("type"),resultSet.getString("manufacturer"),resultSet.getString("model"),resultSet.getString("year"),resultSet.getString("colour"),resultSet.getString("transmission_type"),resultSet.getString("fuel_type"),resultSet.getString("availability"));
            }
            return vehicle;

    }

    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public String getName(String id) {
        return null;
    }
}
