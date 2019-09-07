package chatch.j.mealplanner.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that will help to store and retrieve Recipe information
 * from the database SQLite.
 */
public class DBHelper extends SQLiteOpenHelper {

    private String strSeparator = "__,__";  // Important for adding String array into table

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
    private static final String FIELD_IMAGE = "image";
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
                + FIELD_IMAGE + " BLOB, "
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

        String ingredients = convertListToString(recipe.getIngredients());
        String directions = convertListToString(recipe.getDirections());

        values.put(FIELD_INGREDIENTS, ingredients);
        // DONE: put info for the directions array list
        values.put(FIELD_DIRECTIONS, directions);

        // ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE CREATOR
        values.put(FIELD_CREATOR, recipe.getCreator());

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

        //ADD KEY-VALUE PAIR INFORMATION FOR THE RECIPE IMAGE
        values.put(FIELD_IMAGE, getBitmapAsByteArray(recipe.getImage()));

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
                // DONE: add ingredients and directions into the query
                new String[]{KEY_FIELD_ID, FIELD_RECIPE_TITLE, FIELD_INGREDIENTS, FIELD_DIRECTIONS, FIELD_CREATOR, FIELD_COOK_TIME, FIELD_DIFFICULTY, FIELD_IMAGE, FIELD_CATEGORY},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                Recipe recipe = new Recipe();

                recipe.setId(cursor.getLong(0));
                recipe.setTitle(cursor.getString(1));
                // DONE: Set the value of ingredients
                ArrayList<String> ingredients = convertStringToList(cursor.getString(2));
                recipe.setIngredients(ingredients);

                // DONE: Set the value of the directions
                ArrayList<String> directions = convertStringToList(cursor.getString(3));
                recipe.setDirections(directions);

                // DONE: Shift cursor numbers
                recipe.setCreator(cursor.getString(4));
                recipe.setCookTime(cursor.getInt(5));

                String difficulty = cursor.getString(6);
                if(difficulty.equalsIgnoreCase("EASY")){
                    recipe.setDifficulty(Recipe.Difficulty.EASY);
                } else if(difficulty.equalsIgnoreCase("MEDIUM")){
                    recipe.setDifficulty(Recipe.Difficulty.MEDIUM);
                } else if(difficulty.equalsIgnoreCase("HARD")){
                    recipe.setDifficulty(Recipe.Difficulty.HARD);
                } else{
                    recipe.setDifficulty(Recipe.Difficulty.NONE);
                }

                recipe.setImage(getBitmapFromBytes(cursor.getBlob(7)));

                String category = cursor.getString(8);
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
        // DONE: put in info for the ingredients arraylist
        values.put(FIELD_INGREDIENTS, convertListToString(recipe.getIngredients()));

        // DONE: put in info for the directions arraylist
        values.put(FIELD_DIRECTIONS, convertListToString(recipe.getDirections()));

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

        values.put(FIELD_IMAGE, getBitmapAsByteArray(recipe.getImage()));

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
                new String[]{KEY_FIELD_ID, FIELD_RECIPE_TITLE, FIELD_INGREDIENTS, FIELD_DIRECTIONS, FIELD_CREATOR, FIELD_COOK_TIME, FIELD_DIFFICULTY, FIELD_IMAGE, FIELD_CATEGORY},
                KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        Recipe recipe = null;
        if (cursor != null) {
            cursor.moveToFirst();

            recipe = new Recipe();

            recipe.setId(cursor.getLong(0));
            recipe.setTitle(cursor.getString(1));

            // DONE: Set the value of ingredients
            recipe.setIngredients(convertStringToList(cursor.getString(2)));

            // DONE: Set the value of the directions
            recipe.setDirections(convertStringToList(cursor.getString(3)));

            // DONE: Shift cursor numbers
            recipe.setCreator(cursor.getString(4));
            recipe.setCookTime(cursor.getInt(5));

            String difficulty = cursor.getString(6);
            if(difficulty.equalsIgnoreCase("EASY")){
                recipe.setDifficulty(Recipe.Difficulty.EASY);
            } else if(difficulty.equalsIgnoreCase("MEDIUM")){
                recipe.setDifficulty(Recipe.Difficulty.MEDIUM);
            } else if(difficulty.equalsIgnoreCase("HARD")){
                recipe.setDifficulty(Recipe.Difficulty.HARD);
            } else{
                recipe.setDifficulty(Recipe.Difficulty.NONE);
            }

            recipe.setImageName(cursor.getString(7));

            String category = cursor.getString(8);
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

    /**
     * This method is useful since we cannot add String arrays into the table. Both
     * the Recipe ingredients and directions come in a String array so this will convert
     * the contents of the String arrays into one long string separated by a unique string
     * of characters.
     * @param array String array that we wish to mush together into one String
     * @return  New String of mushed together values
     */
    private String convertListToString(ArrayList<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            // Do not append comma at the end of last element
            if(i<array.size()-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    /**
     * This method is useful since we cannot add String arrays into the table and we cannot
     * get String arrays back out. This method will take a String (assumed to be in the converted form)
     * and separate it based on the designated combination of characters to signal a separation of String
     * values.
     * @param str   String value that we wish to separate. It is assumed to already be in the converted form.
     * @return  String array that contains the separated contents
     */
    private ArrayList<String> convertStringToList(String str){
        String[] arr = str.split(strSeparator);

        ArrayList<String> array = new ArrayList<String>();

        for(int i = 0; i < arr.length; i++){
            array.add(arr[i]);
        }
        return array;
    }

    /**
     * This method prepares a Bitmap image to be put into the SQLite table by converting
     * a Bitmap image into a series of bytes
     * @param bitmap    Bitmap of the specific image
     * @return  Array of bytes from the converted image.
     */
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * This method takes an array of bytes and translates it to its Bitmap image
     * @param imageBytes
     * @return  Bitmap value of the entered bytes
     */
    public static Bitmap getBitmapFromBytes(byte[] imageBytes){
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}
