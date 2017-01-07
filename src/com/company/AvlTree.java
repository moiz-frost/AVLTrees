/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author HUZAIFA MANDVIWALA
 */
public class AvlTree {
    public AvlNode root;
    private static int ALLOWED_IMBALANCE = 1;
    public int recordCount = 0;
    String columnNames[];
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

    AvlTree(String columnNames[]){
        this.columnNames = columnNames;

    }


    //static int debug = 0;
    void preOrder(AvlNode root)
    {
        if(root != null)
        {
            System.out.print(root.element + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    void InOrder(AvlNode root)
    {
        if(root != null)
        {
            preOrder(root.left);
            System.out.print(root.element + " ");
            preOrder(root.right);
        }
    }
    void postOrder(AvlNode root)
    {
        if(root != null)
        {
            preOrder(root.left);
            preOrder(root.right);
            System.out.print(root.element + " ");
        }
    }

    private AvlNode balance( AvlNode t ){
        if( t == null )
            return t;
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
        {
            if( height( t.left.left ) >= height( t.left.right ) )
            {
                t = rotateWithLeftChild( t );
            }
            else{
                t = doubleWithLeftChild( t );
            }
        }
        else{
            if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            {
                if( height( t.right.right ) >= height( t.right.left ) )
                {
                    t = rotateWithRightChild( t );
                }
                else{
                    t = doubleWithRightChild( t );
                }
            }
        }
        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }


    private AvlNode rotateWithLeftChild( AvlNode k2 ){
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }


    private AvlNode rotateWithRightChild( AvlNode k2 ){
        AvlNode k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max( height( k2.right ), height( k2.left ) ) + 1;
        k1.height = Math.max( height( k1.right ), k2.height ) + 1;
        return k1;
    }


    /**
     *Double rotate binary tree node: first left child
     *with its right child; then node k3 with new left child.
     *For AVL trees, this is 0a double rotation for case 2.
     *Update heights, then return new root.
     */
    private AvlNode doubleWithLeftChild( AvlNode k3 ){
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }


    private AvlNode doubleWithRightChild( AvlNode k3 ){
        k3.right = rotateWithLeftChild( k3.right );
        return rotateWithRightChild( k3 );
    }


    public void insert(DataNode x){
        root = insertIn(x, root);
        System.out.println("Node added successfully");
        recordCount++;
    }

    AvlNode insertIn( DataNode x, AvlNode t){
        if( t == null )
            return new AvlNode( x, null, null );
        //int compareResult = x.compareTo( t.element );
        if( x.id < t.element ){
            t.left = insertIn(x, t.left);
        }
        else if( x.id > t.element ){
            t.right = insertIn(x, t.right);
        }
        else{
            ;// Duplicate; do nothing
        }

        return balance( t );
    }
    public AvlNode findNode(int key)
    {

        AvlNode focusNode = root;

        while (focusNode.element   != key)
        {

            if (key < focusNode.element)
            {

                focusNode = focusNode.left;
            } else
            {
// Shift the focus Node to the right child
                focusNode = focusNode.right;
            }
// The node wasn't found
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }

    public Object [] getRecord(int key){
        return find(key).getData();
    }

    public DataNode find(int key){
        AvlNode temp = root;
        while (temp.element != key){

            if(key >= temp.element){
                temp = temp.right;
            }else{
                temp = temp.left;
            }

            if(temp == null){
                System.out.println("Not Found!");
                return null;
            }
        }
        return temp.data;
    }

    public AvlNode findMin( AvlNode t ){
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }
    public AvlNode findMax( AvlNode t ){
        if( t == null )
            return null;
        else if( t.right == null )
            return t;
        return findMax( t.right );
    }
    public int heightoftree(AvlNode root)
    {
        if(root == null) return -1;
        else
            return 1 + Math.max( height(root.left), height(root.right));
    }
    AvlNode remove( int x, AvlNode t ){
        if( t == null ){
            return t; // Item not found; do nothing
        }

        //int compareResult = x.compareTo( t.element );

        if( x < t.element ){
            t.left = remove( x, t.left );
        }
        else if( x > t.element ){
            t.right = remove( x, t.right );
        }
        else if( t.left != null && t.right != null ){
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        } else{
            t = ( t.left != null ) ? t.left : t.right;
        }
        return balance( t );
    }
}

