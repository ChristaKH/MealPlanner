package chatch.j.mealplanner.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import chatch.j.mealplanner.Models.Recipe;
import chatch.j.mealplanner.R;

/**
 * This is a custom RecyclerView adapter that exists to populate the
 * RecyclerView in the recipe_section_layout.xml which holds images
 * of recipes represented in recipe_section_images_layout.xml
 */
public class RecipeSectionRecyclerViewAdapter extends
                RecyclerView.Adapter<RecipeSectionRecyclerViewAdapter.RecipeImageViewHolder>{

    private ArrayList<Recipe> mRecipes = new ArrayList<Recipe>();
    private Context mContext;

    public RecipeSectionRecyclerViewAdapter(Context context, ArrayList<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be re-used to display
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
    public RecipeImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the individual item in the recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_section_images_layout, parent, false);
        RecipeImageViewHolder viewHolder = new RecipeImageViewHolder(view);
        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override onBindViewHolder(ViewHolder, int, List) instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecipeImageViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        holder.bindRecipe(recipe);
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
     * This is the custom ViewHolder class for the RecipeSecionRecyclerViewAdapter class
     * (boy that's a mouthfull)
     * This class will help to populate the recycler view with images associated with
     * a given recipe
     */
    public class RecipeImageViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView recipeImageView;

        public RecipeImageViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            recipeImageView = itemView.findViewById(R.id.recipeImageView);
        }

        /**
         * Method that gets an image that corresponds to a given recipe and
         * uses it for the image view
         * @param recipe    Given recipe whose image we will show
         */
        public void bindRecipe(Recipe recipe){
            String recipeImageName = recipe.getImageName();
            // If there is no image file use a color instead (for now)
            if(recipeImageName.length() == 0){
                ColorDrawable desire = new ColorDrawable(mContext.getResources().getColor(R.color.desire));
                recipeImageView.setImageDrawable(desire);
            } else{
                // @TODO change so that we can get the images from a differen storage than assets
                // Otherwise, set the image view to the proper recipe image
                // Getting images from assets for now
                AssetManager am = mContext.getAssets();

                try {
                    InputStream stream = am.open(recipe.getImageName());
                    Drawable image = Drawable.createFromStream(stream, recipe.getTitle());
                    recipeImageView.setImageDrawable(image);

                } catch (IOException e) {
                    Log.e("Meal Planner", e.getMessage());
                }
            }
        }

    }

}
