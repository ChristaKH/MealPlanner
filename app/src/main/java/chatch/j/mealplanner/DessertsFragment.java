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
 * Subclass of the Fragment class that will be used to display
 * a list of recipes whose category falls under "Desserts".
 * Will display a message when there are no Dessert Recipes in
 * order to indicate so.
 */
public class DessertsFragment extends Fragment {

    // Components of the xml
    private RecyclerView dessertsRecyclerView;
    private TextView noDessertsTextView;

    // Adapter for the RecyclerView
    private RecipeRecyclerViewAdapter adapter;

    // Database related
    private DBHelper db;
    private ArrayList<Recipe> desserts;
    private ArrayList<Recipe> allRecipes;

    public DessertsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desserts, container, false);

        // Initialize database related objects
        db = new DBHelper(getContext());
        desserts = new ArrayList<Recipe>();
        allRecipes = (ArrayList<Recipe>)db.getAllRecipes();

        // Connect to the xml components
        noDessertsTextView = view.findViewById(R.id.noDessertsTextView);
        dessertsRecyclerView = view.findViewById(R.id.dessertsRecyclerView);

        // Initialize and attack adapter for the RecyclerView
        adapter = new RecipeRecyclerViewAdapter(desserts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        dessertsRecyclerView.setLayoutManager(layoutManager);
        dessertsRecyclerView.setAdapter(adapter);
        dessertsRecyclerView.setHasFixedSize(true);

        // Separate out all Recipes with the category of DESSERT and add to the
        // desserts ArrayList
        Recipe tempRecipe;
        for(int i = 0; i < allRecipes.size(); i++){
            tempRecipe = allRecipes.get(i);
            if(tempRecipe.getCategory() == Recipe.Category.DESSERT){
                desserts.add(tempRecipe);
            }
        }

        // If there are no dessert recipes then display the message to indicate so
        if(desserts.size() == 0){
            noDessertsTextView.setVisibility(View.VISIBLE);
        } else{
            noDessertsTextView.setVisibility(View.INVISIBLE);
        }

        return view;
    }

}
