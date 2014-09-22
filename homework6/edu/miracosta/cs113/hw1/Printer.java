package edu.miracosta.cs113.hw1;

/**
 * Class to represent a printer.
 * @author Evan Carey
 */

import java.util.LinkedList;

public class Printer implements Comparable<Printer> {

    // Data Fields
    /** The speed of the printer */
    private final int PRINT_SPEED = 6; // prints a page every 6 seconds
    /** Identifying string */
    private String printerID;
    /** The queue of print jobs */
    private LinkedList<PrintJob> queue;
    /** Whether the printer is waiting for a job */
    private boolean waiting;
    /** The job currently being printed */
    private PrintJob currentJob;
    /** The number of pages in current job that haven't been printed yet */
    private int pagesToPrint;
    /** Time spent on current job */
    private int processingTime;

    /**
     * Construct a new printer with a given ID string.
     * 
     * @param printerID
     *            String used to identify the printer
     */
    public Printer(String printerID) {
        this.printerID = printerID;
        queue = new LinkedList<PrintJob>();
        // totalTime = 0;
        waiting = true;
    }

    /**
     * Add a print job to the printer's queue. The job is inserted based on its
     * priority.
     * 
     * @param job
     *            The print job to be added
     */
    public void addJob(PrintJob job) {

        boolean added = false;

        if (queue.isEmpty()) {
            queue.add(job);
        } else {
            for (PrintJob next : queue) {
                if (job.compareTo(next) > 0) { // job has higher priority than
                                               // next
                    queue.add(queue.indexOf(next), job); // so it is added
                                                         // before next
                    added = true;
                    break;
                }
            }
            if (!added) { // reached end of list
                queue.add(job);
            }
        }
    }

    /**
     * Check if printer is busy or not and call appropriate method.
     */
    public void update() {

        if (isWaiting() && !queue.isEmpty()) {
            startNewJob(queue.remove());
        }
        if (!isWaiting()) {
            // printer is currently printing
            processJob();
        }

    }

    /**
     * Determine if printer has more jobs.
     * 
     * @return true if printer has more jobs
     */
    public boolean hasMoreJobs() {
        return pagesQueued() > 0 || !isWaiting();
    }

    /**
     * Method to start a print job.
     * 
     * @param p
     *            The print job
     */
    public void startNewJob(PrintJob p) {
        currentJob = p;
        pagesToPrint = currentJob.getPages();
        processingTime = 0;
        waiting = false;
    }

    /**
     * Method to process current print job.
     */
    public void processJob() {
        processingTime++;
        if (pagesToPrint > 0) {
            if (processingTime % PRINT_SPEED == 0) {
                pagesToPrint--;
            }
        } else { // finished current job
            output(currentJob);
            waiting = true;
        }
    }

    /**
     * Returns whether the printer is waiting for a job.
     * 
     * @return true if it's waiting.
     */
    public boolean isWaiting() {
        return waiting;
    }

    /**
     * Output completed job and time of completion.
     * 
     * @param current
     *            The completed job
     */
    public void output(PrintJob current) {
        System.out
                .println("(" + PrinterSim.getFormattedTime() + ")  \t"
                        + printerID + " - finished printing job "
                        + current.getJobNum());
    }

    /**
     * Returns how many pages are left to print, both in the current job and the
     * queue.
     * 
     * @return The number of pages left to print
     */
    public int pagesQueued() {
        int pagesInQueue = 0;
        for (PrintJob pj : queue) {
            pagesInQueue += pj.getPages();
        }
        return pagesInQueue + pagesToPrint;
    }

    /**
     * Determines equality with another printer based on the number of pages
     * left to print.
     * 
     * @return true if they have the same number of pages left
     */
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Printer) {
            return ((Printer) other).pagesQueued() == pagesQueued();
        }
        return false;
    }

    /**
     * Compare to another printer based on the number of pages they have left to
     * print.
     * 
     * @return a positive integer if this printer has more pages, a negative
     *         integer if the other printer has more pages, 0 if they have the
     *         same number of pages
     */
    @Override
    public int compareTo(Printer other) {
        if (other != null) {
            return pagesQueued() - ((Printer) other).pagesQueued();
        }
        return 0;
    }
}