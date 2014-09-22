package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class Grain extends Food {

    private final double GRA_PROTEIN_CAL = 10.0;
    private final double GRA_FAT_CAL = 10.0;
    private final double GRA_CARBO_CAL = 60.0;
    
    public Grain(String name) {
        this.name = name;
        setCalories(GRA_PROTEIN_CAL + GRA_FAT_CAL + GRA_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return GRA_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return GRA_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return GRA_CARBO_CAL / getCalories();
    }

}
