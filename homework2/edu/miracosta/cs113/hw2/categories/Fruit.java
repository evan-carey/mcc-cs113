package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class Fruit extends Food {

    private final double FRU_PROTEIN_CAL = 0.0;
    private final double FRU_FAT_CAL = 0.0;
    private final double FRU_CARBO_CAL = 60.0;
    
    public Fruit(String name) {
        this.name = name;
        setCalories(FRU_PROTEIN_CAL + FRU_FAT_CAL + FRU_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return FRU_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return FRU_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return FRU_CARBO_CAL / getCalories();
    }

}
