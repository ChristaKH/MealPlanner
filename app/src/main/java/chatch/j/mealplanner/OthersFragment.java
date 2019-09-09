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
 * a list of recipes with the category of "Others"
 * Will display a message when there are no Other Recipes in
 * order to indicate so.
 */
public class OthersFragment extends Fragment {


    // Components of the xml
    private RecyclerView othersRecyclerView;
    private TextView noOthersTextView;

    // Adapter for the RecyclerView
    private RecipeRecyclerViewAdapter adapter;

    // Database related
    private DBHelper db;
    private ArrayList<Recipe> others;
    private ArrayList<Recipe> allRecipes;

    public OthersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_others, container, false);

        // Initialize database related objects
        db = new DBHelper(getContext());
        others = new ArrayList<Recipe>();
        allRecipes = (ArrayList<Recipe>) db.getAllRecipes();

        // Connect to the xml components
        noOthersTextView = view.findViewById(R.id.noOthersTextView);
        othersRecyclerView = view.findViewById(R.id.othersRecyclerView);

        // Initialize and attack adapter for the RecyclerView
        adapter = new RecipeRecyclerViewAdapter(others);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        othersRecyclerView.setLayoutManager(layoutManager);
        othersRecyclerView.setAdapter(adapter);
        othersRecyclerView.setHasFixedSize(true);

        // Separate out all Recipes with the category of OTHER and add to the
        // others ArrayList
        Recipe tempRecipe;
        for (int i = 0; i < allRecipes.size(); i++) {
            tempRecipe = allRecipes.get(i);
            if (tempRecipe.getCategory() == Recipe.Category.OTHER) {
                others.add(tempRecipe);
            }
        }

        // If there are no other recipes then display the message to indicate so
        if (others.size() == 0) {
            noOthersTextView.setVisibility(View.VISIBLE);
        } else {
            noOthersTextView.setVisibility(View.INVISIBLE);
        }

        return view;
    }

}
