package edu.miracosta.cs113.hw2;

/** Abstract class for an item of food */
public abstract class Food {
    
    // Data FieldS
    private double calories = 0.0;
    protected String name = "";

    // Abstract Methods
    
    /** Calculates the percent of protein in a Food object */
    public abstract double percentProtein();

    /** Calculates the percent of fat in a Food object */
    public abstract double percentFat();

    /** Calculates the percent of carbohydrates in a Food object */
    public abstract double percentCarbohydrates();

    // Actual Methods
    
    /** Get the calories.
     * @return calories
     */
    public double getCalories() {
        return calories;
    }

    /** Set the calories.
     * @param cal
     */
    public void setCalories(double cal) {
        calories = cal;
    }

    @Override
    public String toString() {
        return name + " (" + getCalories() + " cals) "
        		+ "\n\tPercent protein: " + percentProtein() 
                + "\n\tPercent fat: " + percentFat() 
                + "\n\tPercent carbohydrates: " + percentCarbohydrates() + "\n";
    }
}
