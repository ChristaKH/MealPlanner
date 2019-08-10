package chatch.j.mealplanner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A subclass of the Fragment class that will be used to display
 * a list of recipes whose category is "Meal". This fragment uses
 * a RecyclerView to allow a long list of recipes to be scrolled through.
 */
public class MealsFragment extends Fragment {

    private RecyclerView mealsRecyclerView;

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meals, container, false);

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);

        return view;
    }

}
