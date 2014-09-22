package edu.miracosta.cs113.hw1;

/**
 * Assignment: Week 8 HW 1
 *             Programming Project 2, pg. 356
 *              
 * File: HuffTest.java
 * 
 * @author Evan Carey
 * 
 * Problem Statement: Write an application to test the HuffmanTree class.
 *  1. Read a text file and build a frequency table for the characters
 *  2. Create a Huffman code tree and then a string of 0s and 1s that
 *     represents the code string for that file
 *  3. Read the string back in and recreate the contents of the original file
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import KW.CH06.HuffmanTree;
import KW.CH06.HuffmanTree.HuffData;

public class HuffTest {
    
    // Data Fields
    /** Path of the file to encode. */
    private static final String IN_FILE = "homework8/test.txt";
    /** Path of the file written to by HuffmanTree.printCode() */
    private static final String CODE_FILE = "homework8/code_table.txt";
    /** String of the text from IN_FILE */
    private static String fileText;

    /**
     * Build a frequency table using readFile().
     * Using methods in HuffmanTree, build a Huffman Tree using the frequency table
     * and output the codes to a file.
     * Read in the codes with getEncoded() to construct the encoded string.
     * Output the encoded string.
     * Decode and output the text using a method in HuffmanTree.
     * @param args
     */
    public static void main(String[] args) {
        
        // Build frequency table from text file
        HuffData[] freqTable = readFile(IN_FILE);
        
        // Create and build Huffman code tree
        HuffmanTree tree = new HuffmanTree();
        tree.buildTree(freqTable);
        
        // Output code table to file
        try {
            tree.printCode(new PrintStream(CODE_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error writing to file.");
            System.exit(1);
        }
        
        // Print original text
        System.out.println("Original:\t" + fileText);
        
        String encodedText = getEncoded(fileText);
        
        // Print encoded text
        System.out.println("Encoded:\t" + encodedText);
        // Print decoded text
        System.out.println("Decoded:\t" + tree.decode(encodedText));
    }
    
    /**
     * Read text from file and store that characters and their frequencies
     * in a HashMap. Convert HashMap to an array of HuffData objects.
     * @param fileName The path of the text file to be read.
     * @return An array of HuffData objects
     */
    public static HuffData[] readFile(String fileName) {
        
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> table = new HashMap<Character, Integer>();
        
        // Read file text into table
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int next;
            while ((next = reader.read()) != -1)  {
                sb.append((char) next);
                Integer freq = table.get((char) next);
                if (freq != null) {
                    table.put((char) next, freq + 1);
                } else {    // first occurrence
                    table.put((char) next, 1);
                }
            }
            reader.close();            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file.");
            System.exit(1);
        }
        
        // Convert table to HuffData array
        HuffData[] huffArray = new HuffData[table.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : table.entrySet()) {
            huffArray[i] = new HuffData(entry.getValue().doubleValue(), entry.getKey());
            i++;
        }
        // Copy text from file into a string
        fileText = sb.toString();
        return huffArray;
    }
    
    /**
     * Convert a string of text into an encoded string using the table generated
     * by HuffmanTree.printCode()
     * @param text The text to be converted.
     * @return The text converted using Huffman encoding.
     */
    public static String getEncoded(String text) {
        HashMap<Character, String> codes = new HashMap<Character, String>();
        
        try {
            Scanner iStream = new Scanner(new FileInputStream(CODE_FILE));
            while (iStream.hasNext()) {
                String nextStr = iStream.next();
                char nextChar;
                if (nextStr.startsWith("space")) {
                    nextChar = ' ';
                } else {
                    nextChar = nextStr.charAt(0);
                }
                String code = iStream.next();
                codes.put(nextChar, code);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error reading from file.");
            System.exit(1);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i ++) {
            sb.append(codes.get(text.charAt(i)));
        }
        return sb.toString();
    }
}