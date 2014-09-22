package edu.miracosta.cs113.hw3_2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {
    private String first, last;

    /**
     * Student constructor
     * 
     * @param first
     *            Student's first name
     * @param last
     *            Student's last name
     */
    public Student(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public boolean equals(Object other) {
        if (other instanceof Student) {
            return (getFirst().equalsIgnoreCase(((Student) other).getFirst()) && getLast()
                    .equalsIgnoreCase(((Student) other).getLast()));
        }
        return false;
    }

    public String toString() {
        return first + " " + last;
    }
}