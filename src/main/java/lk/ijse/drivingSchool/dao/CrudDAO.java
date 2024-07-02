package lk.ijse.drivingSchool.dao;

import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T>{
    public ArrayList<T> getAll() throws SQLException;

    public  boolean save(T entity) throws SQLException ;

    public  boolean delete(String nic) throws SQLException ;

    public  T get(String nic) throws SQLException ;


    public  String getNextId() throws SQLException ;

    public  String getName(String id) throws SQLException;

    public  String getId(String NIC) throws SQLException ;


}
