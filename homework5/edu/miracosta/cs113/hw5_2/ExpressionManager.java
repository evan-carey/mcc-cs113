package edu.miracosta.cs113.hw5_2;

/**
 * Assignment: Week 5 Homework 2 (Chapter 4, Programming Project 4)
 * File: ExpressionManager.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Develop an Expression Manager that can
 *         do the following operations:
 *         - Balanced Symbols Check
 *         - Infix to Postfix Conversion
 *         - Postfix to Infix Conversion
 *         - Evaluating a Postfix Expression
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpressionManager {

    /**Presents the user with a menu of the operations.
     * @param args
     */
    public static void main(String[] args) {
        
        boolean done = false;
        
        // User menu
        while (!done) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Select an operation (Enter 1,2,3,4,5)"
                    + "\n\t1. Balanced Symbols Check"
                    + "\n\t2. Infix to Postfix Conversion"
                    + "\n\t3. Postfix to Infix Conversion"
                    + "\n\t4. Evaluating a Postfix Expression"
                    + "\n\t5. Quit");
            try {
                // get user's choice
                int choice = keyboard.nextInt();
                
                // call appropriate method based on choice
                switch (choice) {
                case 1: // Balanced Symbols Check
                    checkParens();
                    break;
                case 2: // Infix to Postfix Conversion
                    infixToPostfix();
                    break;
                case 3: // Postfix to Infix Conversion
                    postfixToInfix();
                    break;
                case 4: // Evaluating a Postfix Expression
                    evalPostfix();
                    break;
                case 5: // Quit
                    done = true;
                    break;
                default:
                    System.out.println("Innvalid selection, please try again.");
                    break;
                }
            } catch (InputMismatchException e) {    // user did not input an integer
                System.out.println("Innvalid selection, please try again.");
            }
            System.out.println();
        }
    }

    /**
     * Determine whether a user-defined expression has balanced
     * parentheses and output result.
     */
    private static void checkParens() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Expression to be checked: ");
        String input = keyboard.nextLine().trim();
        
        // check balance and output result
        if (ParenChecker.isBalanced(input)) {
            System.out.println("Parentheses are balanced.");
        } else {
            System.out.println("Parentheses are not balanced.");
        }   
    }
    
    /**
     * Convert a user-defined infix expression to postfix form and output result.
     * Display message if infix expression is not valid (e.g. contains unsupported operators).
     * Display message if expression fails parentheses check.
     */
    private static void infixToPostfix() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Infix Expression: ");
        String input = keyboard.nextLine().trim();
        
        // if parentheses are balance, convert expression to postfix form and output it
        if (ParenChecker.isBalanced(input)) {
            try {
                System.out.println(input + " = " + InfixToPostfix.convert(input));
            } catch (SyntaxErrorException e) {  // thrown by InfixToPostfix.convert()
                System.out.println("Not a valid infix expression. (" + e.getMessage() + ")");
            }
        } else {
            System.out.println("Parentheses are not balanced.");
        }   
    }
    
    /**
     * Convert a user-defined postfix expression to infix form and output result.
     * Display message if postfix expression is not valid (e.g. contains unsupported operators).
     */
    private static void postfixToInfix() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Postfix Expression: ");
        String input = keyboard.nextLine().trim();
        
        try {
            System.out.println(input + " = " + PostfixToInfix.convert(input));
        } catch (SyntaxErrorException e) {  // thrown by PostfixToInfix.convert()
            System.out.println("Not a valid postfix expression. (" + e.getMessage() + ")");
        }
    }
    
    /**
     * Evaluate a user-defined postfix expression and output result.
     * Display message if postfix expression is not valid (e.g. contains unsupported operators).
     */
    private static void evalPostfix() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Postfix Expression: ");
        String input = keyboard.nextLine().trim();
        
        try {
            System.out.println(input + " = " + RPNCalculator.calcRPN(input));
        } catch (SyntaxErrorException e) {  // thrown by RPNCalculator.calcRPN()
            System.out.println("Not a valid postfix expression. (" + e.getMessage() + ")");
        }
    }
}