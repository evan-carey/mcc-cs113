package edu.miracosta.cs113.lab1;

/**
 * Assignment: Week 5 Lab 1
 * File: ReverseString.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Use an ArrayStack to reverse a string
 */
import java.util.Scanner;

import edu.miracosta.cs113.hw5_1.ArrayStack;

public class ReverseString {

    /**
     * Prompt user to enter a string and output the reverse
     * @param args
     */
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter a string:");
        String orig = keyboard.nextLine();

        System.out.println("\nThe reverse is:\n" + reverse(orig));
    }

    /**
     * Reverse a string using an ArrayStack.
     * @param orig
     *            The string to be reversed
     * @return The reversed string as a StringBuilder object
     */
    public static StringBuilder reverse(String orig) {
        
        // push characters from orig to stack
        ArrayStack<Character> stack = new ArrayStack<Character>();
        for (int i = 0; i < orig.length(); i++) {
            stack.push(orig.charAt(i));
        }
        
        // pop characters from stack and append to StringBuilder
        StringBuilder rev = new StringBuilder();
        while (!stack.empty()) {
            rev.append(stack.pop());
        }
        return rev;
    }
}