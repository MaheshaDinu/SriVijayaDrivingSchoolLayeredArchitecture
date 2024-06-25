package lk.ijse.drivingSchool.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnnection {

    private static DbConnnection dbConnnection;

    private Connection connection;

    private DbConnnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sriVijayaDrivingSchool",
                "root",
                "Ijse@123"
        );
    }
    public static DbConnnection getInstance() throws SQLException {
        return (dbConnnection == null) ? dbConnnection = new DbConnnection() : dbConnnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
