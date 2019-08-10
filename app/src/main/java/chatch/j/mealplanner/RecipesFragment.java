package chatch.j.mealplanner;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import chatch.j.mealplanner.Models.Recipe;

/**
 * The RecipesFragment class is the class for the recipes
 * fragment that is shown on the root layout when the
 * "Recipes" button on the bottom navigation view is clicked
 */
public class RecipesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        return view;
    }
}
