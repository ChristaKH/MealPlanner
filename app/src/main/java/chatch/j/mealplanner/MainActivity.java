package chatch.j.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import chatch.j.mealplanner.Models.DBHelper;

public class MainActivity extends AppCompatActivity {

    // @TODO Create a sorting method in the Recipes class
    private Toolbar toolbar;
    private BottomNavigationView mainBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);
        // Connect the components of the xml to the main activity
        toolbar = findViewById(R.id.toolbar);
        mainBottomNavigationView = (BottomNavigationView) findViewById(R.id.mainBottomNavigationView);

        // Attach the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        // Set a default item to be selected and perform actions when items are clicked
        setupNavigationView();
    }

    /**
     * This method will allow us to add the menu items
     * to the toolbar, specifically the "add new recipe" button
     * @param menu  Menu of buttons that was made for the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_recipe_menu, menu);

        return true;
    }

    /**
     * This method performs an action when the toolbar menu
     * item is selected. Specifically, this reacts when the
     * add button is clicked and will bring up the activity for
     * entering a new Recipe.
     * @param menuItem selected menu item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.addMenuItem:
                // When the add button on the toolbar is selected
                // Change to the NewRecipeActivity in order to input
                // a new Recipe
                Intent newIntent = new Intent(this, NewRecipeActivity.class);
                startActivity(newIntent);
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    /**
     * This method is used to put together the bottom navigation view
     */
    private void setupNavigationView(){

        // Give the bottom navigation tabs action when clicked
        if(mainBottomNavigationView != null){

            // Select the first menu item by default and show proper fragment (TodayFragment)
            Menu menu = mainBottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when menu item is selected
            mainBottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            selectFragment(menuItem);
                            return false;
                        }
                    }
            );
        }
    }

    /**
     * Perform action when any menu item is selected
     * @param item item on menu that has been selected
     */
    protected void selectFragment(MenuItem item){
        item.setChecked(true);

        switch(item.getItemId()){
            case R.id.todayMenuItem:
                // Action to perform when Today menu item is selected
                pushFragment(new TodayFragment());
                break;
            case R.id.calendarMenuItem:
                // Action to perform when Calendar menu item is selected
                pushFragment(new CalendarFragment());
                break;
            case R.id.recipesMenuItem:
                //Action to perform when Recipes menu item is selected
                pushFragment(new RecipesFragment());
                break;
        }
    }

    /**
     * Method to push any fragment into given id
     * @param fragment an instance of a fragment to show into
     *                 the given id
     */
    protected void pushFragment(Fragment fragment){
        if(fragment == null){
            return;
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}
