/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author Dayem Siddiqui
 */
class AvlNode{
    // Constructors
    AvlNode( DataNode data ){
        this( data, null, null );
    }

    AvlNode( DataNode data, AvlNode lt, AvlNode rt ){
        element = data.id;
        left = lt;
        right = rt;
        height = 0;
        this.data = data;
    }

    public DataNode data;
    public int element; // The data in the node
    AvlNode left; // Left child
    AvlNode right; // Right child
    int height; // Height
}
