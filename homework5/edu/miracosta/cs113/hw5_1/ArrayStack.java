package edu.miracosta.cs113.hw5_1;

/**
 * Assignment: Week 5 Homework 1 (Ch. 4, Programming Project 3)
 * File: ArrayStack.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Provide a complete 
 *              implementation of class ArrayStack
 */
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<E> implements StackInt<E> {

    // Data Fields
    /** Storage for stack */
    private E[] theData;
    /** Index to top of stack */
    int topOfStack = -1; // Initially empty stack
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Construct an empty stack with the default initial capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        theData = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Insert a new item on top of the stack.
     * 
     * @param obj The item to be inserted
     * @return The item that was inserted
     */
    @Override
    public E push(E obj) {
        if (topOfStack == theData.length - 1) {
            reallocate();
        }
        theData[++topOfStack] = obj;
        return obj;
    }

    /**
     * Return the top item on the stack.
     * 
     * @return The top item on the stack
     */
    @Override
    public E peek() {
        return theData[topOfStack];
    }

    /**
     * Remove and return the top item on the stack.
     * 
     * @return The top item on the stack
     * @throws EmptyStackException
     *             if the stack is empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack--];
    }

    /**
     * Return true if stack is empty.
     * 
     * @return true if index of top of stack is less than 0, false otherwise
     */
    @Override
    public boolean empty() {
        return topOfStack < 0;
    }

    /**
     * Double the size of the array.
     */
    public void reallocate() {
        theData = Arrays.copyOf(theData, theData.length * 2);
    }
}