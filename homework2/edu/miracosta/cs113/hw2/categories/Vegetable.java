package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class Vegetable extends Food {
    
    private final double VEG_PROTEIN_CAL = 8.0;
    private final double VEG_FAT_CAL = 0.0;
    private final double VEG_CARBO_CAL = 20.0;

    public Vegetable(String name) {
        this.name = name;
        setCalories(VEG_PROTEIN_CAL + VEG_FAT_CAL + VEG_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return VEG_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return VEG_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return VEG_CARBO_CAL / getCalories();
    }

}