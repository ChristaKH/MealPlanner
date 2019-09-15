package chatch.j.mealplanner.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import chatch.j.mealplanner.R;

/**
 * This is an adapter class for the RecyclerView that is used in the AddIngredientFragment.
 * This adapter utilizes the single_ingredient_layout.xml to display the ingredients that are
 * added by the user as well as allow the user to remove an ingredient that has already been added.
 */
public class AddIngredientRecyclerViewAdapter {

    /**
     * This is the inner class that extends the ViewHolder of the RecyclerView. This
     * View Holder fills out the components of the single_ingredient_layout.xml with
     * the values that are given in the parent class.
     */
    public class AddIngredientViewHolder extends RecyclerView.ViewHolder {

        // Important components of single_ingredient_layout.xml
        private TextView ingredientAmountTextView;
        private TextView measurementTypeTextView;
        private TextView ingredientNameTextView;
        private Button removeButton;

        public AddIngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            // Connect to xml components
            ingredientAmountTextView = itemView.findViewById(R.id.ingredientAmountTextView);
            measurementTypeTextView = itemView.findViewById(R.id.measurementTypeTextView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }

        /**
         * Method that takes in the given information and binds it to the single ingredient layout.
         * The amount is assumed to be a number above 0.
         * @param amount    Amount of an ingredient. Pre-checked value must be over 0.
         * @param type  Measurement type of an ingredient. May be empty.
         * @param ingredient    Name of ingredient
         */
        public void bindIngredient(int amount, String type, String ingredient){
            // Note: amount should be checked for a negative number when the add ingredient
            // button is clicked

            // Set the ingredient amount
            ingredientAmountTextView.setText(String.valueOf(amount));

            // Set the measurement type that is displayed
            if(type.equalsIgnoreCase("--") || type.equalsIgnoreCase("  ")){
                measurementTypeTextView.setText("   ");
            } else{
                measurementTypeTextView.setText(type.toUpperCase());
            }

            // Set the ingredient name in all uppercase letters
            ingredientNameTextView.setText(ingredient.toUpperCase());

            // Give the remove button a listener that removes this instance of the
            // single ingredient layout
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
