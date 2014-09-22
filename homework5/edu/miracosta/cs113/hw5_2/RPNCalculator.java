package edu.miracosta.cs113.hw5_2;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class to evaluate a postfix expression.
 * @author Evan Carey
 */
public class RPNCalculator {

    // Data Fields
    /** supported operators */
    private static final String[] OPERATORS = {"+", "-", "*", "/", "%"};
    /** stack to store operands */
    private static Stack<Double> stack;

    /**
     * Evaluates a postfix expression.
     * Supports +,-,*,/,% operators.
     * Only integer operands are supported.
     * @param rpn The expression to be evaluated
     * @return The resulting value of the expression
     * @throws SyntaxErrorException if expression is invalid
     */
    public static double calcRPN(String rpn) throws SyntaxErrorException {
        
        stack = new Stack<Double>();
        
        StringTokenizer sToke = new StringTokenizer(rpn);   // whitespace delimiter
        
        try {
            String token;
    
            while (sToke.hasMoreElements()) {
                token = sToke.nextToken();
                
                // if token is an integer, push it
                try {
                    Integer i = Integer.parseInt(token);
                    stack.push(i.doubleValue());
                    continue;
                } catch (NumberFormatException e) {}
                
                // if token is an operator, perform operation
                if (isOperator(token)) {
                    try {
                        performOperation(token);
                    } catch (ArithmeticException e) {
                        throw new SyntaxErrorException(e.getMessage());
                    }
                } else { // token is not an integer or an operator
                    throw new SyntaxErrorException("Unexpected Token Encountered: " + token);
                }
            }
            double result = stack.pop(); // final value of expression
            if (!stack.empty()) { // too many operands
                throw new SyntaxErrorException("Elements left on stack");
            }
            
            return result;
            
        } catch (EmptyStackException e) { // too few operands
            throw new SyntaxErrorException("Attempt to pop from an empty stack");
        } 
    }
    
    /**
     * Evaluate current operation.
     * @param op The operator
     * @throws EmptyStackException if it attempts to pop from an empty stack
     * @throws ArithmeticException if it attempts to divide by zero
     */
    public static void performOperation(String op) throws EmptyStackException, ArithmeticException {
        // pop operators from stack
        double x, y;
        y = stack.pop();
        x = stack.pop();
        
        //perform operation and push result
        
        if (op.equals("+")) {           // +
            stack.push(x + y);
        } else if (op.equals("-")) {    // -
            stack.push(x - y);
        } else if (op.equals("*")) {    // *
            stack.push(x * y);
        } else if (op.equals("/")) {    // /
            if (y == 0) {
                throw new ArithmeticException("Attempt to divide by zero");
            }
            stack.push(x / y);
        } else if (op.equals("%")) {    // %
            if (y == 0) {
                throw new ArithmeticException("Attempt to divide by zero");
            }
            stack.push(x % y);
        } 
    }
    
    /**
     * Determine whether a string is an operator
     * @param op The string to be checked
     * @return true if op is an operator.
     */
    public static boolean isOperator(String op) {
        for (String s : OPERATORS) {
            if (op.equals(s)) {
                return true;
            }
        }
        return false;
    }
}