package chatch.j.mealplanner;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import chatch.j.mealplanner.Adapters.AddIngredientRecyclerViewAdapter;
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
    private TextView ingredientErrorTextView;

    private String amount;
    private String type = "";
    private String name;
    private Ingredient ingredient;
    private ArrayList<Ingredient> ingredients;
    private AddIngredientRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManagerRV;

    private OnAddIngredientsInteractionListener mListener;

    public AddIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ingredient = new Ingredient();
        ingredients = new ArrayList<Ingredient>();
        recyclerViewAdapter = new AddIngredientRecyclerViewAdapter(ingredients);
        layoutManagerRV = new LinearLayoutManager(getContext());

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
        ingredientErrorTextView = view.findViewById(R.id.ingredientErrorTextView);

        // Set up the spinner with the proper values
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, measurements);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurementTypeSpinner.setAdapter(dataAdapter);

        // Set up the RecyclerView with a LayoutManager and adapter
        ingredientsRecyclerView.setLayoutManager(layoutManagerRV);
        ingredientsRecyclerView.setAdapter(recyclerViewAdapter);
        ingredientsRecyclerView.setHasFixedSize(true);

        // Set the onClickListener for the spinner
        measurementTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinnerValue = adapterView.getItemAtPosition(i).toString();
                String message = " measurement was selected";

                // Set the ingredient measurement type based on the selected spinner value
                switch(spinnerValue){
                    case "   ":
                        ingredient.setType(Ingredient.Type.WHOLE);
                        message += "___";
                        type = meas[0];
                        break;
                    case "TSP":
                        ingredient.setType(Ingredient.Type.TSP);
                        message += "TSP";
                        type = meas[1];
                        break;
                    case "TBSP":
                        ingredient.setType(Ingredient.Type.TBSP);
                        message += "TBSP";
                        type = meas[2];
                        break;
                    case "CUP":
                        ingredient.setType(Ingredient.Type.CUP);
                        message += "CUP";
                        type = meas[3];
                        break;
                    case "QT":
                        ingredient.setType(Ingredient.Type.QT);
                        message += "QT";
                        type = meas[4];
                        break;
                    case "OZ":
                        ingredient.setType(Ingredient.Type.OZ);
                        message += "OZ";
                        type = meas[5];
                        break;
                    case "LB":
                        ingredient.setType(Ingredient.Type.LB);
                        message += "LB";
                        type = meas[6];
                        break;
                    case "GAL":
                        ingredient.setType(Ingredient.Type.GAL);
                        message += "GAL";
                        type = meas[7];
                        break;
                    case "PT":
                        ingredient.setType(Ingredient.Type.PT);
                        message += "PT";
                        type = meas[8];
                        break;
                }

                Toast.makeText(adapterView.getContext(), message, Toast.LENGTH_SHORT).show();
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
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClicked();
            }
        });

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

    /**
     * This method is called when the add button is clicked.
     * If the add button is clicked, check to see that all values were filled out, if not, display error message
     * If all values are filled out, form an Ingredient object using the given values, add it to the ArrayList of ingredients,
     * update RecyclerView with new ingredient, prepare for new ingredient by reinitializing the ingredient object,
     * empty all fields for the user, and make any error messages invisible
     */
    private void addButtonClicked(){
        // Retrieve all values entered by the user
        amount = ingredientAmountEditText.getText().toString();
        name = ingredientNameEditText.getText().toString();

        if(amount.equalsIgnoreCase("")){
            // If no amount is entered, display error message
            ingredientErrorTextView.setText("Amount of ingredient is missing");
            ingredientErrorTextView.setVisibility(View.VISIBLE);
        } else if(type.equalsIgnoreCase("")){
            // If no measurement type was chosen, display error message
            ingredientErrorTextView.setText("Measurement type not chosen");
            ingredientErrorTextView.setVisibility(View.VISIBLE);
        } else if(name.equalsIgnoreCase("")){
            // If no ingredient name is entered, display error message
            ingredientErrorTextView.setText("Ingredient name is missing");
            ingredientErrorTextView.setVisibility(View.VISIBLE);
        } else{
            ingredientErrorTextView.setVisibility(View.INVISIBLE);

            // use values to finish creating the ingredient object and add it to the array list
            // @TODO: fix the issue for if the user enters a fraction for the amount
            ingredient.setAmount(Integer.parseInt(amount));
            ingredient.setName(name);
            ingredients.add(ingredient);

            // Update RecyclerView with new information
            recyclerViewAdapter.notifyDataSetChanged();

            // Prepare for a fresh start so the user can possibly enter a new ingredient
            ingredient = new Ingredient();
            type = "";
            ingredientAmountEditText.setText("");
            ingredientNameEditText.setText("");
        }
    }
}
