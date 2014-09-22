package edu.miracosta.cs113.hw1;
/**
 * Assignment: Week 11 HW 1
 *             Programming Project 3, pg. 468
 *              
 * File: ShakerSort.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Implement the shaker sort algorithm.
 *  The shaker sort is an adaptation of the bubble sort that alternates the direction
 * in which the array elements are scanned during each pass. The first pass starts its scan
 * with the first element, moving the larger element in each pair down the array. The
 * second pass starts the scan with the next-to-last element, moving the smaller element
 * in each pair up the array, and so on.
 */
import java.util.Arrays;

public class ShakerSort {
    
    /**
     * Sort an array of type T using the shaker sort algorithm.
     * pre: table contains Comparable objects.
     * post: table is sorted.
     * @param table The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] table) {
        
        int leftBound = -1;
        int rightBound = table.length - 2;
        boolean swap = false;
        
        do {
            swap = false;
            leftBound++;
            // left-to-right pass
            for (int i = leftBound; i <= rightBound; i++) {
                if (table[i].compareTo(table[i + 1]) > 0) {
                    T temp = table[i];
                    table[i] = table[i + 1];
                    table[i + 1] = temp;
                    swap = true;
                }
            }
            // exit loop early if no swaps were performed
            if (!swap) {
                break;
            }
            
            swap = false;
            rightBound--;
            // right-to-left pass
            for (int i = rightBound; i >= leftBound; i--) {
                if (table[i].compareTo(table[i + 1]) > 0) {
                    T temp = table[i];
                    table[i] = table[i + 1];
                    table[i + 1] = temp;
                    swap = true;
                }
            }
            
        } while (swap);
    }
    
    /**
     * Test the sort method with an array of integers.
     * @param args Not used
     */
    public static void main(String[] args) {
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100 + 1);
        }
        System.out.println("Unsorted: " + Arrays.toString(a));
        ShakerSort.sort(a);
        System.out.println("Sorted:   " + Arrays.toString(a));
    }
}