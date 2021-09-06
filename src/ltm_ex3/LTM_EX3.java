/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_ex3;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author toank
 */
public class LTM_EX3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        String host = "20.198.248.201";
        String dbName = "contactdb";
        int port = 3306;
        String mysqlURL ="jdbc:mysql:"+host +":"+ port + "/" +dbName;
        
        String username = "tmpuser";
        String password = "tmpuser@123";

        String dbClass = "com.mysql.jdbc.Driver";
        try {
             Class.forName(dbClass);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }
        Connection connection = (Connection) DriverManager.getConnection(mysqlURL,username, password);
 
        
        Statement statement = connection.createStatement();
        connection.close();
        System.err.println("Hello world" + "hello work" + "wwww");
       
        
        System.out.println("hiii");
    }
    
}
