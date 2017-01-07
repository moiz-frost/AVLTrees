package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Coder on 12/9/2015.
 */
public class CreateTable {


    JTextField tableNameField;
    JLabel tableNameLabel;
    JTextField colNamesField;
    JLabel colNamesLabel;
    JButton createButton;
    private JPanel panel;
    private JLabel successLabel;
    JFrame frame = new JFrame();
    CreateTable(){
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataTable temp = new DataTable(tableNameField.getText());
                String []colNames = colNamesField.getText().split(",");
                temp.defineColumns(colNames);
                Main.dbase.addTables(temp);
                successLabel.setText("Table Created Successfully");
            }
        });
    }



    public void mainDisplay(){
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setVisible(true);



    }
}
