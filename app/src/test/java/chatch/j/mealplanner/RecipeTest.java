package chatch.j.mealplanner;

/**
 * This is the test class for the Recipe class in the Models folder
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import chatch.j.mealplanner.Models.Recipe;

import static org.junit.Assert.*;

public class RecipeTest {
    private Recipe testRecipe, expectedRecipe;
    public static String[] testTitles = {" ", "garlic bread", "cHiCkEn PoT pIe"};
    public static String[] expectedTitles = {"", "Garlic Bread", "Chicken Pot Pie"};
    public static ArrayList<String> tIngredients = new ArrayList<String>();
    public static ArrayList<String> eIngredients = new ArrayList<String>();
    public static String[] testIngredients = {" CHICKEN ", " OnIoNs", "pie crust"};
    public static String[] expectedIngredients = {"Chicken", "Onions", "Pie Crust"};
    public static String[] testDirections = {"          ", " this is test. number! One. ", "THIS IS. TEST? NUMBER TWO..." };
    public static String[] expectedDirections = {"", "This is test. Number! One.", "This is. Test? Number two..."};
    public static String[] testCreator = {"    ", " JAKE TAYLOR ", " cHrIsTa HaTcH"};
    public static String[] expectedCreator = {"", "Jake Taylor", "Christa Hatch"};
    public static Recipe.Difficulty[] testDifficulties = {Recipe.Difficulty.NONE, Recipe.Difficulty.EASY,
                                                            Recipe.Difficulty.MEDIUM, Recipe.Difficulty.HARD};
    public static Recipe.Difficulty[] expectedDifficulties = {Recipe.Difficulty.NONE, Recipe.Difficulty.EASY,
            Recipe.Difficulty.MEDIUM, Recipe.Difficulty.HARD};
    public static int[] testCookTimes = {-1, 0, 100};
    public static int[] expectedCookTimes = {0, 0, 100};


    /**
     * Before testing each method, create a completely blank Recipe object
     */
    @Before
    public void createRecipe(){
        testRecipe = new Recipe();
        expectedRecipe = new Recipe();

        for(int i = 0; i < expectedIngredients.length; i++){
            tIngredients.add(testIngredients[i]);
            eIngredients.add(expectedIngredients[i]);
        }
    }

    /**
     * Method for testing the getTitle() method of class Recipe
     */
    @Test
    public void testGetTitle(){
        for(int i = 0; i < expectedTitles.length; i++){
            testRecipe.setTitle(expectedTitles[i]);
            assertEquals(expectedTitles[i], testRecipe.getTitle());
        }
    }

    /**
     * Method for testing the setTitle() method of class Recipe
     */
    @Test
    public void testSetTitle(){
        for(int i = 0; i < testTitles.length; i++){
            testRecipe.setTitle(testTitles[i]);
            assertEquals(expectedTitles[i], testRecipe.getTitle());
        }
    }

    /**
     * Method for testing the getIngredients() method
     */
    @Test
    public void testGetIngredients(){
        testRecipe.setIngredients(eIngredients);
        ArrayList<String> tempIngredients = testRecipe.getIngredients();
        for(int i = 0; i < tempIngredients.size(); i++){
            assertEquals(expectedIngredients[i], tempIngredients.get(i));
        }
    }

    /**
     * Method for testing the setIngredients() method
     */
    @Test
    public void testSetIngredients(){
        testRecipe.setIngredients(tIngredients);
        ArrayList<String> tempIngredients = testRecipe.getIngredients();
        for(int i = 0; i < tempIngredients.size(); i++){
            assertEquals(expectedIngredients[i], tempIngredients.get(i));
        }
    }
}
