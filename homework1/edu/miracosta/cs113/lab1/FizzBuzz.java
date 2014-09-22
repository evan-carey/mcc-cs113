package edu.miracosta.cs113.lab1;
/**
 * Assignment: Week 1 Lab 1
 * File: FizzBuzz.java
 * @author Evan Carey
 *
 * Problem Statement: Write a program that prints numbers
 * 1 to 100. If the number is a multiple of 3, print "Fizz".
 * If it's a multiple of 5, print "Buzz". If it's a multiple
 * of both, print "FizzBuzz".
 */

public class FizzBuzz {
    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.print("Fizz");
            }
            if (i % 5 == 0) {
                System.out.print("Buzz");
            }
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}