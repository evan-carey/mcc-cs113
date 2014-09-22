package edu.miracosta.cs113.hw1;
/**
 * Assignment: Week 7 HW 1
 *              
 * File: SubsetOfSum.java
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

public class SubsetOfSum {

    // Data Fields
    /** The set of entered numbers */
    private static ArrayList<Integer> numbers;
    private static ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
    /** The desired sum */
    private static int sum;
    /** Whether a subset was found */
    private static boolean found = false;

    /**
     * Prompt user for list of integers and store them in numbers ArrayList.
     */
    public static void getNumbers() {

        numbers = new ArrayList<Integer>();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Input list of integers. Press 'Enter' when finished: ");
        String numStr = keyboard.nextLine();

        StringTokenizer strToke = new StringTokenizer(numStr);

        while (strToke.hasMoreTokens()) {
            String token = strToke.nextToken();

            // Parse integers from user input and add to numbers
            try {
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
                //If user inputs something other than an int, they are prompted to try again
                System.out.println("Error: Invalid entry. Please input an integer.");
                keyboard.nextLine();
            }
        }
    }
    
    public static void addSubset(ArrayList<Integer> newSet) {
        for (ArrayList<Integer> subset : allSubsets) {
            if (newSet.equals(subset)) {
                return;
            }
        }
        allSubsets.add(newSet);
    }
    
    /**
     * Iterate through each subset of a given set and output it if the sum of
     * its elements equals the target sum. If no such subsets are found, output
     * a message indicating so.
     * 
     * @param set
     *            The given set of integers
     */
    public static void findSubsets(ArrayList<Integer> set) {

        for (long i = 0; i < (1 << set.size()) /*- 1*/; i++) {
            
            ArrayList<Integer> currentSet = new ArrayList<Integer>();
            
            for (int j = 0; j < set.size(); j++) {
                if ((i >> j) % 2 == 1) {
                    currentSet.add(set.get(j));
                }
            }
            printSubset(currentSet);
            
            if (sum == sumOfSet(currentSet)) {
                printSubset(currentSet);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No subsets found.");
        }
    }

    /**
     * Output a given set in {a, b, c, ...} format.
     * 
     * @param currentSet
     *            The set to be output
     */
    private static void printSubset(ArrayList<Integer> currentSet) {

        if (currentSet.size() == 1) {
            System.out.println("{" + currentSet.get(0) + "}");
            return;
        }

        for (int i = 0; i < currentSet.size(); i++) {
            if (i == 0) {
                System.out.print("{" + currentSet.get(i) + ", ");
            } else if (i == currentSet.size() - 1) {
                System.out.println(currentSet.get(i) + "}");
            } else {
                System.out.print(currentSet.get(i) + ", ");
            }
        }
    }
    
    private static void printSubsets(ArrayList<ArrayList<Integer>> sets) {

        System.out.println("\nSubsets that sum to " + sum + ":");
        
        for (ArrayList<Integer> set : sets) {
            System.out.println(set.toString());
        }
    }

    /**
     * Return the sum of all elements in a given set.
     * 
     * @param set
     *            The set whose elements are to be summed
     * @return The sum
     */
    public static int sumOfSet(ArrayList<Integer> set) {
        
        int theSum = 0;
        for (int i : set) {
            theSum += i;
        }
        return theSum;
    }

    /**
     * Call methods to get list of numbers and desired sum from user, then call
     * method to find all subsets of the list whose elements total the sum.
     * 
     * @param args
     */
    public static void main(String[] args) {
        getNumbers();
        getSum();
        
        findSubsets(numbers);
        if (found) {
            printSubsets(allSubsets);
        } else {
            System.out.println("\nNo subsets sum to " + sum);
        }
    }
}