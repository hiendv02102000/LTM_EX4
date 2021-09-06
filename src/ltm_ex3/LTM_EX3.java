/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_ex3;

import java.sql.DriverManager;
import dbconnection.MySQLConnUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author toank
 */
public class LTM_EX3 {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {
        return MySQLConnUtils.getMySQLConnection();
    }


    public static void main(String[] args) throws SQLException {
        String host = "20.198.248.201";
        String dbName = "contactdb";
        int port = 3306;
        String mysqlURL ="jdbc:mysql://"+host +":"+ port + "/" +dbName;
        
        String username = "tmpuser";
        String password = "tmpuser@123";

        Connection connection = (Connection) DriverManager.getConnection(mysqlURL, username, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM EMPLOYEE";
        ResultSet resultSet =  statement.executeQuery(query);    
        while(resultSet.next()) {
            System.out.println(resultSet.getString(1) + " "
            + resultSet.getString(2) + " "
            + resultSet.getString(3));
        }
        
        connection.close();
        System.err.println("Hello world" + "hello work" + "wwww");
       
        
        System.out.println("hiii");
    }
}
