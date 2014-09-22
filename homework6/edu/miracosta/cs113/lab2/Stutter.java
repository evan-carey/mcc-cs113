package edu.miracosta.cs113.lab2;
/**
 * Group Project: Section 5.2 Pg. 252, Programming Exercise 2
 *              
 * File: Stutter.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Write a recursive method stutter that returns
 * a string with each character in its argument repeated.
 */
public class Stutter {

    /**
     * Test the stutter method with the string "hello"
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.print(stutter("hello"));

    }
    
    /**
     * Recursive method that returns a string with each
     * character in its argument repeated.
     * 
     * @param str The string to be stuttered
     * @return the stuttered string
     */
    public static String stutter(String str) {
        if (str == null || str.length() < 1) {
            // Base Case
            return "";
        } else {
            // Recursive Case
            return str.charAt(0) + "" + str.charAt(0) + "" 
                    + stutter(str.substring(1));
        }
    }
}