package chatch.j.mealplanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * This Fragment is used so that the user can enter the directions of a new
 * Recipe as well as load in a picture that they wish to represent the Recipe.
 */
public class AddDirectionsFragment extends Fragment {

    private OnAddDirectionsInteractionListener mListener;

    // Important components from the xml
    private Button directionsPreviousButton;
    private Button directionsFinishButton;

    private ArrayList<String>recipeDirections;
    private Bitmap recipeImage;

    public AddDirectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_directions, container, false);

        directionsPreviousButton = view.findViewById(R.id.directionsPreviousButton);
        directionsFinishButton = view.findViewById(R.id.directionsFinishButton);

        recipeDirections = new ArrayList<String>();

        // Attach listener for previous button so that it goes back to the AddIngredientsFragment
        directionsPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDirectionsPreviousClicked();
            }
        });

        // Attach listener for finish button so that it creates and stores a new Recipe object,
        // Adds Recipe data to the database,
        // And returns to the MainActivity
        directionsFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDirectionsFinishClicked(recipeDirections, recipeImage);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddDirectionsInteractionListener) {
            mListener = (OnAddDirectionsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddDirectionsInteractionListener {
        // DONE: Update argument type and name
        void onDirectionsPreviousClicked();
        void onDirectionsFinishClicked(ArrayList<String> directions, Bitmap image);
    }
}
