package edu.miracosta.cs113.lab2;

/**
 * Assignment: Week 5 Lab 2
 *              Page 200, Programming Exercises 1, 2, 3
 * File: QueueLab.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: 
 *      Store integer values in a stack, copy the values to a second stack and a
 *      queue (class MyQueue), and then output their contents
 */
import java.util.Stack;

import edu.miracosta.cs113.lab1.MyQueue;

public class QueueLab {

    /**Fill a stack with integer values, then copy the stack's contents to another
     * stack and a queue. Output the contents of the second stack and queue.
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 1. Create two stacks and a queue of Integer objects. Store the
         * numbers -1, 15, 23, 44, 4, 99 in the first stack.
         */
        int[] items = { -1, 15, 23, 44, 4, 99 };

        Stack<Integer> firstStack = new Stack<Integer>();
        Stack<Integer> secondStack = new Stack<Integer>();
        MyQueue<Integer> queue = new MyQueue<Integer>();

        // push values in items[] to firstStack
        for (int i = 0; i < items.length; i++) {
            firstStack.push(items[i]);
        }

        /**
         * 2. Get each number from the first stack and store it in the second
         * stack and the queue.
         */
        while (!firstStack.empty()) {
            secondStack.push(firstStack.peek());
            queue.offer(firstStack.pop());
        }

        /**
         * 3. Remove a value from the second stack and the queue and display
         * each pair of values on a separate output line. Continue until the
         * data structures are empty.
         */
        System.out.println("Stack\tQueue");
        while (!secondStack.empty()) {
            System.out.println(secondStack.pop() + "\t" + queue.remove());
        }
    }
}