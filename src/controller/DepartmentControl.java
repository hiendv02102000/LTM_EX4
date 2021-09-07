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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

        view.getjButton2().addActionListener((ActionEvent ae) -> {
            try {
                int r = view.getJTable().getSelectedRow();
                Department d = models.get(r);
                Department dd = view.getData();
                d.setDeptName(dd.getDeptName());
                d.setDeptNo(dd.getDeptNo());
                d.setLocation(dd.getLocation());
                dao.update(d);
                getAll();
            } catch (Exception e) {
            }
        });
        view.getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int r = view.getJTable().getSelectedRow();
                if (r > 0) {
                    Department dd = models.get(r);
                    view.setData(dd);
                }
            }

        });
        view.getjButton3().addActionListener((ActionEvent ae) -> {
            int r = view.getJTable().getSelectedRow();
            Department d = models.get(r);
            try {
                dao.delete(d);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            models.remove(r);
            getAll();
        });

    }

    public void getAll() {
        models.clear();
        models.addAll(Arrays.asList(dao.selectAll()));
        view.showtbl(models);
    }

}
