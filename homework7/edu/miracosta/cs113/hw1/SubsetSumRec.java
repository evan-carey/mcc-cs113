package edu.miracosta.cs113.hw1;

/**
 * Assignment: Week 7 HW 1
 *              
 * File: SubsetSumRec.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Write a program that will read a list of numbers 
 * and a desired sum, then determine the subset of numbers in the list 
 * that yield that sum if such a subset exists.
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SubsetSumRec {

    // Data Fields
    /** The set of entered numbers */
    private static ArrayList<Integer> numbers;
    /** The desired sum */
    private static int sum;
    /** The list of all subsets whose elements sum to desired sum */
    private static ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
    /** Whether a subset was found */
    private static boolean found = false;

    /**
     * Call methods to get list of numbers and desired sum from user, then call
     * methods to find and print all subsets of the list whose elements total the sum.
     * 
     * @param args
     */
    public static void main(String[] args) {
        getNumbers();
        getSum();

        findSubsets(numbers, 0);
        if (found) {
            printSubsets(allSubsets);
        } else {
            System.out.println("\nNo subsets sum to " + sum);
        }
    }

    /**
     * Prompt user for list of integers and store them in numbers ArrayList.
     */
    public static void getNumbers() {

        numbers = new ArrayList<Integer>();
        Scanner keyboard = new Scanner(System.in);

        System.out
                .println("Input list of integers. Press 'Enter' when finished: ");
        String numStr = keyboard.nextLine();

        StringTokenizer strToke = new StringTokenizer(numStr);

        while (strToke.hasMoreTokens()) {
            String token = strToke.nextToken();

            try { // Parse integers from user input and add to numbers
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                // If token is not an integer, it is not added to numbers
            }
        }
    }

    /**
     * Prompt user for desired sum and store it in sum.
     */
    public static void getSum() {

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Input desired sum: ");
                sum = keyboard.nextInt();
                break;
            } catch (InputMismatchException e) {
                // If user inputs something other than an int, they are prompted
                // to try again
                System.out.println("Error: Invalid entry. Please input an integer.");
                keyboard.nextLine();
            }
        }
    }

    /**
     * Recursively search through every subset of a given set. If the elements
     * of a subset sum to desired sum, it is added to the allSubsets list.
     * 
     * @param set
     *            The set to be searched
     * @param i
     *            The index of the current element being examined
     */
    public static void findSubsets(ArrayList<Integer> set, int i) {

        if (sum == sumOfSet(set)) { // Base Case: success
            addSubset(set);
            found = true;
            return;
        } else if (set.isEmpty() || i == set.size()) { // Base Case: failure
            return;
        } else { // Recursive Cases

            // Create a new subset without element at index i
            ArrayList<Integer> newSet = new ArrayList<Integer>(set);
            newSet.remove(i);

            findSubsets(set, i + 1); // test subsets with element i included
            findSubsets(newSet, i); // test subsets without element i
        }
    }

    /**
     * Store the current subset into allSubsets. If allSubsets already contains
     * current subset, it is not added.
     * 
     * @param newSet
     *            The subset to be added.
     */
    public static void addSubset(ArrayList<Integer> newSet) {
        for (ArrayList<Integer> subset : allSubsets) {
            if (newSet.equals(subset)) {
                return;
            }
        }
        allSubsets.add(newSet);
    }

    /**
     * Return the sum of all elements in a given set.
     * 
     * @param set
     *            The set whose elements are to be summed
     * @return The sum of the set
     */
    public static int sumOfSet(ArrayList<Integer> set) {

        int theSum = 0;
        for (int i : set) {
            theSum += i;
        }
        return theSum;
    }

    /**
     * Output all subsets in a given list.
     * 
     * @param sets
     *            The list of subsets
     */
    private static void printSubsets(ArrayList<ArrayList<Integer>> sets) {

        System.out.println("\nSubsets that sum to " + sum + ":");

        for (ArrayList<Integer> set : sets) {
            System.out.println(set.toString()); // print in [a, b, c, ...] format
        }
    }
}
