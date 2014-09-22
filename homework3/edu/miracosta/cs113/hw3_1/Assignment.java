package edu.miracosta.cs113.hw3_1;

import java.io.Serializable;

@SuppressWarnings({ "serial", "rawtypes" })
public class Assignment implements Serializable, Comparable {
    private String course;
    private String name;
    private String dueDate;

    /**
     * Assignment constructor
     * 
     * @param course
     *            Course title
     * @param name
     *            Homework name
     * @param dueDate
     *            Date assignment is due (yyyy/mm/dd format)
     */
    public Assignment(String course, String name, String dueDate) {
        this.course = course;
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return dueDate;
    }

    public String toString() {
        return course + " " + name + " (due " + dueDate + ")";
    }

    public boolean equals(Object other) {
        if (other instanceof Assignment) {
            return getDate().equals(((Assignment) other).getDate());
        }
        return false;
    }

    @Override
    public int compareTo(Object other) {
        return getDate().compareTo(((Assignment) other).getDate());
    }

}