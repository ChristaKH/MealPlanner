package chatch.j.mealplanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddIngredientsInteractionListener} interface
 * to handle interaction events.
 *
 * This fragment is used to enter the ingredients that are used in a new recipe.
 * The user will be able to select the measurement type (tbs, cup, etc.) and add the
 * ingredient name and amount. Once added, the ingredient will show in the RecyclerView below
 * and the user will be able to enter another ingredient if they wish. The user will also be able to
 * delete an ingredient id they wish.
 */
public class AddIngredientsFragment extends Fragment {

    private String[] measurements = {"--", "TSP", "TBSP", "CUP", "QT", "OZ", "LB","GAL", "PT"};

    //Components of the xml
    private Button previousButton;

    private OnAddIngredientsInteractionListener mListener;

    public AddIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ingredients, container, false);
        previousButton = view.findViewById(R.id.previousButton);

        // If the previous button is clicked, return to the AddRecipeBulkFragment
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.previousButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddIngredientsInteractionListener) {
            mListener = (OnAddIngredientsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddIngredientsInteractionListener");
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
    public interface OnAddIngredientsInteractionListener {
        void previousButtonClicked();
    }
}
