package edu.miracosta.cs113.hw2.categories;

import edu.miracosta.cs113.hw2.Food;

public class Dairy extends Food {

    private final double DAI_PROTEIN_CAL = 30.0;
    private final double DAI_FAT_CAL = 50.0;
    private final double DAI_CARBO_CAL = 60.0;
    
    public Dairy(String name) {
        this.name = name;
        setCalories(DAI_PROTEIN_CAL + DAI_FAT_CAL + DAI_CARBO_CAL);
    }
    
    @Override
    public double percentProtein() {
        return DAI_PROTEIN_CAL / getCalories();
    }

    @Override
    public double percentFat() {
        return DAI_FAT_CAL / getCalories();
    }

    @Override
    public double percentCarbohydrates() {
        return DAI_CARBO_CAL / getCalories();
    }

}
