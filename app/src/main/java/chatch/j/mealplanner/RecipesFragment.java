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

/**
 * The RecipesFragment class is the class for the recipes
 * fragment that is shown on the root layout when the
 * "Recipes" button on the bottom navigation view is clicked
 */
public class RecipesFragment extends Fragment {

    private RecyclerView recipesRecyclerView;
    private LinearLayoutManager recipeLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        // Connect recycler view to the xml
        recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
        recipeLayoutManager = new LinearLayoutManager(this.getActivity());

        // Attach Layout Manager
        recipesRecyclerView.setLayoutManager(recipeLayoutManager);

        // Inflate the layout for this fragment
        return view;

    }
}
