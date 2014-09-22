package edu.miracosta.cs113.hw2;
/**
 * Assignment: Week 8 HW 2
 *             Programming Project 6, pg. 357
 *              
 * File: BinaryTreeTraversal.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: In a breadth-first traversal of a binary tree, 
 * the nodes are visited in an order prescribed by their level. First visit
 * the node at level 1, the root node. Then visit the nodes at level 2, in
 * left-to-right order, and so on. You can use a queue to implement a breadth-first 
 * traversal of a binary tree.
 * 
 * Algorithm:
 *  1. Insert the root node in the queue.
 *  2. While the queue is not empty
 *  3.      Remove a node from the queue and visit it.
 *  4.      Place references to its left and right subtrees in the queue.
 *  
 *  Code this algorithm and test it on several binary trees.
 */
import java.util.LinkedList;
import java.util.Queue;

import KW.CH06.BinarySearchTree;
import KW.CH06.BinaryTree;

public class BinaryTreeTraversal<E> {
        
    /**
     * Traverse a binary tree using a breadth-first approach.
     * @param root The root of the binary tree to be traversed
     * @return A string representation of the data visited, using toString()
     */
    public String traverse(BinaryTree<E> root) {
        
        if (root.getData() == null) {
            return null;
        }
        
        Queue<BinaryTree<E>> queue = new LinkedList<BinaryTree<E>>();
        int level = 0;
        int nodesInThisLevel = 1;
        int nodesInNextLevel = 0;
        StringBuilder sb = new StringBuilder();
        
        // 1. Insert the root node in the queue.
        queue.add(root);
        // 2. While the queue is not empty
        while (!queue.isEmpty()) {
            
            // 3. Remove a node from the queue...
            BinaryTree<E> node = queue.remove();
            nodesInThisLevel--;
            
            for (int i = 0; i <= level; i++) {
                sb.append("  ");    // Indent with each level
            }
            
            if (node != null) {
                // 3. ...and visit it
                sb.append(node.getData().toString() + "\n");
                
                // 4. Place references to its left and right subtrees in the queue.
                if (node.getLeftSubtree() != null) {
                    queue.add(node.getLeftSubtree());
                    nodesInNextLevel++;
                }
                if (node.getRightSubtree() != null) {
                    queue.add(node.getRightSubtree());                    
                    nodesInNextLevel++;
                } 
            }
            if (nodesInThisLevel == 0) {
                level++;
                nodesInThisLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Test the breadth-first traversal method on several binary trees.
     * @param args
     */
    public static void main(String[] args) {
        
     // String test
        String ll = "LeftLeft";
        String lr = "LeftRight";
        String rl = "RightLeft";
        String rr = "RightRight";
        String l = "Left";
        String r = "Right";
        String root = "Root";
        // Level 2
        BinaryTree<String> llTree = new BinaryTree<String>(ll, null, null);
        BinaryTree<String> lrTree = new BinaryTree<String>(lr, null, null);
        BinaryTree<String> rlTree = new BinaryTree<String>(rl, null, null);
        BinaryTree<String> rrTree = new BinaryTree<String>(rr, null, null);
        // Level 1
        BinaryTree<String> lTree = new BinaryTree<String>(l, llTree, lrTree);
        BinaryTree<String> rTree = new BinaryTree<String>(r, rlTree, rrTree);
        // Root
        BinaryTree<String> rootTree = new BinaryTree<String>(root, lTree, rTree);
        
        BinaryTreeTraversal<String> strTraversal = new BinaryTreeTraversal<String>();
        System.out.println("String Test:\n" + strTraversal.traverse(rootTree));
        
     // Stark test
        String[] strArray = {"Eddard", "Catelyn", "Jon", "Robb", "Sansa", "Arya", "Brandon", "Rickon"};
        
        System.out.println("Stark Test:");
        BinarySearchTree<String> strTree = new BinarySearchTree<String>();
        for (String stark : strArray) {
            strTree.add(stark);
        }
        System.out.println(strTraversal.traverse(strTree));
        
     // Integer test
        System.out.println("Integer Test:");
        BinarySearchTree<Integer> intTree = new BinarySearchTree<Integer>();
        intTree.add(20); // root
        for (Integer i = 1; i < 13; i++) {
                intTree.add((int)(Math.random() * 40 + 1));
        }
        BinaryTreeTraversal<Integer> iTrav = new BinaryTreeTraversal<Integer>();
        System.out.println(iTrav.traverse(intTree));
    }
}