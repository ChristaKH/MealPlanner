package chatch.j.mealplanner.Models;

/**
 * Class that stores the information for a single ingredient.
 * The information stored includes the ingredient amount, measurement
 * type, and the ingredient name.
 */
public class Ingredient{
    private int mAmount;
    private Type mType;

    public enum Type {WHOLE, TSP,TBSP, CUP, QT, LB, GAL, PT}
}