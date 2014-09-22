package edu.miracosta.cs113.hw2;

/**
 * Assignment: Week 2 HW 2 
 * File: FoodList.java
 * 
 * @author Evan Carey
 * 
 *         Problem Statement: Complete the Food class hierarchy. Read & store
 *         a list of foods. Show the total calories & overall percentages
 *         of fat, protein, and carbohydrates for this list.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.miracosta.cs113.hw2.categories.Dairy;
import edu.miracosta.cs113.hw2.categories.FatsOilsSweets;
import edu.miracosta.cs113.hw2.categories.Fruit;
import edu.miracosta.cs113.hw2.categories.Grain;
import edu.miracosta.cs113.hw2.categories.Protein;
import edu.miracosta.cs113.hw2.categories.Vegetable;

public class FoodList {

    /** Creates a list of foods and outputs the total calories and percents.
     * @param args
     */
    public static void main(String[] args) {

        double totalCal = 0.0;
        double percentFat = 0.0;
        double percentProtein = 0.0;
        double percentCarb = 0.0;
        ArrayList<Food> list = new ArrayList<Food>();

        list.add(new Vegetable("Carrot"));
        list.add(new Protein("Steak"));
        list.add(new Dairy("Yogurt"));
        list.add(new Fruit("Apple"));
        list.add(new Grain("Pretzel"));
        list.add(new FatsOilsSweets("Skittles"));
        list.add(new Fruit("Orange"));
        list.add(new Vegetable("Jalapeno"));
        list.add(new Grain("Bread"));
        
        for (Food f : list) {
            totalCal += f.getCalories();
            percentFat += f.getCalories() * f.percentFat();
            percentProtein += f.getCalories() * f.percentProtein();
            percentCarb += f.getCalories() * f.percentCarbohydrates();
        }
        
        percentFat /= totalCal;
        percentProtein /= totalCal;
        percentCarb /= totalCal;
        
        DecimalFormat percent = new DecimalFormat("0.00%");
        
        System.out.println("Total calories: " + totalCal
                    + "\nFat: " + percent.format(percentFat)
                    + "\nProtein: "+ percent.format(percentProtein)
                    + "\nCarbohydrates: " + percent.format(percentCarb));

    }
}