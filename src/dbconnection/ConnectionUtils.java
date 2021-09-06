/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

/**
 *
 * @author toank
 */
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {
        // Sử dụng Mysql.
        // Bạn có thể thay thế bởi Database nào đó.
        return MySQLConnUtils.getMySQLConnection();
    }

    //
    // Test Connection ...
    //
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {

        System.out.println("Get connection ... ");

        // Lấy ra đối tượng Connection kết nối vào database.
        Connection conn = ConnectionUtils.getMyConnection();

        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM contactdb.EMPLOYEE");
        while (result.next()) {
            System.out.println(result.getString(1) + " "
                    + result.getString(2) + " "
                    + result.getString(3));
        }
        System.out.println("Get connection " + conn);

        System.out.println("Done!");
    }

}
