/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_ex3;

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

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        System.out.println("Get connection ... ");
        Connection conn = LTM_EX3.getMyConnection();

        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM contactdb.EMPLOYEE");

        while (result.next()) {
            System.out.println(result.getString(1) + " "
                    + result.getString(2) + " "
                    + result.getString(3));
        }
        System.out.println("Done!");
    }
}
