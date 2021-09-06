/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.DAOTimeKeeper;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Timekeeper;
import view.TimeKeeperPanel;

/**
 *
 * @author Ronin
 */
public class TimeKeeperControl {

    private TimeKeeperPanel view;
    private ArrayList<Timekeeper> models;
    private DAOTimeKeeper dao;

    public TimeKeeperControl(TimeKeeperPanel view, ArrayList<Timekeeper> models) {
        try {
            dao = new DAOTimeKeeper(ConnectionUtils.getMyConnection());
            this.models = models;
            this.view = view;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());;
            this.dao.closeConnection();
            System.exit(0);
        }
//        view.getBtnAdd().addActionListener((ActionEvent ae) -> {
//            getAll();
//        });
    }
    
    public void getAll() {
        models.addAll(Arrays.asList(dao.selectAll()));
        view.showList(String.valueOf(models.size()));
    }
}
