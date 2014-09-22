package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class FatsOilsSweets extends Food {

    private final double FOS_PROTEIN_CAL = 0.0;
    private final double FOS_FAT_CAL = 45.0;
    private final double FOS_CARBO_CAL = 0.0;
    
    public FatsOilsSweets(String name) {
        this.name = name;
        setCalories(FOS_PROTEIN_CAL + FOS_FAT_CAL + FOS_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return FOS_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return FOS_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return FOS_CARBO_CAL / getCalories();
    }

}
