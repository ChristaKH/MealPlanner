package chatch.j.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import chatch.j.mealplanner.Adapters.RecipeRecyclerViewAdapter;
import chatch.j.mealplanner.Models.Recipe;

public class MainActivity extends AppCompatActivity {

    // @TODO Create a Recipes class to hold many recipes
    // @TODO Create a sorting method in the Recipes class
    // @TODO Use an array list of Recipes instead of an array list of array lists (here and in adapters)
    private Toolbar toolbar;
    private BottomNavigationView mainBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the components of the xml to the main activity
        toolbar = findViewById(R.id.toolbar);
        mainBottomNavigationView = (BottomNavigationView) findViewById(R.id.mainBottomNavigationView);

        // Attach the toolbar
        setSupportActionBar(toolbar);

        // Set a default item to be selected and perform actions when items are clicked
        setupNavigationView();
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

        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}
