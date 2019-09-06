package chatch.j.mealplanner.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that will help to store and retrieve Recipe information
 * from the database SQLite.
 */
public class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    public static final String DATABASE_NAME = "MealPlanner";
    private static final String DATABASE_TABLE = "Recipes";
    private static final int DATABASE_VERSION = 1;

    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_RECIPE_TITLE = "recipe_title";
    private static final String FIELD_INGREDIENTS = "ingredients";
    private static final String FIELD_DIRECTIONS = "directions";
    private static final String FIELD_CREATOR = "creator_name";
    private static final String FIELD_COOK_TIME = "cook_time";
    private static final String FIELD_DIFFICULTY = "difficulty";
    private static final String FIELD_IMAGE_NAME = "image_name";
    private static final String FIELD_CATEGORY = "category";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        // DONE: add field information for the array lists
        String table = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_RECIPE_TITLE + " TEXT, "
                + FIELD_INGREDIENTS + " TEXT, "
                + FIELD_DIRECTIONS + " TEXT, "
                + FIELD_CREATOR + " TEXT, "
                + FIELD_COOK_TIME + " INTEGER, "
                + FIELD_DIFFICULTY + " TEXT, "
                + FIELD_IMAGE_NAME + " TEXT, "
                + FIELD_CATEGORY +  " TEXT " +  ")";
        database.execSQL (table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //********** DATABASE OPERATIONS:  ADD, UPDATE, EDIT, DELETE

    /**
     * Method that adds the data from a Recipe object to the SQLite
     * database.
     * @param recipe    New recipe to add to the database
     */
    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE TITLE
        values.put(FIELD_RECIPE_TITLE, recipe.getTitle());

        // TODO: put info for the ingredients array list
        // TODO: put info for the directions array list

        // ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE CREATOR
        values.put(FIELD_CREATOR, recipe.getCookTime());

        // ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE COOK TIME
        values.put(FIELD_COOK_TIME, recipe.getCookTime());

        // ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE DIFFICULTY
        switch(recipe.getDifficulty()){
            case EASY:
                values.put(FIELD_DIFFICULTY, "EASY");
                break;
            case MEDIUM:
                values.put(FIELD_DIFFICULTY, "MEDIUM");
                break;
            case HARD:
                values.put(FIELD_DIFFICULTY, "HARD");
                break;
            default:
                values.put(FIELD_DIFFICULTY, "NONE");
                break;
        }

        //ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE IMAGE NAME
        values.put(FIELD_IMAGE_NAME, recipe.getImageName());

        // ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE CATEGORY
        switch(recipe.getCategory()){
            case MEAL:
                values.put(FIELD_CATEGORY, "MEAL");
                break;
            case DESSERT:
                values.put(FIELD_CATEGORY, "DESSERT");
                break;
            case DRINK:
                values.put(FIELD_CATEGORY, "DRINK");
                break;
            default:
                values.put(FIELD_CATEGORY, "NONE");
                break;
        }

        // INSERT THE ROW IN THE TABLE
        long id = db.insert(DATABASE_TABLE, null, values);

        // UPDATE THE RECIPE WITH THE NEWLY ASSIGNED ID FROM THE DATABASE
        recipe.setId(id);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    /**
     * Method that gets the recipes stored in the database and returns it in an array list
     * @return  List of all recipes from the database
     */
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipesList = new ArrayList<Recipe>();
        SQLiteDatabase database = getReadableDatabase();

        // A cursor is the results of a database query (what gets returned)
        Cursor cursor = database.query(
                DATABASE_TABLE,
                // TODO: add ingredients and directions into the query
                new String[]{KEY_FIELD_ID, FIELD_RECIPE_TITLE, FIELD_CREATOR, FIELD_COOK_TIME, FIELD_DIFFICULTY, FIELD_IMAGE_NAME, FIELD_CATEGORY},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                Recipe recipe = new Recipe();

                recipe.setId(cursor.getLong(0));
                recipe.setTitle(cursor.getString(1));
                // TODO: Set the value of ingredients
                // TODO: Set the value of the directions
                // TODO: Shift cursor numbers
                recipe.setCreator(cursor.getString(2));
                recipe.setCookTime(cursor.getInt(3));

                String difficulty = cursor.getString(4);
                if(difficulty.equalsIgnoreCase("EASY")){
                    recipe.setDifficulty(Recipe.Difficulty.EASY);
                } else if(difficulty.equalsIgnoreCase("MEDIUM")){
                    recipe.setDifficulty(Recipe.Difficulty.MEDIUM);
                } else if(difficulty.equalsIgnoreCase("HARD")){
                    recipe.setDifficulty(Recipe.Difficulty.HARD);
                } else{
                    recipe.setDifficulty(Recipe.Difficulty.NONE);
                }

                recipe.setImageName(cursor.getString(5));

                String category = cursor.getString(6);
                if(category.equalsIgnoreCase("MEAL")){
                    recipe.setCategory(Recipe.Category.MEAL);
                } else if(category.equalsIgnoreCase("DESSERT")){
                    recipe.setCategory(Recipe.Category.DESSERT);
                } else if(category.equalsIgnoreCase("DRINK")){
                    recipe.setCategory(Recipe.Category.DRINK);
                } else{
                    recipe.setCategory(Recipe.Category.OTHER);
                }

                recipesList.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return recipesList;
    }

    /**
     * Method that deletes a single recipe from the database
     * @param recipe
     */
    public void deleteRecipe(Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(recipe.getId())});
        db.close();
    }


    /**
     * This method will erase all Recipes by deleting the all of
     * the data stored in the database. This should not be used unless
     * absolutely necessary or in cases of testing.
     */
    public void deleteAllRecipes()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    /**
     * This method updates the information for a specific Recipe
     * in the database
     * @param recipe    New Recipe whose data will take the place of
     *                  the old recipe
     */
    public void updateRecipe(Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_RECIPE_TITLE, recipe.getTitle());
        // TODO: put in info for the ingredients arraylist
        // TODO: put in info for the directions arraylist
        values.put(FIELD_CREATOR, recipe.getCreator());
        values.put(FIELD_COOK_TIME, recipe.getCookTime());

        switch(recipe.getDifficulty()){
            case EASY:
                values.put(FIELD_DIFFICULTY, "EASY");
                break;
            case MEDIUM:
                values.put(FIELD_DIFFICULTY, "MEDIUM");
                break;
            case HARD:
                values.put(FIELD_DIFFICULTY, "HARD");
                break;
            default:
                values.put(FIELD_DIFFICULTY, "NONE");
                break;
        }

        switch(recipe.getCategory()){
            case MEAL:
                values.put(FIELD_CATEGORY, "MEAL");
                break;
            case DESSERT:
                values.put(FIELD_CATEGORY, "DESSERT");
                break;
            case DRINK:
                values.put(FIELD_CATEGORY, "DRINK");
                break;
            default:
                values.put(FIELD_CATEGORY, "NONE");
                break;
        }

        values.put(FIELD_IMAGE_NAME, recipe.getImageName());

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(recipe.getId())});
        db.close();
    }


    /**
     * This method retrieves a single recipe based on an id
     * @param id    Id of the Recipe that is being searched for
     * @return  Sought after Recipe
     */
    public Recipe getRecipe(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_RECIPE_TITLE, FIELD_CREATOR, FIELD_COOK_TIME, FIELD_DIFFICULTY, FIELD_IMAGE_NAME, FIELD_CATEGORY},
                KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        Recipe recipe = null;
        if (cursor != null) {
            cursor.moveToFirst();

            recipe = new Recipe();

            recipe.setId(cursor.getLong(0));
            recipe.setTitle(cursor.getString(1));
            // TODO: Set the value of ingredients
            // TODO: Set the value of the directions
            // TODO: Shift cursor numbers
            recipe.setCreator(cursor.getString(2));
            recipe.setCookTime(cursor.getInt(3));

            String difficulty = cursor.getString(4);
            if(difficulty.equalsIgnoreCase("EASY")){
                recipe.setDifficulty(Recipe.Difficulty.EASY);
            } else if(difficulty.equalsIgnoreCase("MEDIUM")){
                recipe.setDifficulty(Recipe.Difficulty.MEDIUM);
            } else if(difficulty.equalsIgnoreCase("HARD")){
                recipe.setDifficulty(Recipe.Difficulty.HARD);
            } else{
                recipe.setDifficulty(Recipe.Difficulty.NONE);
            }

            recipe.setImageName(cursor.getString(5));

            String category = cursor.getString(6);
            if(category.equalsIgnoreCase("MEAL")){
                recipe.setCategory(Recipe.Category.MEAL);
            } else if(category.equalsIgnoreCase("DESSERT")){
                recipe.setCategory(Recipe.Category.DESSERT);
            } else if(category.equalsIgnoreCase("DRINK")){
                recipe.setCategory(Recipe.Category.DRINK);
            } else{
                recipe.setCategory(Recipe.Category.OTHER);
            }

            cursor.close();
        }
        db.close();
        return recipe;
    }

}
