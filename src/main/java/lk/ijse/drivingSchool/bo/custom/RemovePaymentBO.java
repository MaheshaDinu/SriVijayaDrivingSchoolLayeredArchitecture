package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;

import java.sql.SQLException;

public interface RemovePaymentBO extends SuperBO {
    public  String getId(String NIC) throws SQLException ;
    public  boolean delete(String date, String studentId, String amount) throws SQLException;
}
