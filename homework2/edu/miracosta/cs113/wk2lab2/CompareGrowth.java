package edu.miracosta.cs113.wk2lab2;

/**
 * Assignment: Week 2 Lab 2 
 * File: CompareGrowth.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write a program that compares the values of y1 and y2
 *         for values of n from 0 up to 100 in increments of 10.
 * 
 *          y1 = 100 * n + 10
 *          y2 = 5 * n * n + 2
 */

public class CompareGrowth {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        for (int n = 0; n <= 100; n += 10) {
            int y1 = 100 * n + 10;
            int y2 = 5 * n * n + 2;
            System.out.println("n = " + n + "    y1 = " + y1 + "    y2 = "+ y2);
        }
    }
}