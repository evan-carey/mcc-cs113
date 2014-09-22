package edu.miracosta.cs113.hw1;
/**
 * Assignment: Week 12 HW 1
 *             Programming Exercises 1 & 2, pg. 531
 *              
 * File: SkipList.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement:  Complete the skip-list class found in the book.
 * To do this complete the programming exercises on pg. 531.
 *   a. Complete the code for the add method.
 *   b. To remove a node from a skip-list, you need to update all references 
 *      to the node being deleted to reference its successors.  Code the remove method.
 */

import java.util.Arrays;
import java.util.Random;

/**
 * A Skip List is an alternative to a binary tree that provides for
 * approximately logorithmic searching, insertion, and deletion.
 * Skip Lists were developed by William Pugh and first described in
 * "Skip Lists: A Probabilistic Alternative to Balanced Treees",
 * CACM 13:8 (June 1990) pp 668-676
 * @author Koffman & Wolfgang
 */
public class SkipList<E extends Comparable<E>>  implements SearchTree<E> {

    /** Static class to contain the data and the links */
    static class SLNode<E> {

        SLNode<E>[] links;
        E data;

        /** Create a node of level n */
        @SuppressWarnings("unchecked")
        SLNode(int n, E data) {
            links = (SLNode<E>[]) new SLNode[n];
            this.data = data;
        }
    }
    /** Maximum level */
    int maxLevel = 2;
    /** Nominal maximum capacity */
    int maxCap = computeMaxCap(maxLevel);
    /** Natural Log of 2 */
    static final double LOG2 = Math.log(2.0);
    /** A random number generator */
    final static Random rand = new Random();
    /** The current size of the skipList */
    int size;

    /**
     * Method to compute the maximum capacity, given the maximum
     * level. It computes Math.pow(2, maxLevel) - 1, using shift.
     * @return Math.pow(2, maxLevel+1) - 1;
     */
    private static int computeMaxCap(int maxLevel) {
        return ~(~0 << maxLevel);
    }

    /**
     * Method to generate a lograthmic distributed integer between
     * 1 and maxLevel.  I.E. half of the values returned are 1, 1/4
     * are 2, 1/8 3, 1/16 4, etc.
     * @return a random lograthmic distributed int between 1 and maxLevel
     */
    private int logRandom() {
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }
    /** The head node is a dummy node, it contains no data */
    SLNode<E> head = new SLNode<E>(maxLevel, null);

    /*<listing chapter="9" number="7">*/
    @SuppressWarnings("unchecked")
    /**
     * Search for an item in the list
     * @param item The item being sought
     * @return A SLNode array which references the nodes
     *         preceeding the sought item at each level.
     */
    private SLNode<E>[] search(E item) {
        SLNode<E>[] result = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null && current.links[i].data.compareTo(item) < 0) {
                current = current.links[i];
            }
            result[i] = current;
        }
        return result;
    }

    /**
     * Find an object in the skip list
     * @param target The item being sought
     * @return A reference to the object in the skip list that compares
     *         equal as determined by compareTo to the target. If not
     *         found null is returned.
     */
    @Override
    public E find(E target) {
        SLNode<E>[] update = search(target);
        if (update[0].links[0] != null && update[0].links[0].data.compareTo(target) == 0) {
            return update[0].links[0].data;
        } else {
            return null;
        }
    }
    
// Insert solution to programming exercise 1, section 6, chapter 9 here

    /**
     * Inserts item where it belongs in the tree.
     * @param item The item to be inserted
     * @return True if item is inserted; false if it isn't (already in tree).
     */
    @Override
    public boolean add(E item) {
        SLNode<E>[] update = search(item);
        
        // check if item is already in list
        if (update[0].links[0] != null && update[0].links[0].data.compareTo(item) == 0) {
            return false;
        }
        
        size++;
        // increase the height if needed
        if (size > maxCap) {
            maxLevel++;
            maxCap = computeMaxCap(maxLevel);
            head.links = Arrays.copyOf(head.links, maxLevel);
            update = Arrays.copyOf(update, maxLevel);
            update[maxLevel - 1] = head;
        }
        // Create new node for the item
        SLNode<E> itemNode = new SLNode<E>(logRandom(), item);
        
        // Insert the node into the list
        for (int i = 0; i < itemNode.links.length; i++) {
            // Set the new node's references
            itemNode.links[i] = update[i].links[i];
            // Set the old references to the new node
            update[i].links[i] = itemNode;
        }
        return true;
    }

    /**
     * Determine if an item is in the tree
     * @param target Item being sought in tree
     * @return true If the item is in the tree, false otherwise
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

// Insert solution to programming exercise 2, section 6, chapter 9 here
    
    @SuppressWarnings("unchecked")
    /**
     * Find and delete target from tree.
     * @param target The item to delete.
     * @return The item if it is present, null otherwise
     */
    @Override
    public E delete(E target) {
        
        if (!contains(target)) {
            return null;
        }
        
        SLNode<E> targetNode = head;
        SLNode<E>[] update = (SLNode<E>[]) new SLNode[maxLevel];
        
        
        for (int i = targetNode.links.length - 1; i >= 0; i--) {
            while (targetNode.links[i] != null && targetNode.links[i].data.compareTo(target) < 0) {
                targetNode = targetNode.links[i];
            }
            update[i] = targetNode;
        }
        targetNode = targetNode.links[0];
        
        for (int i = 0; i < update.length; i++) {
            if (update[i].links[i] != targetNode) {
                break;
            }
            update[i].links[i] = targetNode.links[i];
        }
        size--;
        
        return target;
    }
    /**
     * Removes target from tree.
     * @post target is not in the tree
     * @param target Item to be removed
     * @return true if the object was in the tree, false otherwise
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /** Remove all data from the tree */
    public void clear() {
        for (int i = 0; i < maxLevel; i++) {
            head.links[i] = null;
        }
        size = 0;
    }
    
    
    /**
     * Returns a string representation of the data in the skip list
     * @return The string representation
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SLNode<E> x = head.links[0];
        while (x != null) {
            sb.append(x.data);
            x = x.links[0];
            if (x != null) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    /**
     * Main method to test the add and remove methods
     * @param args
     */
    public static void main(String[] args) {
        SkipList<Integer> sl = new SkipList<Integer>();
        
        sl.add(3);
        sl.add(30);
        sl.add(-1);
        sl.add(13);
        sl.add(19);
        sl.add(1);
        sl.add(5);
        sl.add(8);
        sl.add(15);
        sl.add(20);
        sl.add(17);
        sl.add(2);
        sl.add(5);
        sl.add(11);
        sl.add(13);
        sl.add(4);
        sl.add(28);
        sl.add(0);
        
        System.out.println("After insertion: " + sl);
        
        sl.remove(5);
        sl.remove(17);
        sl.remove(28);
        sl.remove(1);
        sl.remove(2);
        
        System.out.println("After removal:   " + sl);
    }
}
/*</listing>*/
