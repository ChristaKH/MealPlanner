package chatch.j.mealplanner;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import chatch.j.mealplanner.Adapters.RecipeRecyclerViewAdapter;
import chatch.j.mealplanner.Models.Recipe;

/**
 * The RecipesFragment class is the class for the recipes
 * fragment that is shown on the root layout when the
 * "Recipes" button on the bottom navigation view is clicked
 */
public class RecipesFragment extends Fragment {

    private RecyclerView recipesRecyclerView;
    private LinearLayoutManager recipeLayoutManager;

    private ArrayList<Recipe> breakfastRecipes;
    private ArrayList<Recipe> lunchRecipes;
    private ArrayList<Recipe> dinnerRecipes;
    private ArrayList<Recipe> dessertRecipes;
    private ArrayList<Recipe> otherRecipes;
    private ArrayList<ArrayList<Recipe>> allRecipes;
    private RecipeRecyclerViewAdapter mRecipeRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        // Connect recycler view to the xml
        recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
        recipeLayoutManager = new LinearLayoutManager(this.getActivity());

        // Attach Layout Manager
        recipesRecyclerView.setLayoutManager(recipeLayoutManager);

        // Initialize all recipe array lists
        breakfastRecipes = new ArrayList<Recipe>();
        lunchRecipes = new ArrayList<Recipe>();
        dinnerRecipes = new ArrayList<Recipe>();
        dessertRecipes = new ArrayList<Recipe>();
        otherRecipes = new ArrayList<Recipe>();
        allRecipes = new ArrayList<ArrayList<Recipe>>();

        // Fill recipe array lists with dummy values for now
        // @TODO fill array lists with appropriate values based on actual recipes
        Recipe tempRecipe = new Recipe();
        for(int i = 0; i < 10; i++){
            tempRecipe.setCategory(Recipe.Category.BREAKFAST);
            breakfastRecipes.add(tempRecipe);

            tempRecipe.setCategory(Recipe.Category.LUNCH);
            lunchRecipes.add(tempRecipe);

            tempRecipe.setCategory(Recipe.Category.DINNER);
            dinnerRecipes.add(tempRecipe);

            tempRecipe.setCategory(Recipe.Category.OTHER);
            otherRecipes.add(tempRecipe);
        }

        // Fill allRecipes with the recipes based on category
        allRecipes.add(breakfastRecipes);
        allRecipes.add(lunchRecipes);
        allRecipes.add(dinnerRecipes);
        allRecipes.add(dessertRecipes);
        allRecipes.add(otherRecipes);

        // initialize the recycler view adapter for the vertical (outer) recycler view
        mRecipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this.getActivity(), allRecipes);

        // Define layout manager for vertical recycler view in recipe fragment
        recipeLayoutManager = new LinearLayoutManager(this.getActivity(), RecyclerView.VERTICAL, false);

        // Setup main(vertical) recycler view in the recipes fragment
        recipesRecyclerView.setHasFixedSize(true);
        recipesRecyclerView.setLayoutManager(recipeLayoutManager);
        recipesRecyclerView.setAdapter(mRecipeRecyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}
