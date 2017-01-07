package com.company;

/**
 * Created by Coder on 12/9/2015.
 */
public class DataNode {
    int id;
    Object [] record;
    DataNode(int id, Object record[]){
        this.id = id;
        this.record = record;
    }

    public Object[] getData(){
        Object temp[]  = new Object[record.length+1];
        temp[0] = id;
        for (int i = 0; i < record.length; i++) {
            temp[i+1] = record[i];
        }
        return  temp;
    }


}
