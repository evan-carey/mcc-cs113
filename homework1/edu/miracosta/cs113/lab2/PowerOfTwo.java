package edu.miracosta.cs113.lab2;

/**
 * Assignment: Week 1 Lab 2 
 * File: PowerOfTwo.java
 * @author Evan Carey
 * 
 * Problem Statement: Write a program that determines if a number is a
 * power of two.
 */
import java.util.Scanner;

public class PowerOfTwo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int num = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        num = keyboard.nextInt();

        if (num < 1) {
            System.out.println(num + " is not a positive integer.");
        } else if (powerOfTwo(num)) {
            System.out.println(num + " is a power of two! Hooray!");
        } else {
            System.out.println(num + " is not a power of two :(");
        }
    }

    /**
     * @param num
     * @return true if num is a power of two, false otherwise
     */
    public static boolean powerOfTwo(int num) {
        if (num == 1) {
            return true;
        } else if (num % 2 == 0) {
            return powerOfTwo(num / 2);
        }
        return false;
    }
}