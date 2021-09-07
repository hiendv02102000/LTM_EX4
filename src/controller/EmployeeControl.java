package controller;

import java.sql.SQLException;

import controller.dao.DAOEmployee;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
            System.err.println(e.getMessage());
            this.dao.closeConnection();
            System.exit(0);
        }
        getAll();
        view.getjButton1().addActionListener((ActionEvent ae) -> {
            Employee e = view.getData();
            dao.insert(e);
            getAll();
        });

        view.getjButton2().addActionListener((ActionEvent ae) -> {
            try {
                int r = view.getJTable().getSelectedRow();
                Employee e = models.get(r);
                Employee ee = view.getData();
                e.setEmpNo(ee.getEmpNo());
                e.setEmpName(ee.getEmpName());
                e.setJob(ee.getJob());
                e.setSalary(ee.getSalary());
                e.setDeptId(ee.getDeptId());
                dao.update(e);
                getAll();
            } catch (Exception e) {
            }
        });

        view.getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int r = view.getJTable().getSelectedRow();
                if (r > 0) {
                    Employee ee = models.get(r);
                    view.setData(ee);
                }

            }
        });

        view.getjButton3().addActionListener((ActionEvent ae) -> {
            int r = view.getJTable().getSelectedRow();
            Employee e = models.get(r);
            try {
                dao.delete(e);
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
        view.setDefault(models);
    }
}
