package chatch.j.mealplanner.Models;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Recipe class represents a single recipe
 * Required:
 *  - Recipe Title
 *  - Ingredients
 *  - Cooking Directions
 *
 *  Optional:
 *  - Recipe Creator
 *  - Cook Time
 *  - Difficulty
 */
public class Recipe {
    // Required components to a recipe
    private String mTitle;
    private ArrayList<String> mIngredients;
    private ArrayList<String> mDirections;

    // Optional components to a recipe
    private String mCreator;
    private Difficulty mDifficulty;
    private int mCookTime;

    // enum to represent recipe difficulty
    private enum Difficulty {NONE, EASY, MEDIUM, HARD}

    /**
     * Empty construtor that exists mainly for testing purposes
     * All instance variables are set to their default values
     */
    public Recipe(){
        mTitle = "";
        mIngredients = new ArrayList<String>();
        mDirections = new ArrayList<String>();
        mCreator = "";
        mDifficulty = Difficulty.NONE;
        mCookTime = 0;
    }

    /**
     * This constructor is for only receiving the required elements of a recipe
     * @param title Initial title of current recipe
     * @param ingredients   List of initial ingredients for current recipe
     * @param directions    List of directions for current recipe
     */
    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> directions) {
        this.setTitle(title);
        this.setIngredients(ingredients);
        this.setDirections(directions);
        mCreator = "";
        mDifficulty = Difficulty.NONE;
        mCookTime = 0;
    }

    /**
     * This constructor receives the required elements for a recipe plus the name of
     * the recipe creator
     * @param title Initial title of current recipe
     * @param ingredients   List of initial ingredients for current recipe
     * @param directions    List of initial directions for current recipe
     * @param creator   Name of creator of current recipe
     */
    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> directions, String creator) {
        this.setTitle(title);
        this.setIngredients(ingredients);
        this.setDirections(directions);
        this.setCreator(creator);
        mDifficulty = Difficulty.NONE;
        mCookTime = 0;
    }

    /**
     * This constructor receives the required elements for a recipe plus
     * the level of difficulty for the recipe
     * @param title Initial title of current recipe
     * @param ingredients   List of initial ingredients for current recipe
     * @param directions    List of initial directions for current recipe
     * @param difficulty    Level of difficulty for current recipe
     */
    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> directions, Difficulty difficulty) {
        this.setTitle(title);
        this.setIngredients(ingredients);
        this.setDirections(directions);
        this.setDifficulty(difficulty);
        mCreator = "";
        mCookTime = 0;
    }

    /**
     * This constructor receives the required elements for a recipe
     * plus the cook time for the recipe
     * @param title Initial title of current recipe
     * @param ingredients   List of initial ingredients for current recipe
     * @param directions    List of initial directions for current recipe
     * @param cookTime  Time it takes to make the recipe in minutes
     */
    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> directions, int cookTime) {
        this.setTitle(title);
        this.setIngredients(ingredients);
        this.setDirections(directions);
        this.setCookTime(cookTime);
        mCreator = "";
        mDifficulty = Difficulty.NONE;
    }

    /**
     * This constructor receives elements for a recipe
     * which includes both required and optional components
     * @param title Initial title of current recipe
     * @param ingredients   List of initial ingredients for current recipe
     * @param directions    List of initial directions for current recipe
     * @param creator   Name of creator of current recipe
     * @param difficulty    Level of difficulty for current recipe
     * @param cookTime  Time it takes to make the recipe in minutes
     */
    public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> directions,
                  String creator, Difficulty difficulty, int cookTime) {
        this.setTitle(title);
        this.setIngredients(ingredients);
        this.setDirections(directions);
        this.setCookTime(cookTime);
        this.setCreator(creator);
        this.setDifficulty(difficulty);
    }

    /**
     * This method returns the title of the recipe
     * @return  mTitle  The title of the current recipe
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * This method sets the title of the recipe to the given title
     * but fixes capital and lowercase issues first
     * @param title new title for recipe
     */
    public void setTitle(String title) {
        title = fixTitle(title);
        mTitle = title;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        mIngredients = ingredients;
    }

    public ArrayList<String> getDirections() {
        return mDirections;
    }

    public void setDirections(ArrayList<String> directions) {
        mDirections = directions;
    }

    public String getCreator() {
        return mCreator;
    }

    public void setCreator(String creator) {
        mCreator = creator;
    }

    public Difficulty getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        mDifficulty = difficulty;
    }

    public int getCookTime() {
        return mCookTime;
    }

    public void setCookTime(int cookTime) {
        mCookTime = cookTime;
    }

    private String fixSentence(String sentence){
        Scanner scan = new Scanner(sentence);

        return sentence;
    }

    private String fixTitle(String title){
        return title;
    }
}
