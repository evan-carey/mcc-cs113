package edu.miracosta.cs113.lab1;

/**
 * Assignment: Week 4 Lab 
 * File: Stack.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write a class that emulates
 *         Java's Stack class (using ArrayList or LinkedList)
 */
import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackArray<E> {

    private ArrayList<E> stack;

    /**
     * Construct a new stack
     */
    public StackArray() {
        stack = new ArrayList<E>();
    }

    /**
     * Test to see if the stack is empty.
     * @return True if stack is empty; false otherwise
     */
    public boolean empty() {
        return stack.isEmpty();
    }

    /**
     * Examine the top element of the stack.
     * @return The top element of the stack, without removing it
     */
    public E peek() {
        if (!empty()) {
            return stack.get(stack.size() - 1);
        }
        throw new EmptyStackException();
    }

    /**
     * Pop the top element of the stack.
     * @return The element popped
     */
    public E pop() {
        if (!empty()) {
            return stack.remove(stack.size() - 1);
        }
        throw new EmptyStackException();
    }

    /**
     * Push an object onto the stack.
     * @param obj
     *            The object being pushed
     * @return The object being pushed
     */
    public E push(E obj) {
        stack.add(obj);
        return obj;
    }
}
