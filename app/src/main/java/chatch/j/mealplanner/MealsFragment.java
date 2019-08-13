package chatch.j.mealplanner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import chatch.j.mealplanner.Adapters.RecipeRecyclerViewAdapter;
import chatch.j.mealplanner.Models.Recipe;


/**
 * A subclass of the Fragment class that will be used to display
 * a list of recipes whose category is "Meal". This fragment uses
 * a RecyclerView to allow a long list of recipes to be scrolled through.
 */
public class MealsFragment extends Fragment {

    private RecyclerView mealsRecyclerView;
    private RecipeRecyclerViewAdapter adapter;

    private ArrayList<Recipe> meals = new ArrayList<Recipe>();

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        adapter = new RecipeRecyclerViewAdapter(meals);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(adapter);mealsRecyclerView.setHasFixedSize(true);

        prepareTestData();

        return view;
    }


    public void prepareTestData(){
        Recipe temp = new Recipe();
        for(int i = 0; i < 10; i++){
            meals.add(temp);
        }
        adapter.notifyDataSetChanged();
    }
}
