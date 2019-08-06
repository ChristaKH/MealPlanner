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

    /**
     * This method returns the list of ingredients for current recipe
     * @return  List of recipe ingredients
     */
    public ArrayList<String> getIngredients() {
        ArrayList<String> tempIngredients = new ArrayList<String>();
        for(int i = 0; i < mIngredients.size(); i++){
            tempIngredients.add(mIngredients.get(i));
        }
        return tempIngredients;
    }

    /**
     * Method that sets the list of ingredients for the recipe
     * @param ingredients   New list of ingredients for the recipe
     */
    public void setIngredients(ArrayList<String> ingredients) {
        String ingredient;
        mIngredients.clear();
        for(int i = 0; i < ingredients.size(); i++){
            ingredient = fixTitle(ingredients.get(i));
            mIngredients.add(ingredient);
        }
    }

    /**
     * Method that returns a list of recipe directions
     * @return  Recipe directions in an ArrayList of type String
     */
    public ArrayList<String> getDirections() {
        ArrayList<String> newDirec = new ArrayList<String>();
        for(int i = 0; i < mDirections.size(); i++){
            newDirec.add(mDirections.get(i));
        }
        return newDirec;
    }

    /**
     * Method that sets the list of directions for the recipe
     * @param directions    List of recipe directions
     */
    public void setDirections(ArrayList<String> directions) {
        mDirections.clear();
        String direction;
        for(int i = 0; i < directions.size(); i++){
            direction = fixSentence(directions.get(i));
            mDirections.add(direction);
        }
    }

    /**
     * Method that returns the name of the creator of the current recipe
     * @return  Creator name
     */
    public String getCreator() {
        return mCreator;
    }

    /**
     * Method that takes in the name of the recipe creator, fixes
     * all capitalization issues, and sets it to the creator of the recipe
     * @param creator   Name of recipe creator being entered
     */
    public void setCreator(String creator) {
        mCreator = fixTitle(creator);
    }

    /**
     * Method that returns the level of difficulty for the current recipe
     * @return  enum representing recipe difficulty
     */
    public Difficulty getDifficulty() {
        return mDifficulty;
    }

    /**
     * Method that sets the difficulty of the recipe
     * @param difficulty    Entered difficulty of recipe
     */
    public void setDifficulty(Difficulty difficulty) {
        mDifficulty = difficulty;
    }

    /**
     * Method that returns the time it takes to complete the
     * recipe in minutes
     * @return  Recipe cook time
     */
    public int getCookTime() {
        return mCookTime;
    }

    /**
     * Method that sets the time it takes to make current recipe
     * If new cook time is a negative number then set cook time to 0
     * @param cookTime  New cook time in minutes
     */
    public void setCookTime(int cookTime) {
        if(cookTime < 0){
            cookTime = 0;
        }
        mCookTime = cookTime;
    }

    /**
     * Method that receives a String and fixes the capitalization
     * of the String to be that of a sentence where the first letter
     * of every sentence is capitalized
     * @param sentence  Initial sentence
     * @return  Cleaned up sentence
     */
    private String fixSentence(String sentence){
        // Start off with all letters lowercase
        sentence = sentence.toLowerCase();

        // Put sentence into character array to prevent constant
        // recreation of a String
        char[] newSentence = sentence.toCharArray();

        // The first letter should always be capital
        // For now, we are ignoring the possibility of quotations
        newSentence[0] = Character.toUpperCase(newSentence[0]);

        // Go through the entire sentence/character array
        for(int i = 0; i < newSentence.length - 1; i++){
            // If the current character is a '.', '?', or '!'
            if(newSentence[i] == '.'|| newSentence[i] == '?' || newSentence[i] == '!'){
                // Then make the next character an uppercase letter because it is the start
                // of a new sentence
                // Again, ignoring the possibility of quotations for now
                newSentence[i+1] = Character.toUpperCase(newSentence[i+1]);
            }
        }

        // Return fixed sentence
        return newSentence.toString();
    }

    private String fixTitle(String title){
        return title;
    }
}
