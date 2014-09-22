package edu.miracosta.cs113.hw2;

/**
 * Assignment: Week 6 HW 2
 *              
 * File: ChangeDispenser.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Write a recursive method that will dispense change for a
 * given amount of money. The method will display all possible combinations
 * that equal the desired amount.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeDispenser {

    /** Holds all combinations of change as integer arrays */
    private static ArrayList<int[]> combos = new ArrayList<int[]>();

    /**
     * Prompt user to input an amount in cents to be broken down. If input is
     * negative or not an integer, an error message is displayed and they are
     * prompted to reenter.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int amount;
        for (;;) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter amount in cents: ");
            try {
                amount = keyboard.nextInt();
                if (amount < 0) {
                    throw new InputMismatchException();
                }
                makeChange(amount, 0, 0, 0, amount);
                break; // exit infinite loop
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid entry, please try again.");
            } catch (StackOverflowError e) {
                System.out.println("\nERROR: Stack Overflow.");
                System.exit(0);
            }
        }
        // Output combinations
        System.out.println("\nAll possible combinations for " + amount
                + " cents:");
        for (int[] a : combos) {
            System.out.println(a[0] + " Q\t" + a[1] + " D\t" + a[2] + " N\t"
                    + a[3] + " P");
        }
    }

    /**
     * Displays all combinations of quarters, dimes, nickels, and pennies that
     * equal the desired amount.
     * 
     * @param total
     *            The total amount in cents
     * @param q
     *            The current number of quarters
     * @param d
     *            The current number of dimes
     * @param n
     *            The current number of nickels
     * @param p
     *            The current number of pennies
     */
    public static void makeChange(int total, int q, int d, int n, int p) {

        // Base Case
        if (q * 25 + d * 10 + n * 5 + p > total) {
            return;
        }

        storeCombination(q, d, n, p);

        // Recursive Cases
        if (p >= 5) {
            makeChange(total, q, d, n + 1, p - 5);
        }
        if (p >= 10) {
            makeChange(total, q, d + 1, n, p - 10);
        }
        if (p >= 25) {
            makeChange(total, q + 1, d, n, p - 25);
        }
    }

    /**
     * Store the current combination of change into combos. If combos already
     * contains the current combination, it is not added.
     * 
     * @param q
     *            The number of quarters
     * @param d
     *            The number of dimes
     * @param n
     *            The number of nickels
     * @param p
     *            The number of pennies
     */
    public static void storeCombination(int q, int d, int n, int p) {
        int[] newCombo = { q, d, n, p };

        for (int[] combo : combos) {
            if (Arrays.equals(newCombo, combo)) {
                return; // combination already stored
            }
        }
        combos.add(newCombo);
    }
}