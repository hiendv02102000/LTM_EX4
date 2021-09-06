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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MySQLConnUtils {
 
 // Kết nối vào MySQL.
 public static Connection getMySQLConnection() throws SQLException,
         ClassNotFoundException {
     String hostName = "20.198.248.201";
 
     String dbName = "contactdb";
     String userName = "tmpuser";
     String password = "tmpuser@123";
 
     return getMySQLConnection(hostName, dbName, userName, password);
 }
 
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {

     Class.forName("com.mysql.jdbc.Driver");
 
     // Ví dụ: jdbc:mysql://localhost:3306/simplehr
     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?zeroDateTimeBehavior=convertToNull";
 
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}