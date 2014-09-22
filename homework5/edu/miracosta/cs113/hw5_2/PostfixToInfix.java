package edu.miracosta.cs113.hw5_2;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class to convert a postfix expression to infix form.
 * @author Evan Carey
 */
public class PostfixToInfix {

    // Data Fields
    /** supported operators */
    private static final String[] OPERATORS = {"+", "-", "*", "/", "%"};
    /** stack to store operands */
    private static Stack<String> stack;
    
    /**
     * Convert an expression from postfix form to infix form.
     * Supports +,-,*,/,% operators.
     * Mathematical validity of expression is not checked.
     * @param postfix The postfix expression to be converted
     * @return The infix expression
     * @throws SyntaxErrorException
     */
    public static String convert(String postfix) throws SyntaxErrorException {
        
        stack = new Stack<String>();
        try {
            
            StringTokenizer sToke = new StringTokenizer(postfix);   // whitespace delimiter
            String next;    // to store a token of sToke
            
            while (sToke.hasMoreTokens()) {
                next = sToke.nextToken();
                
                try {   // if token is an integer, push it to stack
                    stack.push(Integer.parseInt(next) + "");
                    continue;
                } catch (NumberFormatException e) {}
                try {   // if token is a double, push it to stack
                    stack.push(Double.parseDouble(next) + "");
                    continue;
                } catch (NumberFormatException e) {}
                
                
                if (isOp(next)) {   // if token is an operator, process it
                    processOp(next);
                } else if (next.matches("\\w+")) {  // if token is a variable, push it to stack
                    stack.push(next);
                } else { 
                    // token is not a number, operator, or variable
                    throw new SyntaxErrorException("Unexpected Token Encountered: " + next);
                }
            }
        
            String infix = stack.pop(); // resulting infix expression
            
            if (!stack.empty()) {   // too many operands
                throw new SyntaxErrorException("Elements left on stack");
            }
            
            // return infix expression without the leading and trailing parentheses
            return infix.substring(1, infix.length() - 1).trim();
            
        } catch (EmptyStackException e) {   // too few operands
            throw new SyntaxErrorException("Attempt to pop from an empty stack");
        }
    }
    
    /**
     * Method to process operators.
     * @param op The operator
     * @throws EmptyStackException
     */
    public static void processOp(String op) throws EmptyStackException {
        // pop two operands
        String operand2 = stack.pop();
        String operand1 = stack.pop();
        
        // push infix form with surrounding parentheses
        stack.push("(" + operand1 + " " + op + " " + operand2 + ")");
    }
    
    /**
     * Determine whether a string is an operator
     * @param op The string to be checked
     * @return true if op is an operator
     */
    private static boolean isOp(String op) {
        for (String s : OPERATORS) {
            if (op.equals(s)) {
                return true;    // op is supported
            }
        }
        return false; //op is not supported
    }
}
