package edu.miracosta.cs113.hw5_2;

/** 
 * Class to report a syntax error. 
 * @author Evan Carey
 */

@SuppressWarnings("serial")
public class SyntaxErrorException extends Exception {

    /**
     * Construct a SyntaxErrorException with the specified message.
     * @param message The message
     */
    public SyntaxErrorException(String message) {
        super(message);
    }
}
