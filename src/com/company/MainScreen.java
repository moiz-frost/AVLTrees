package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Coder on 12/11/2015.
 */
public class MainScreen {

    private JButton showDatabaseButton;
    private JPanel panel1;
    private JButton createTableButton;
    private JLabel totalTablesLabel;
    JFrame frame = new JFrame();
    public MainScreen() {

        createTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTable createForm = new CreateTable();
                createForm.mainDisplay();
            }
        });
        showDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ShowDatabase show = new ShowDatabase();
                show.display();
            }
        });
    }



    public void display(){
        frame.add(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setVisible(true);
    }
}
