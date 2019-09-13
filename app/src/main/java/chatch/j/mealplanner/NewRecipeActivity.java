package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import chatch.j.mealplanner.Models.Recipe;

/**
 * This is the class for the XML activity_new_recipe. The activity exists
 * so that the user can input new recipes into the database and app.
 */
public class NewRecipeActivity extends AppCompatActivity
                                implements AddRecipeBulkFragment.AddRecipeBulkInteractionListener,
                                            AddIngredientsFragment.OnAddIngredientsInteractionListener,
                                            AddDirectionsFragment.OnAddDirectionsInteractionListener{
    private Toolbar toolbar;

    // New Recipe to be added
    // Values of Recipe will change throughout the fragments
    // If final finish button is clicked then this object will be added to the database
    private Recipe newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        newRecipe = new Recipe();

        // Set the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.newRecipeActivityTitle);

        // Add the back button to the toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        // When the back button is clicked, return to the main activity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                // Start activity if you want to start at the beginning part of main activity
                // Otherwise keep finish() only
                // startActivity(intent);
                finish();
            }
        });

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.newRecipeRootLayout, new AddRecipeBulkFragment());
                ft.commit();
            }
        }
    }

    /**
     * Defines the action that takes place when the Next button is clicked in
     * the AddRecipeBulk fragment.
     * When Next button is appropriately clicked (check in the fragment class),
     * take in the values for the new Recipe and add them to the Recipe object in NewRecipeActivity
     * and switch fragments from the AddRecipeBulkFragment to the AddIngredientsFragment
     */
    @Override
    public void onBulkNextClicked(String name, String creator, int time, Recipe.Difficulty diff, Recipe.Category cat) {
        // Take the given values, presumably entered by the user for the new Recipe,and set them as values for the Recipe object
        newRecipe.setTitle(name);
        newRecipe.setCreator(creator);
        newRecipe.setCookTime(time);
        newRecipe.setDifficulty(diff);
        newRecipe.setCategory(cat);

        // Change to the AddIngredientsFragment
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.newRecipeRootLayout, new AddIngredientsFragment());
                ft.commit();
            }
        }
    }

    /**
     * This is the method that allows communication from the AddIngredientsFragment to
     * the AddRecipeBulkFragment. Specifically used when the previous button is clicked, this
     * method makes the transition back to the AddRecipeBulkFragment.
     */
    @Override
    public void previousButtonClicked() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.newRecipeRootLayout, new AddRecipeBulkFragment());
                ft.commit();
            }
        }
    }

    /**
     * This is the method that allows communication from the AddIngredientsFragment to the
     * AddDirectionsFragment. Specifically used when the next button is clicked, this method makes
     * the transition to the AddDirectionsFragment.
     */
    @Override
    public void nextButtonClicked() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.newRecipeRootLayout, new AddDirectionsFragment());
                ft.commit();
            }
        }
    }

    /**
     * This is the method that allows communication from the AddDirectionsFragment to
     * the AddIngredientsFragment. Specifically used when the previous button is clicked, this method
     * makes the transition back to the AddIngredientsFragment.
     */
    @Override
    public void onDirectionsPreviousClicked() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if(fragmentManager != null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if(ft != null){
                ft.replace(R.id.newRecipeRootLayout, new AddIngredientsFragment());
                ft.commit();
            }
        }
    }

    /**
     * This method is used in the AddDirectionsFragment to allow communication between the
     * AddDirectionsFragment, the MainActivity, and the Firebase database. When called, this
     * method will create a Recipe object, store it in the Firebase database, and return to
     * the MainActivity.
     */
    @Override
    public void onDirectionsFinishClicked() {

    }
}
