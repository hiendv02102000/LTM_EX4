package controller;

import java.sql.SQLException;

import controller.dao.DAOEmployee;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import model.Employee;
import view.EmployeePanel;


public class EmployeeControl {
    private EmployeePanel view;
    private ArrayList<Employee> models;
    private DAOEmployee dao;
    
    public EmployeeControl(EmployeePanel view, ArrayList<Employee> models) {
        try {
            dao = new DAOEmployee(ConnectionUtils.getMyConnection());
            this.models = models;
            this.view = view;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());;
            this.dao.closeConnection();
            System.exit(0);
        }
        getAll();
        view.getjButton1().addActionListener((ActionEvent ae) -> {
            
        });
    }
    
    public void getAll() {
        models.addAll(Arrays.asList(dao.selectAll()));
        view.setDefault(models);
    }
}
