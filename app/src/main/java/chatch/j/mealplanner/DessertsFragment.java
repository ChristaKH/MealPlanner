package chatch.j.mealplanner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Subclass of the Fragment class that will be used to display
 * a list of recipes whose category falls under "Desserts"
 */
public class DessertsFragment extends Fragment {


    public DessertsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desserts, container, false);
    }

}
