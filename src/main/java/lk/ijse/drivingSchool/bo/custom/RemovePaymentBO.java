package lk.ijse.drivingSchool.bo.custom;

import java.sql.SQLException;

public interface RemovePaymentBO {
    public  String getId(String NIC) throws SQLException ;
    public  boolean delete(String date, String studentId, String amount) throws SQLException;
}
