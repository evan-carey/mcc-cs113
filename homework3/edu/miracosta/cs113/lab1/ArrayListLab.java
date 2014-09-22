package edu.miracosta.cs113.lab1;

/**
 * Assignment: Week 3 Lab 1 
 *          Programming Exercises 1 & 2 Section 2.1 pg. 69
 * File: ArrayListLab.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write static methods replace()
 *         and delete().
 */

import java.util.ArrayList;

public class ArrayListLab {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("Evan");
        aList.add("Carey");
        aList.add("Evan");
        aList.add("Colin");
        aList.add("Trixie");
        aList.add("Colin");
        
        System.out.println("Original list:");
        for (String foo : aList) {
            System.out.print(foo + " ");
        }
        System.out.println();
        
        
        replace(aList, "Evan", "John");
        System.out.println("\nAfter replacing every instance of \"Evan\" with \"John\":");
        for (String foo : aList) {
            System.out.print(foo + " ");
        }
        System.out.println();
        
        delete(aList, "Colin");
        System.out.println("\nAfter removing the first instance of \"Colin\":");
        for (String foo : aList) {
            System.out.print(foo + " ");
        } 
    }
    
    // Programming Exercise 1
    /** Replaces each occurrence of oldItem in aList with newItem.
     * @param aList The ArrayList being searched
     * @param oldItem The String being replaced
     * @param newItem The replacing String
     */
    public static void replace(ArrayList<String> aList, String oldItem, String newItem) {
        for (String str : aList) {
            if (str.equals(oldItem)) {
                aList.set(aList.indexOf(str), newItem);
            }
        }
    }

    // Programming Exercise 2
    /** Deletes the first occurrence of target in aList
     * @param aList The ArrayList being searched
     * @param target The String being deleted
     */
    public static void delete(ArrayList<String> aList, String target) {
        if (!aList.remove(target)) {
            System.out.println("\"" + target + "\" not found.");
        }
    }
}