package edu.miracosta.cs113.hw3_1;

/**
 * Assignment: Week 3 Homework 1 
 * File: HomeworkList.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write a program to maintain a list of homework
 *         assignments. 
 */
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkList {

    /**
     * Read homeworkList from file into ArrayList for manipulation.
     * After, write objects from ArrayList back to file.
     * 
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Assignment> homeworkList = new ArrayList<Assignment>();

        // Read Assignment objects from file into homeworkList
        try {
            ObjectInputStream iStream = new ObjectInputStream(
                    new FileInputStream("homework.dat"));
            try {
                while (true) {
                    homeworkList.add((Assignment) iStream.readObject());
                }
            } catch (EOFException e) {
                // reached end of file (good thing)
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            iStream.close();
        } catch (IOException e) {
            System.out.println("Error reading from homework file.");
        }

        boolean done = false;

        // User menu
        while (!done) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What would you like to do? (Enter 1,2,3,4,5)"
                    + "\n\t1. Add a new assignment."
                    + "\n\t2. Remove an assignment."
                    + "\n\t3. Show all assignments."
                    + "\n\t4. Find assignment(s) with earliest due date."
                    + "\n\t5. Save and quit.");

            int choice = keyboard.nextInt();
            switch (choice) {
            case 1:
                addAssignment(homeworkList);
                break;
            case 2:
                removeAssignment(homeworkList);
                break;
            case 3:
                showAllAssignments(homeworkList);
                break;
            case 4:
                findEarliestDue(homeworkList);
                break;
            case 5:
                done = true; // breaks out of loop
                break;
            default:
                System.out.println("Innvalid selection, please try again.\n");
                break;
            }
        }
        
        // Write all Assignment objects in homeworkList to file
        try {
            ObjectOutputStream oStream = new ObjectOutputStream(
                    new FileOutputStream("homework.dat", false));

            for (Assignment a : homeworkList) {
                oStream.writeObject(a);
            }
            oStream.close();
            System.out.println("Homework list successfully written to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    /**
     * Add new Assignment to end of homeworkList.
     * 
     * @param homeworkList
     *            ArrayList being added to
     */
    public static void addAssignment(ArrayList<Assignment> homeworkList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter course: ");
        String course = keyboard.nextLine();
        System.out.print("Enter assignment name: ");
        String name = keyboard.nextLine();
        System.out.print("Enter due date (yyyy/mm/dd): ");
        String dueDate = keyboard.nextLine();

        if (homeworkList.add(new Assignment(course, name, dueDate))) {
            System.out.println(course + " " + name + " successfully added.\n");
        }
    }

    /**
     * Remove an Assignment from homeworkList.
     * 
     * @param homeworkList
     *            ArrayList to remove from
     */
    public static void removeAssignment(ArrayList<Assignment> homeworkList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter course: ");
        String course = keyboard.nextLine();
        System.out.print("Enter assignment name: ");
        String name = keyboard.nextLine();
        for (Assignment a : homeworkList) {
            // Remove Assignment object if course and name match
            if (course.equalsIgnoreCase(a.getCourse())
                    && name.equalsIgnoreCase(a.getName())) {
                if (homeworkList.remove(a)) {
                    System.out.println(a + " successfully removed.\n");
                    return;
                }
            }
        }
        System.out.println("No assignment matching " + "\"" + course + " "
                + name + "\" found.");
    }

    /**
     * Display every Assignment in homeworkList.
     * 
     * @param homeworkList
     *            ArrayList being printed
     */
    public static void showAllAssignments(ArrayList<Assignment> homeworkList) {
        System.out.println("All assignments:");
        for (Assignment a : homeworkList) {
            System.out.println("\t" + a);
        }
        System.out.println();
    }

    /**
     * Find Assignment in homeworkList with earliest due date.
     * 
     * @param homeworkList
     *            ArrayList being searched
     */
    private static void findEarliestDue(ArrayList<Assignment> homeworkList) {
        Assignment earliest = homeworkList.get(0);
        if (earliest == null) {
            System.out.println("No assignments due.");
        } else {
            for (Assignment a : homeworkList) {
                if (a.compareTo(earliest) < 0) {
                    earliest = a;
                }
            }
            System.out.println("Earliest assignment(s) due:");
            for (Assignment a : homeworkList) {
                if (a.equals(earliest)) {
                    System.out.println("\t" + a);
                }
            }
        }
        System.out.println("\n");
    }
}