package edu.miracosta.cs113.hw5_2;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Class to convert an infix expression to postfix form.
 * @author Evan Carey
 */
public class InfixToPostfix {
    
    // Data Fields
    /** supported operators */
    private static final String[] OPERATORS = {"+", "-", "*", "/", "%", "(", ")"};
    /** precedence of operators in OPERATORS (in order) */
    private static final int[] PRECEDENCE = {1, 1, 2, 2, 2, -1, -1};

    /** stack to store operators */
    private static Stack<String> stack;
    /** the postfix expression */
    private static StringBuilder postfix;
    
    /**
     * Convert an expression from infix form to postfix form.
     * Supports +,-,*,/,% operators.
     * Aside from parentheses check, mathematical validity of expression is not checked.
     * @param infix The infix expression to be converted.
     * @return The resulting postfix expression
     * @throws SyntaxErrorException if infix expression is not valid
     */
    public static String convert(String infix) throws SyntaxErrorException {

        stack = new Stack<String>();
        postfix = new StringBuilder();
        
        try {
            // replace all opening parentheses and brackets in expression with "( "
            infix = infix.replaceAll("\\(|\\[|\\{", "\\( ");
            // replace all closing parentheses and brackets in expression with ") "
            infix = infix.replaceAll("\\)|\\]|\\}", " \\)");
            
            StringTokenizer sToke = new StringTokenizer(infix); // whitespace delimiter
            String next; // to hold a token of sToke
            
            while (sToke.hasMoreTokens()) {
                
                next = sToke.nextToken();
                
                try {   // if token is an integer, append it to postfix
                    postfix.append(Integer.parseInt(next) + " ");
                    continue;
                } catch (NumberFormatException e) {}
                try {   // if token is a double, append it to postfix
                    postfix.append(Double.parseDouble(next) + " ");
                    continue;
                } catch (NumberFormatException e) {}
                
                if (isOp(next)) {   // if token is an operator, process it
                    processOp(next);
                } else if (next.matches("\\w+")) {  // if token is a variable, append it to postfix
                    postfix.append(next + " ");
                } else {
                 // token is not a number, operator, or variable
                    throw new SyntaxErrorException("Unexpected Token Encountered: " + next);
                }
            }
        
            // append any operators left on the stack to postfix
            while (!stack.empty()) {
                String op = stack.pop();
                if (op.equals("(")) {
                    throw new SyntaxErrorException("Unmatched opening parenthesis");
                }
                postfix.append(op + " ");
            }
            
            // return resulting postfix expression
            return postfix.toString();
        
        } catch (EmptyStackException e) {
            throw new SyntaxErrorException("Attempt to pop from an empty stack");
        }
    }
    
    /**
     * Method to process operators.
     * @param op The operator
     * @throws EmptyStackException
     */
    public static void processOp(String op) throws EmptyStackException {
        
        if (stack.empty() || op.equals("(")) {
            stack.push(op);
        } else {
            // peek the stack and assign it to top
            String top = stack.peek();
            if (precedence(op) > precedence(top)) {
                stack.push(op);
            } else {
                // pop all stacked operators with equal or higher precedence than op
                while (!stack.empty() && precedence(op) <= precedence(top)) {
                    stack.pop();
                    if (top.equals("(")) {  // matching parenthesis popped - exit loop
                        break;
                    }
                    postfix.append(top + " ");
                    if (!stack.empty()) {
                        // reset top
                        top = stack.peek();
                    }
                }
                if (!op.equals(")")) {
                    stack.push(op);
                }
            }
        }
    }
    
    /**
     * Determine whether a string is an operator.
     * @param op The string.
     * @return true if op is an operator
     */
    private static boolean isOp(String op) {
        for (String s : OPERATORS) {
            if (op.equals(s)) {
                return true;    // op is a supported operator
            }
        }
        return false;   // op is not a supported operator
    }
    
    /**
     * Determine precedence of an operator.
     * @param op The operator
     * @return the precedence
     */
    private static int precedence(String op) {
        for (int i = 0; i < OPERATORS.length; i++) {
            if (op.equals(OPERATORS[i])) {
                return PRECEDENCE[i];
            }
        }
        return -1;  // should never be reached
    }
}