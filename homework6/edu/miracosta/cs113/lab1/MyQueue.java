package edu.miracosta.cs113.lab1;
/**
 * Assignment: Week 6 Lab 1
 *              
 * File: MyQueue.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Create a queue implementation with LinkedList
 */
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<E> {

    // Data Field
    LinkedList<E> queue;
    
    /**
     * Construct a new queue
     */
    public MyQueue() {
        queue = new LinkedList<E>();
    }
    
    /**
     * Returns the entry at the front of the queue without
     * removing it.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public E element() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return queue.getFirst();
    }
    
    /**
     * Inserts item at the rear of the queue
     * 
     * @param item The element to be added
     * @return whether the insertion was successful
     */
    public boolean offer(E item) {
        return queue.add(item);
    }

    /**
     * Returns the entry at the front of the queue.
     * 
     * @return the element at the front, or null if the queue is empty
     */
    public E peek() {
        try {
            return queue.getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Removes the entry at the front of the queue.
     * 
     * @return the element removed, or null if the queue is empty
     */
    public E poll() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    /**
     * Removes the entry at the front of the queue.
     * 
     * @return the element removed
     * @throws NoSuchElementException if the queue is empty
     */
    public E remove() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.removeFirst();
    }   
}