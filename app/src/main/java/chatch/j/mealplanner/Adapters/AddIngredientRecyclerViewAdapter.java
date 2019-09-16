package chatch.j.mealplanner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import chatch.j.mealplanner.Models.Ingredient;
import chatch.j.mealplanner.R;

/**
 * This is an adapter class for the RecyclerView that is used in the AddIngredientFragment.
 * This adapter utilizes the single_ingredient_layout.xml to display the ingredients that are
 * added by the user as well as allow the user to remove an ingredient that has already been added.
 */
public class AddIngredientRecyclerViewAdapter extends RecyclerView.Adapter<AddIngredientRecyclerViewAdapter.AddIngredientViewHolder> {
    private ArrayList<Ingredient> mIngredients;
    private Context mContext;

    public AddIngredientRecyclerViewAdapter(List<Ingredient> ingredients){
        mIngredients = (ArrayList<Ingredient>) ingredients;
    }

    /**
     * Called when RecyclerView needs a new {@link AddIngredientViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(AddIngredientViewHolder, int, List). Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public AddIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the single recipe in CardView form
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ingredient_layout, parent, false);
        AddIngredientRecyclerViewAdapter.AddIngredientViewHolder viewHolder =
                        new AddIngredientRecyclerViewAdapter.AddIngredientViewHolder(view);

        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link AddIngredientViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link AddIngredientViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override onBindViewHolder(AddIngredientViewHolder, int, List) instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull AddIngredientViewHolder holder, int position) {
        holder.bindIngredient(mIngredients.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    /**
     * This is the inner class that extends the ViewHolder of the RecyclerView. This
     * View Holder fills out the components of the single_ingredient_layout.xml with
     * the values that are given in the parent class.
     */
    public class AddIngredientViewHolder extends RecyclerView.ViewHolder {

        // Important components of single_ingredient_layout.xml
        private TextView ingredientAmountEditText;
        private TextView measurementTypeTextView;
        private TextView ingredientNameTextView;
        private Button removeButton;

        public AddIngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            // Connect to xml components
            ingredientAmountEditText = itemView.findViewById(R.id.ingredientAmountEditText);
            measurementTypeTextView = itemView.findViewById(R.id.measurementTypeTextView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }

        /**
         * Method that takes in the given information and binds it to the single ingredient layout.
         * @param ingredient    Object that represents a single ingredient in a recipe
         */
        public void bindIngredient(Ingredient ingredient){
            // Note: amount should be checked for a negative number when the add ingredient
            // button is clicked

            // Set the ingredient amount
            ingredientAmountEditText.setText(String.valueOf(ingredient.getAmount()));

            // Set the measurement type that is displayed
            switch(ingredient.getType()){
                case TSP:
                    measurementTypeTextView.setText("TSP");
                    break;
                case TBSP:
                    measurementTypeTextView.setText("TBSP");
                    break;
                case GAL:
                    measurementTypeTextView.setText("GAL");
                    break;
                case CUP:
                    measurementTypeTextView.setText("CUP");
                    break;
                case QT:
                    measurementTypeTextView.setText("QT");
                    break;
                case PT:
                    measurementTypeTextView.setText("PT");
                    break;
                case LB:
                    measurementTypeTextView.setText("LB");
                    break;
                default:
                    measurementTypeTextView.setText("   ");
                    break;
            }

            // Set the ingredient name in all uppercase letters
            ingredientNameTextView.setText(ingredient.getName());

            // Give the remove button a listener that removes this instance of the
            // single ingredient layout
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Remove ingredient from our ArrayList
                    mIngredients.remove(getAdapterPosition());

                    // Update the items in the RecyclerView
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), mIngredients.size());
                }
            });
        }
    }
}
