package edu.miracosta.cs113.hw1;

/**
 * Assignment: Week 6 Homework 1 (Ch. 4, Programming Project 5) File:
 * PrinterSim.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Write a program that simulates processing 100
 *         print jobs of varying length (1-50 pages) using one, two, or three
 *         printers. The simulations will be compared by the time required to
 *         process all the jobs.
 */

public class PrinterSim {

    /** Number of print jobs in simulation */
    private static final int NUM_JOBS = 100;
    /** Set of the print jobs */
    private static PrintJob[] jobsArray;
    /** Simulated time (in seconds) */
    private static int time;

    /**
     * Construct a new simulation with a given number of jobs. Initialize jobs
     * and add them to the array.
     * 
     * @param numJobs
     *            Number of jobs in the simulation
     */
    public PrinterSim(int numJobs) {
        jobsArray = new PrintJob[numJobs];
        for (int i = 0; i < numJobs; i++) {
            // add a new print job with a random number of pages [1, 50]
            jobsArray[i] = new PrintJob(i + 1, (int) (Math.random() * 50 + 1));
        }
    }

    /**
     * Run simulation with one printer.
     */
    public void runOnePrinterSim() {

        Printer printerA = new Printer("Printer");

        time = 0;
        int nextJob = 0;

        while (nextJob < NUM_JOBS) {
            if (time % 60 == 0) {
                // Every 60 cycles, a job is sent to the printer
                printerA.addJob(jobsArray[nextJob]);
                System.out.println("(" + getFormattedTime() + ")  \t"
                        + jobsArray[nextJob] + " sent to Printer.");
                nextJob++;
            }
            time++;
            printerA.update();
        }
        while (printerA.hasMoreJobs()) {
            time++;
            printerA.update();

        }
        // After simulation has finished, output processing time
        System.out.println("\nTotal Simulation Time (One System Printer): "
                + getFormattedTime() + "\n");
    }

    /**
     * Run simulation with two printers.
     */
    public void runTwoPrinterSim() {

        Printer printerA = new Printer("Printer A");
        Printer printerB = new Printer("Printer B");

        time = 0;
        int nextJob = 0;

        while (nextJob < NUM_JOBS) {
            if (time % 60 == 0) {
                // Every 60 cycles, a job is sent to the printer with the fewest
                // pages in its queue
                if (printerA.compareTo(printerB) <= 0) {
                    printerA.addJob(jobsArray[nextJob]);
                    System.out.println("(" + getFormattedTime() + ")  \t"
                            + jobsArray[nextJob] + " sent to Printer A.");
                } else {
                    printerB.addJob(jobsArray[nextJob]);
                    System.out.println("(" + getFormattedTime() + ")  \t"
                            + jobsArray[nextJob] + " sent to Printer B.");
                }
                nextJob++;
            }
            time++;
            printerA.update();
            printerB.update();
        }
        while (printerA.hasMoreJobs() || printerB.hasMoreJobs()) {
            time++;
            printerA.update();
            printerB.update();
        }
        // After simulation has finished, output processing time
        System.out.println("\nTotal Simulation Time (Two System Printers): "
                + getFormattedTime() + "\n");
    }

    /**
     * Run simulation with three printers.
     */
    public void runThreePrinterSim() {

        Printer printerA = new Printer("Printer A");
        Printer printerB = new Printer("Printer B");
        Printer printerC = new Printer("Printer C");

        time = 0;
        int nextJob = 0;

        while (nextJob < NUM_JOBS) {
            if (time % 60 == 0) {
                // Every 60 cycles, a new job is sent to the printer with the fewest
                // pages in its queue
                if (printerA.compareTo(printerB) <= 0
                        && printerA.compareTo(printerC) <= 0) {
                    printerA.addJob(jobsArray[nextJob]);
                    System.out.println("(" + getFormattedTime() + ")  \t"
                            + jobsArray[nextJob] + " sent to Printer A.");
                } else if (printerB.compareTo(printerC) <= 0
                        && printerB.compareTo(printerA) <= 0) {
                    printerB.addJob(jobsArray[nextJob]);
                    System.out.println("(" + getFormattedTime() + ")  \t"
                            + jobsArray[nextJob] + " sent to Printer B.");
                } else {
                    printerC.addJob(jobsArray[nextJob]);
                    System.out.println("(" + getFormattedTime() + ")  \t"
                            + jobsArray[nextJob] + " sent to Printer C.");
                }
                nextJob++;
            }
            time++;
            printerA.update();
            printerB.update();
            printerC.update();
        }
        while (printerA.hasMoreJobs() || printerB.hasMoreJobs()
                || printerC.hasMoreJobs()) {
            time++;
            printerA.update();
            printerB.update();
            printerC.update();
        }
        // After simulation has finished, output processing time
        System.out.println("\nTotal Simulation Time (Three System Printers): "
                + getFormattedTime() + "\n");
    }

    /**
     * Method to format seconds into a string (hh:mm:ss)
     * 
     * @param seconds
     *            The time to format
     * @return the formatted time
     */
    public static String formatTime(int seconds) {
        int h = seconds / 3600; // hours
        seconds %= 3600;
        int m = seconds / 60; // minutes
        int s = seconds % 60; // seconds

        if (h > 0) {
            return String.format("%d:%02d:%02d", h, m, s); // time as hh:mm:ss
        } else {
            return String.format("%d:%02d", m, s); // time as mm:ss
        }
    }

    /**
     * Returns the formatted time of the current simulation.
     * 
     * @return the time in hh:mm:ss format
     */
    public static String getFormattedTime() {
        return formatTime(time);
    }

    /**
     * Create a new PrinterSim object and run the three simulations.
     * 
     * @param args
     */
    public static void main(String[] args) {

        PrinterSim sim = new PrinterSim(NUM_JOBS);

        // One printer simulation
        System.out.println("\nSimulation using One System Printer:\n");
        sim.runOnePrinterSim();

        // Two printer simulation
        System.out.println("\nSimulation using Two System Printers (A, B):\n");
        sim.runTwoPrinterSim();

        // Three printer simulation
        System.out.println("\nSimulation using Three System Printers (A, B, C):\n");
        sim.runThreePrinterSim();

    }
}
