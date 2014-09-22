package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class Protein extends Food {

    private final double PRO_PROTEIN_CAL = 40.0;
    private final double PRO_FAT_CAL = 50.0;
    private final double PRO_CARBO_CAL = 0.0;
    
    public Protein(String name) {
        this.name = name;
        setCalories(PRO_PROTEIN_CAL + PRO_FAT_CAL + PRO_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return PRO_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return PRO_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return PRO_CARBO_CAL / getCalories();
    }

}