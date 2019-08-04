package chatch.j.mealplanner;

import org.junit.Test;

import chatch.j.mealplanner.Models.Recipe;

import static org.junit.Assert.*;

public class RecipeTest {
    private Recipe recipe;

    @Test
    public void testSetTitle(){
        recipe = new Recipe();
        String messedUpTitle = "title 1";
        recipe.setTitle(messedUpTitle);
        assertEquals("Title 1", recipe.getTitle());
    }

    @Test
    public void testGetTitle(){
        recipe = new Recipe();
        //recipe.setTitle("1");
        assertEquals("", recipe.getTitle());
    }
}
