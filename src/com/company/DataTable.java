package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Coder on 12/9/2015.
 */
public class DataTable {
    private AvlTree tree;
    private int totalCols;
    private String [] schema;
    String tableName;
    DataTable(String tableName){
        this.tableName = tableName;
    }
    public void defineColumns(String[] columns){
        tree = new AvlTree(columns);
        totalCols = columns.length;
        schema = columns;
    }
    public String[] getSchema(){
        return  schema;
    }
    private String[] getColNames(){
        return  tree.columnNames;
    }
    @Override
    public String toString(){
        return tableName;
    }
    public int getNumberOfRecords(){
        return tree.recordCount;
    }

    public Object [] getRecord(int key){
        return tree.getRecord(key);
    }

   public boolean insert(Object[] data){
       if(tree == null){
           System.out.println("Schema is undefined");
           return  false;
       }
       if(data.length != totalCols){
           System.out.println("Invalid Data");
           return false;
       }
       DataNode record = new DataNode(tree.recordCount +1, data);
       tree.insert(record);
       return true;
   }

    public void displayTable(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String header[]  = getHeader();
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);
        for (int count = 1; count <= getNumberOfRecords(); count++) {
            //Add id to object array in DataNode
            dtm.addRow(getRecord(count));
        }
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public void showRecord(int key){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String header[]  = getHeader();
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);
            dtm.addRow(getRecord(key));
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    private String [] getHeader(){
        String [] temp = new String[getColNames().length+1];
        temp[0] = "ID";
        for (int i = 0; i < getColNames().length; i++) {
            temp[i+1]  = getColNames()[i];
        }
        return temp;
    }
}
