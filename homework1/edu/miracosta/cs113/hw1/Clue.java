package edu.miracosta.cs113.hw1;

/**
 * Assignment: Week 1 Homework 1 
 * File: Clue.java
 * @author Evan Carey
 * 
 * Problem Statement: Write a program that is able to guess the correct
 * combination of murderer, location, and weapon efficiently.
 */

import edu.miracosta.cs113.theory.Theory;

public class Clue {

    public static void main(String[] args) {
        int m = 1; // murderer
        int l = 1; // location
        int w = 1; // weapon
        int jack; // Jack's response to each theory

        do {
            jack = Theory.theoryTest3(m, l, w);
//            m += (jack == 1) ? 1 : 0;
//            l += (jack == 2) ? 1 : 0;
//            w += (jack == 3) ? 1 : 0;
            switch (jack) {
                case 1: m++; break;
                case 2: l++; break;
                case 3: w++; break;
                default:     break;
            }
        } while (jack != 0);
        
        System.out.println("Murderer: " + m + "\nLocation: " + l + "\nWeapon: " + w);
        Theory.checkTheory();
    }
}
