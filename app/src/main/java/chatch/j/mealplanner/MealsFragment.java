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
 * a list of recipes whose category is "Meal". This fragment uses
 * a RecyclerView to allow a long list of recipes to be scrolled through.
 */
public class MealsFragment extends Fragment {

    private RecyclerView mealsRecyclerView;
    private TextView noMealsTextView;
    private RecipeRecyclerViewAdapter adapter;

    private ArrayList<Recipe> allRecipes;
    private ArrayList<Recipe> meals;
    private DBHelper db;

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize the database
        db = new DBHelper(getContext());
        meals = new ArrayList<Recipe>();
        allRecipes = new ArrayList<Recipe>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        adapter = new RecipeRecyclerViewAdapter(meals);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        noMealsTextView = view.findViewById(R.id.noMealsTextView);

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(adapter);
        mealsRecyclerView.setHasFixedSize(true);

        // load all Recipe objects from the database
        allRecipes = (ArrayList<Recipe>)db.getAllRecipes();

        // Separate all Recipe objects that have MEAL as their category
        Recipe tempRecipe;
        for(int i = 0; i < allRecipes.size(); i++){
            tempRecipe = allRecipes.get(i);
            if(tempRecipe.getCategory() == Recipe.Category.MEAL){
                meals.add(tempRecipe);
            }
        }

        // If there are MEAL recipes display message to indicate that
        if(meals.size() == 0){
            noMealsTextView.setVisibility(View.VISIBLE);
        } else{
            noMealsTextView.setVisibility(View.INVISIBLE);
        }

        adapter.notifyDataSetChanged();

        // prepareTestData();

        return view;
    }


    /**
     * This method exists solely for the purpose of testing the RecyclerView of the meals
     * fragment. It will populate the Recycler with 10 empty Recipe objects to be displayed.
     */
    public void prepareTestData(){
        Recipe temp = new Recipe();
        for(int i = 0; i < 10; i++){
            meals.add(temp);
        }
        adapter.notifyDataSetChanged();
    }
}
