package chatch.j.mealplanner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import chatch.j.mealplanner.Adapters.RecipeRecyclerViewAdapter;
import chatch.j.mealplanner.Models.DBHelper;
import chatch.j.mealplanner.Models.Recipe;


/**
 * A subclass of the Fragment class that will be used to display
 * a list of recipes with the category of "Drink"
 * Will display a message when there are no Drink Recipes in
 * order to indicate so.
 */
public class DrinksFragment extends Fragment {

    // Components of the xml
    private RecyclerView drinksRecyclerView;
    private TextView noDrinksTextView;

    // Adapter for the RecyclerView
    private RecipeRecyclerViewAdapter adapter;

    // Database related
    private DBHelper db;
    private ArrayList<Recipe> drinks;
    private ArrayList<Recipe> allRecipes;

    public DrinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);

        // Initialize database related objects
        db = new DBHelper(getContext());
        drinks = new ArrayList<Recipe>();
        allRecipes = (ArrayList<Recipe>) db.getAllRecipes();

        // Connect to the xml components
        noDrinksTextView = view.findViewById(R.id.noDrinksTextView);
        drinksRecyclerView = view.findViewById(R.id.drinksRecyclerView);

        // Initialize and attack adapter for the RecyclerView
        adapter = new RecipeRecyclerViewAdapter(drinks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        drinksRecyclerView.setLayoutManager(layoutManager);
        drinksRecyclerView.setAdapter(adapter);
        drinksRecyclerView.setHasFixedSize(true);

        // Separate out all Recipes with the category of DRINK and add to the
        // drinks ArrayList
        Recipe tempRecipe;
        for (int i = 0; i < allRecipes.size(); i++) {
            tempRecipe = allRecipes.get(i);
            if (tempRecipe.getCategory() == Recipe.Category.DRINK) {
                drinks.add(tempRecipe);
            }
        }

        // If there are no drink recipes then display the message to indicate so
        if (drinks.size() == 0) {
            noDrinksTextView.setVisibility(View.VISIBLE);
        } else {
            noDrinksTextView.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
