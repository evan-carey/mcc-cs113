package edu.miracosta.cs113.hw1;
/**
 * Assignment: Week 9 HW 1
 *             Programming Project 2, pg. 417
 *              
 * File: WordFrequencyTest.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Use a HashMap to store the frequency counts for all the words in a
 * large text document. Display the contents of this HashMap. Convert it to an array.
 * Sort the array based on key value and display it. Then sort the array by decreasing
 * frequency and display it.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordFrequencyTest {

    /** Contains the word-frequency pairs to be stored in the array */
    private static class Word {
        
        /** The key */
        private String word;
        /** the value */
        private int frequency;
        
        /**
         * Creates a new word-frequency pair.
         * @param word The key
         * @param frequency The value
         */
        public Word(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
        
        /**
         * Returns the key/word.
         * @return The key
         */
        public String getWord() {
            return word;
        }
        
        /**
         * Returns the value/frequency.
         * @return The value
         */
        public int getFreq() {
            return frequency;
        }
        
        /**
         * Returns a string representation of the pair.
         * @return A string in the form "word=frequency"
         */
        public String toString() {
            return word + "=" + frequency;
        }
    }
    
    /**
     * Read a given text file and store the frequency counts for all the words in a HashtableChain.
     * @param fileName A string representation of the file path/name to be read.
     * @return The created HashMap
     */
    public static Map<String, Integer> getFrequency(String fileName) {
        
        Map<String, Integer> table = new HashtableChain<String, Integer>();
        
        try {
            Scanner iStream = new Scanner(new FileInputStream(fileName));
            iStream.useDelimiter(".");
            while (iStream.hasNextLine()) {
                String next;
                while ((next = iStream.findInLine("[\\p{L}\\p{N}']+")) != null) {
                    next = next.toLowerCase();
                    Integer freq = table.get(next);
                    if (freq != null) {
                        table.put(next, freq + 1);
                    } else {
                        table.put(next, 1);
                    }
                }
                iStream.nextLine();
            }
            iStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
            System.exit(1);
        }
        return table;
    }
    
    /**
     * Display the contents of a Map by iterating through a set view of the map.
     * @param table The Map to be displayed.
     */
    public static void displayTable(Map<String, Integer> table) {
        
        int i = 0;
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            
            if (i % 10 == 0) {
                System.out.print("\n  ");
            } else {
                System.out.print(", ");
            }
            i++;
            System.out.print(entry.getKey() + "=" + entry.getValue());
        }
    }
    
    /**
     * Convert a Map to an array that stores Word objects.
     * @param table The Map to be converted.
     * @return The array
     */
    public static Word[] mapToArray(Map<String, Integer> table) {
        
        Set<Map.Entry<String, Integer>> set = table.entrySet();
        Word[] freqArray = new Word[set.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : set) {
            freqArray[i] = new Word(entry.getKey(), entry.getValue());
            i++;
        }
        return freqArray;
    }
    
    /**
     * Sort the contents of a Word array based on key value (lexicographic order).
     * @param a The Word array to be sorted.
     */
    public static void sortByKey(Word[] a) {
        
        Arrays.sort(a, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                if (w1 == null) {
                    return -1;
                }
                if (w2 == null) {
                    return 1;
                }
                return w1.getWord().compareTo(w2.getWord());
            }
        });
    }
    
    /**
     * Sort the contents of a Word array in decreasing order by frequency.
     * @param a The Word array to be sorted.
     */
    public static void sortByFreq(Word[] a) {
        
        Arrays.sort(a, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                if (w1 == null) {
                    return -1;
                }
                if (w2 == null) {
                    return 1;
                }
                return w2.getFreq() - w1.getFreq();
            }
        });
    }
    
    /**
     * Display the contents of a Word array using the elements' toString method, 10 per line.
     * @param a The Word array to be displayed.
     */
    public static void displayArray(Word[] a) {
        
        int i = 0;
        for (Word w : a) {
            if (w == null) {
                continue;
            }
            if (i % 10 == 0) {
                System.out.print("\n  ");
            } else {
                System.out.print(", ");
            }
            i++;
            System.out.print(w);
        }
    }
    
    /**
     * Create a HashMap to store the frequency counts for words in a text file.
     * Convert it to an array and sort it first by key, then by value.
     * @param args
     */
    public static void main(String[] args) {
        
        // Text file to be read (relative path)
        String file = "homework9/readme.txt";
        
        // HashMap to store frequency counts of words in the file
        HashtableChain<String, Integer> freqMap = (HashtableChain<String, Integer>) getFrequency(file);
        
        // Display the contents of the HashMap
        System.out.println("Unsorted HashMap:");
        displayTable(freqMap);
        
        // Array representation of the HashMap
        Word[] freqArray = mapToArray(freqMap);
        
        // Sort the array based on the keys and display it
        sortByKey(freqArray);
        System.out.println("\n\nSorted By Key:");
        displayArray(freqArray);
        
        // Sort the array in decreasing order by frequency and display it.
        sortByFreq(freqArray);
        System.out.println("\n\nSorted By Frequency:");
        displayArray(freqArray);
    }
}