package chatch.j.mealplanner;

import android.app.FragmentManager;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import chatch.j.mealplanner.Adapters.RecipesViewPagerAdapter;

/**
 * The RecipesFragment class is the class for the recipes
 * fragment that is shown on the root layout when the
 * "Recipes" button on the bottom navigation view is clicked
 */
public class RecipesFragment extends Fragment {

    private ViewPager recipesViewPager;
    private RecipesViewPagerAdapter mRecipesViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        recipesViewPager = view.findViewById(R.id.recipesViewPager);
        mRecipesViewPagerAdapter = new RecipesViewPagerAdapter(getActivity().getSupportFragmentManager());
        return view;
    }
}
