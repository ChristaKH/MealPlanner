package chatch.j.mealplanner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import chatch.j.mealplanner.Models.Recipe;
import chatch.j.mealplanner.R;

/**
 * This is an adapter class for the RecyclerView that is in the Meals Fragment, Desserts Fragment,
 * Drinks Fragment, and Others Fragment.This will populate the RecyclerView with recipes in CardView
 * form depending on the selected category.
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> mRecipes = new ArrayList<Recipe>();
    private Context mContext;

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type.
     * <p>
     * The RecipeViewHolder will be used to display recipes of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be re-used to display
     * different recipes in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new RecipeViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the single recipe in CardView form
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recipe_layout, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * updates the contents of the RecipeViewHolder to reflect the recipe at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the recipe changes in the data set unless the recipe itself is
     * invalidated or the new position cannot be determined.
     * <p>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the recipe within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    /**
     * Returns the total number of recipes in the data set held by the adapter.
     *
     * @return The total number of recipes in this adapter.
     */
    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    /**
     * This is the custom ViewHolder for the RecipeRecyclerViewAdapter class which
     * is used to populate the RecyclerViews in the Recipe Fragment with recipes based
     * on recipe category
     */
    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        /**
         * Components from single_recipe_layout.xml
         */
        private ImageView cardRecipeImageView;
        private TextView cardRecipeTitleTextView;
        private TextView cardCreatorTextView;
        private TextView cardCookTimeTextView;
        private TextView cardDifficultyTextView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            cardRecipeImageView = itemView.findViewById(R.id.cardRecipeImageView);
            cardRecipeTitleTextView = itemView.findViewById(R.id.cardRecipeTitleTextView);
            cardCreatorTextView = itemView.findViewById(R.id.cardCreatorTextView);
            cardCookTimeTextView = itemView.findViewById(R.id.cardCookTimeTextView);
            cardDifficultyTextView = itemView.findViewById(R.id.cardDifficultyTextView);
        }

        /**
         * This method makes it easier for the the recycler view to be populated based
         * on the contents of the View Holder. This method should be called inside the
         * onBindViewHolder(RecipeViewHolder, int) method
         * @param recipe
         */
        public void bindRecipe(Recipe recipe){
            // @TODO properly add in images
            // cardRecipeImageView.setImageDrawable();
            cardRecipeTitleTextView.setText(recipe.getTitle());
            cardCreatorTextView.setText(recipe.getCreator());
            cardCookTimeTextView.setText(recipe.getCookTime());

            // Change the text view based on the enum representing difficulty
            switch(recipe.getDifficulty()){
                case NONE:
                    cardDifficultyTextView.setText("--");
                    break;
                case EASY:
                    cardDifficultyTextView.setText("EASY");
                    break;
                case MEDIUM:
                    cardDifficultyTextView.setText("MEDIUM");
                    break;
                case HARD:
                    cardDifficultyTextView.setText("HARD");
                    break;
                default:
                    cardDifficultyTextView.setText("--");
                    break;
            }
        }
    }
}
