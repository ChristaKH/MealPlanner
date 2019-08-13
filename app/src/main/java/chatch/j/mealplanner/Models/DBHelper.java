package chatch.j.mealplanner.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String FIELD_NAME = "recipe_title";
    private static final String FIELD_INGREDIENTS = "ingredients";
    private static final String FIELD_DIRECTIONS = "directions";
    private static final String FIELD_CREATOR = "creator_name";
    private static final String FIELD_COOK_TIME = "cook_time";
    private static final String FIELD_DIFFICULTY = "difficulty";
    private static final String FIELD_IMAGE_NAME = "image_name";
    private static final String FIELD_CATEGORY = "category";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
