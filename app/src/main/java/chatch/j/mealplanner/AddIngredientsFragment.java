package chatch.j.mealplanner;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import chatch.j.mealplanner.Models.Ingredient;

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

    private String[] meas = {"   ", "TSP", "TBSP", "CUP", "QT", "OZ", "LB","GAL", "PT"};

    //Components of the xml
    private ArrayList<String> measurements;
    private EditText ingredientAmountEditText;
    private EditText ingredientNameEditText;
    private Button addIngredientButton;
    private RecyclerView ingredientsRecyclerView;
    private Button previousButton;
    private Button nextButton;
    private Spinner measurementTypeSpinner;

    private int amount;
    private String type;
    private String name;
    private Ingredient ingredient;
    private ArrayList<Ingredient> ingredients;

    private OnAddIngredientsInteractionListener mListener;

    public AddIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ingredient = new Ingredient();
        ingredients = new ArrayList<Ingredient>();

        measurements = new ArrayList<String>();
        for(int i = 0; i < meas.length; i++){
            measurements.add(meas[i]);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ingredients, container, false);

        // Connect to components of the xml
        ingredientAmountEditText = view.findViewById(R.id.ingredientAmountEditText);
        ingredientNameEditText = view.findViewById(R.id.ingredientNameEditText);
        addIngredientButton = view.findViewById(R.id.addIngredientButton);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        previousButton = view.findViewById(R.id.previousButton);
        nextButton = view.findViewById(R.id.nextButton);
        measurementTypeSpinner = view.findViewById(R.id.measurementTypeSpinner);

        // Set up the spinner with the proper values
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, measurements);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurementTypeSpinner.setAdapter(dataAdapter);

        // Set the onClickListener for the spinner
        measurementTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinnerValue = adapterView.getItemAtPosition(i).toString();

                // {"   ", "TSP", "TBSP", "CUP", "QT", "OZ", "LB","GAL", "PT"}
                // Set the ingredient measurement type based on the selected spinner value
                switch(spinnerValue){
                    case "   ":
                        ingredient.setType(Ingredient.Type.WHOLE);
                        type = meas[0];
                        break;
                    case "TSP":
                        ingredient.setType(Ingredient.Type.TSP);
                        type = meas[1];
                        break;
                    case "TBSP":
                        ingredient.setType(Ingredient.Type.TBSP);
                        type = meas[2];
                        break;
                    case "CUP":
                        ingredient.setType(Ingredient.Type.CUP);
                        type = meas[3];
                        break;
                    case "QT":
                        ingredient.setType(Ingredient.Type.QT);
                        type = meas[4];
                        break;
                    case "OZ":
                        ingredient.setType(Ingredient.Type.QT);
                        type = meas[5];
                        break;
                    case "LB":
                        ingredient.setType(Ingredient.Type.LB);
                        type = meas[6];
                        break;
                    case "GAL":
                        ingredient.setType(Ingredient.Type.GAL);
                        type = meas[7];
                        break;
                    case "PT":
                        ingredient.setType(Ingredient.Type.PT);
                        type = meas[8];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // If the add button is clicked,
        // Check to see that all values were filled out, if not, display error message
        // If all values are filled out,
        // form an Ingredient object using the given values,
        // add it to the ArrayList of ingredients,
        // update RecyclerView with new ingredient,
        // prepare for new ingredient by reinitializing the ingredient object,
        // empty all fields for the user,
        // and make any error messages invisible

        // If the previous button is clicked, return to the AddRecipeBulkFragment
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.previousButtonClicked();
            }
        });

        // If next button is clicked, move on to the AddDirectionsFragment
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.nextButtonClicked();
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
        void nextButtonClicked();
    }
}
