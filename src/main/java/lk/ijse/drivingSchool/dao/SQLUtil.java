package lk.ijse.drivingSchool.dao;

import lk.ijse.drivingSchool.Db.DbConnnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql,Object... args) throws SQLException {
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        for (int i = 0 ;i< args.length;i++){
            ptsm.setObject(i + 1,args[i]);
        }
        if (sql.startsWith("select")){
            return (T) ptsm.executeQuery();
        } else {
            return (T) (Boolean) (ptsm.executeUpdate()>0);
        }
    }
}
