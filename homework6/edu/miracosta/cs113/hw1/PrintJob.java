package edu.miracosta.cs113.hw1;

/**
 * Class to represent a printing job.
 * 
 * @author Evan Carey
 */

public class PrintJob implements Comparable<PrintJob> {

    // Data Fields
    /** ID number for the job */
    private int jobNum;
    /** The number of pages */
    private int pages;

    /** The priority (based on pages) */
    private int priority;
    /** priorities */
    private final int HIGH = 3; // 0-9 pages
    private final int MEDIUM = 2; // 10-19 pages
    private final int LOW = 1; // 20 or more pages

    /**
     * Construct a new print job.
     * 
     * @param jobNum
     *            The job's number
     * @param pages
     *            The number of pages
     */
    public PrintJob(int jobNum, int pages) {
        this.jobNum = jobNum;
        this.pages = pages;
        setPriority(pages);
    }

    /**
     * Returns the job's ID number.
     * 
     * @return The ID number
     */
    public int getJobNum() {
        return jobNum;
    }

    /**
     * Returns the number of pages in the job.
     * 
     * @return The number of pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Returns the job's priority as a integer (1,2,3).
     * 
     * @return The priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority for the job.
     * 
     * @param pages
     *            The number of pages in the job
     */
    public void setPriority(int pages) {
        if (pages < 10) {
            priority = HIGH;
        } else if (pages < 20) {
            priority = MEDIUM;
        } else {
            priority = LOW;
        }
    }

    /**
     * Determines equality between job and another PrintJob based on their
     * priorities.
     * 
     * @param other
     *            The object being tested for equality
     * @return true if they have the same priority
     */
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof PrintJob) {
            return ((PrintJob) other).getPriority() == getPriority();
        }
        return false;
    }

    /**
     * Compare job to another PrintJob based on their priorities.
     * 
     * @param other
     *            The PrintJob being compared to
     * @return a positive integer if job has higher priority than other, a
     *         negative integer if job has lower priority than other, 0 if they
     *         have the same priority or other is null
     */
    @Override
    public int compareTo(PrintJob other) {
        if (other != null) {
            return getPriority() - ((PrintJob) other).getPriority();
        }
        return 0;
    }

    /**
     * String representation as "Job JOBNUM (PAGES pgs)", where JOBNUM is the
     * job ID number and PAGES is the number of pages.
     * 
     * @return the string
     */
    @Override
    public String toString() {
        return "Job " + getJobNum() + " (" + getPages() + " pgs)";
    }
}
