package chatch.j.mealplanner.Models;

/**
 * Class that stores the information for a single ingredient.
 * The information stored includes the ingredient amount, measurement
 * type, and the ingredient name.
 */
public class Ingredient{
    private int mAmount;
    private Type mType;
    private String mName;

    public enum Type {WHOLE, TSP,TBSP, CUP, QT, LB, GAL, PT}

    /**
     * Empty constructor that sets the default values for the class's attributes
     */
    public Ingredient(){
        mAmount = 0;
        mType = Type.WHOLE;
        mName = "";
    }

    /**
     * Full constructor that uses the mutator methods to change the instance variables
     * while checking for valid input.
     * @param amount    Amount of an ingredient that is needed. Cannot be negative.
     * @param type  Measurement type used for the ingredient.
     * @param name  Name of the current ingredient
     */
    public Ingredient(int amount, Type type, String name){
        this.setAmount(amount);
        this.setType(type);
        this.setName(name);
    }

    /**
     * Mutator method that sets the value of the instance variable mAmount
     * to the new amount given if it is greater than 0. If not, it is set to
     * the default value of 0.
     * @param newAmount Amount of an ingredient required
     */
    public void setAmount(int newAmount){
        if(newAmount > 0){
            mAmount = newAmount;
        } else{
            mAmount = 0;
        }
    }

    /**
     * Accessor method that returns the value of mAmount, also known
     * as the amount of a single ingredient needed.
     * @return  mAmount
     */
    public int getAmount(){
        return mAmount;
    }

    public void setType(Type newType){

    }

    public void setName(String newName){

    }
}