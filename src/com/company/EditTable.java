package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Coder on 12/11/2015.
 */
public class EditTable {
    private JButton importCsvButton;
    private JPanel panel1;
    private JButton xmlExportButton;
    private JLabel findIdLabel;
    private JTextField findIdTextField;
    private JButton findButton;
    private JLabel durationLabel;
    private DataTable actionTable;

    JFrame frame = new JFrame();

    public EditTable(final DataTable table) {
        actionTable = table;
        importCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*=== Reference Code For Importing File   ===*/
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Import A File");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();

                    importCsv(selectedFile, actionTable);

                }
            }
        });
        xmlExportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a location for XML File");
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    exportXML(actionTable, selectedFile.getAbsolutePath());
                    System.out.println("Exported!");

                }

            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long time = System.nanoTime();
              Object record[] = actionTable.getRecord(Integer.parseInt(findIdTextField.getText().trim()));
                if(record != null){
                    System.out.println("Record Found");
                    durationLabel.setText("Duration: " + (System.nanoTime() - time));
                    actionTable.showRecord(Integer.parseInt(findIdTextField.getText().trim()));
                }
                else{
                    durationLabel.setText("Data Not Found!");
                }
            }
        });
    }

    public void display(){
        frame.add(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setVisible(true);

    }

    private void importCsv(File selectedFile, DataTable table){
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(selectedFile.getAbsolutePath())));
            String line;
            while ((line = br.readLine()) != null) {
                Object []record = line.split(",");
                table.insert(record);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



    public void exportXML(DataTable table, String filepath){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filepath + ".xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.println("<table>");
        String []schema = table.getSchema();
        for (int i = 1; i < table.getNumberOfRecords()-1; i++) {
            Object[] record = table.getRecord(i);
            writer.println("<record>");
            for (int j = 0; j <  schema.length; j++) {
                writer.println("<" +schema[j].toLowerCase().trim() + ">");
                writer.println(record[j]);
                writer.println("</" +schema[j].toLowerCase().trim() + ">");
            }
            writer.println("</record>");
        }
        writer.println("</table>");
        writer.close();
    }
}
