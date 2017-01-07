package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Coder on 12/11/2015.
 */
public class ShowDatabase {
    private JComboBox tablescomboBox;
    private JPanel panel1;
    private JLabel selectTableLabel;
    private JButton selectTableButton;
    private JLabel editTableLabel;
    private JComboBox editTableComboBox;
    private JButton editTableButton;

    JFrame frame = new JFrame();

    public ShowDatabase() {

        selectTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               DataTable temp = (DataTable) tablescomboBox.getSelectedItem();
                temp.displayTable();
            }
        });
        editTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Opens The Edit Table Window
                EditTable editTable = new EditTable((DataTable) editTableComboBox.getSelectedItem());
                editTable.display();
            }
        });
    }



    public void display(){
        frame.add(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setVisible(true);
        for (int i = 0; i < Main.dbase.getTotalTables() ; i++) {
            tablescomboBox.addItem(Main.dbase.getTablesList().get(i));
            editTableComboBox.addItem(Main.dbase.getTablesList().get(i));
        }
    }
}
