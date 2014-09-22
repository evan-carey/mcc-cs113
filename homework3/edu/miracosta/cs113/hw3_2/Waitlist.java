package edu.miracosta.cs113.hw3_2;
/**
 * Assignment: Week 3 Homework 2 
 * File: Waitlist.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write a program to maintain a wait list
 *         of students. Use KWLinkedList. 
 */
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import edu.miracosta.cs113.hw4.KWLinkedList;

public class Waitlist {

    /**
     * Read waitList from file into KWLinkedList for manipulation.
     * After, write objects from KWLinkedList back to file.
     * 
     * @param args
     */
    public static void main(String[] args) {
        KWLinkedList<Student> waitList = new KWLinkedList<Student>();

        try {
            ObjectInputStream iStream = new ObjectInputStream(
                    new FileInputStream("waitlist.dat"));
            try {
                while (true) {
                    waitList.add((Student) iStream.readObject());
                }
            } catch (EOFException e) {
                // reached end of file (good thing)
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            iStream.close();
        } catch (IOException e) {
            System.out.println("Error reading from waitlist file.");
        }

        boolean done = false;

        // User menu
        while (!done) {
            System.out.println("Current Wait List:");
            int i = 1;
            for (Student s : waitList) {
                System.out.println("\t" + i + ". " + s);
                i++;
            }
            Scanner keyboard = new Scanner(System.in);
            System.out.println("\nWhat would you like to do? (Enter 1,2,3,4,5)"
                    + "\n\t1. Add a new student at end of list."
                    + "\n\t2. Add a new student at beginning of list."
                    + "\n\t3. Remove student from beginning of list."
                    + "\n\t4. Remove student by name."
                    + "\n\t5. Save and quit.");

            int choice = keyboard.nextInt();
            switch (choice) {
            case 1:
                addToEnd(waitList);
                break;
            case 2:
                addToBeginning(waitList);
                break;
            case 3:
                removeFromBeginning(waitList);
                break;
            case 4:
                removeByName(waitList);
                break;
            case 5:
                done = true; // breaks out of loop
                break;
            default:
                System.out.println("Innvalid selection, please try again.\n");
                break;
            }
        }

        // Write all Student objects in waitList to file
        try {
            ObjectOutputStream oStream = new ObjectOutputStream(
                    new FileOutputStream("waitlist.dat", false));

            for (Student s : waitList) {
                oStream.writeObject(s);
            }
            oStream.close();
            System.out.println("Wait list successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to file.");
        }
    }

    /**
     * Add new Student to end of waitList.
     * 
     * @param waitList
     *            LinkedList new Student object is added to
     */
    private static void addToEnd(KWLinkedList<Student> waitList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter student's name (first last): ");
        String first = keyboard.next();
        String last = keyboard.next();
        if (waitList.add(new Student(first, last))) {
            System.out.println(first + " " + last + " added to end of list.");
        }
    }

    /**
     * Add new to to beginning of waitList.
     * 
     * @param waitList
     *            LinkedList new Student object is added to
     */
    private static void addToBeginning(KWLinkedList<Student> waitList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter student's name (first last): ");
        String first = keyboard.next();
        String last = keyboard.next();
        waitList.addFirst(new Student(first, last));
        System.out.println(first + " " + last + " added to beginning of list.");
    }

    /**
     * Remove the first Student in waitList.
     * 
     * @param waitList
     *            LinkedList that Student object is being removed from
     */
    private static void removeFromBeginning(KWLinkedList<Student> waitList) {
        Student removed = waitList.remove(0);
        System.out.println(removed + " removed from list.");
    }

    /**
     * Remove Student from waitList by name.
     * 
     * @param waitList
     *            LinkedList that Student object is being removed from
     */
    private static void removeByName(KWLinkedList<Student> waitList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter student's name to remove (first last): ");
        String first = keyboard.next();
        String last = keyboard.next();
        Student temp = new Student(first, last);
        if (waitList.remove(temp)) {
            System.out.println(temp + " removed from list.");
        } else {
            System.out.println(temp + " not found in list.");
        }
    }
}