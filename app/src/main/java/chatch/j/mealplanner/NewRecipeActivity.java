package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import chatch.j.mealplanner.Models.Recipe;

/**
 * This is the class for the XML activity_new_recipe. The activity exists
 * so that the user can input new recipes into the database and app.
 */
public class NewRecipeActivity extends AppCompatActivity{
    private Toolbar toolbar;

    // New Recipe to be added
    // Values of Recipe will change throughout the fragments
    // If final finish button is clicked then this object will be added to the database
    public static Recipe newRecipe;

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
}
