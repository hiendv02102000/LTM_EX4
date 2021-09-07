/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.DAODepartment;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Department;
import view.DepartmentPanel;

/**
 *
 * @author toank
 */
public class DepartmentControl {

    private DepartmentPanel view;
    private ArrayList<Department> models;
    private DAODepartment dao;
    public DepartmentControl(DepartmentPanel departmentPanell, ArrayList<Department> arrayList) throws SQLException {
        try {
            dao = new DAODepartment(ConnectionUtils.getMyConnection());
            this.models = arrayList;
            this.view = departmentPanell;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            this.dao.closeConnection();
            System.exit(0);
        }
        getAll();
        view.getjButton1().addActionListener((ActionEvent ae) -> { 
            Department d = view.getData();
            dao.insert(d);
            view.addRow(d);
            //getAll();
        });
        
    }
    
    public void getAll() {
        models.clear();
        models.addAll(Arrays.asList(dao.selectAll()));
        view.showtbl(models);
    }


    
}
