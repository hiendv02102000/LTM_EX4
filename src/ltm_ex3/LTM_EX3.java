/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_ex3;

import controller.EmployeeControl;
import controller.TimeKeeperControl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import model.Department;
import model.Employee;
import model.Timekeeper;
import view.MainFrame;

public class LTM_EX3 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException 
                | InstantiationException 
                | IllegalAccessException 
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MainFrame frame = new MainFrame();
        DepartmenControl department = new DepartmentControl(frame.getDepartmentPanell(), new ArrayList<Department>());
        EmployeeControl empControl = new EmployeeControl(frame.getEmployeePanel(), new ArrayList<Employee>());
        TimeKeeperControl control = new TimeKeeperControl(frame.getTimeKeeperPanel(), new ArrayList<Timekeeper>());
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }
}
