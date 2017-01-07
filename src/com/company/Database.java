package com.company;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Coder on 12/9/2015.
 */
public class Database {
    private ArrayList<DataTable> tables = new ArrayList<DataTable>();
    private int totalTables = 0;

    public void  addTables(DataTable table){
        tables.add(table);
        totalTables++;
    }
    public ArrayList<DataTable> getTablesList(){
        return tables;
    }

    public int getTotalTables(){
        return totalTables;
    }
}
