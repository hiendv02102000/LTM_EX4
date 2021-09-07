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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ListSelectionEvent;
import model.Timekeeper;
import view.TimeKeeperPanel;

/**
 *
 * @author Ronin
 */
public final class TimeKeeperControl {

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
        getAll();
        view.getBtnAdd().addActionListener((ActionEvent ae) -> {
            add();
        });
        view.getTbTimeKeeper().getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            view.showSelectedRow();
        });
        view.getBtnEdit().addActionListener((ActionEvent ae) -> {
            edit();
        });
        view.getBtnDelete().addActionListener((ActionEvent ae) -> {
            delete();
        });
    }

    public void getAll() {
        models.clear();
        models.addAll(Arrays.asList(dao.selectAll()));
        view.showList(models);
    }

    public void add() {
        Timekeeper tk;
        try {
            tk = view.getData();
        } catch (ParseException ex) {
            view.showMessage("Dữ liệu nhập không hợp lệ vui lòng thử lại.");
            return;
        }
        int i = dao.insert(tk);
        if (i == 0) {
            view.showMessage("Thêm thất bại, vui lòng thử lại.");
        } else {
            getAll();
            view.showMessage("Thêm thành công.");
        }
    }

    public void edit() {
        Timekeeper tk;
        try {
            tk = view.getData();
        } catch (ParseException ex) {
            view.showMessage("Dữ liệu nhập không hợp lệ vui lòng thử lại.");
            return;
        }
        int i = dao.update(tk);
        if (i == 0) {
            view.showMessage("Thêm thất bại, vui lòng thử lại.");
        } else {
            getAll();
            view.showMessage("Sửa thành công.");
        }
    }

    public void delete() {
        Timekeeper tk;
        try {
            tk = view.getData();
        } catch (ParseException ex) {
            view.showMessage("Dữ liệu nhập không hợp lệ vui lòng thử lại.");
            return;
        }
        int i = dao.detete(tk);
        if (i == 0) {
            view.showMessage("Xóa thất bại, vui lòng thử lại.");
        } else {
            getAll();
            view.showMessage("Xóa thành công.");
        }
    }
}
