/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import model.Timekeeper;

/**
 *
 * @author Ronin
 */
public class DAOTimeKeeper extends IDAO<Timekeeper> {

    public DAOTimeKeeper(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
    }

    @Override
    public Timekeeper[] selectAll() {
        Vector<Timekeeper> tk = new Vector<Timekeeper>();
        Timekeeper[] result;
        try {
            String sql = "Select * from TIMEKEEPER";
            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                Timekeeper e = new Timekeeper(
                        rs.getString(1),
                        rs.getDate(2),
                        BigInteger.valueOf(rs.getInt(4)),
                        rs.getString(3)
                );
                tk.add(e);
                i++;
            }
            result = new Timekeeper[i];
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
            return null;
        }
        return tk.toArray(result);
    }

    @Override
    public Timekeeper[] selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Timekeeper object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Timekeeper object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
