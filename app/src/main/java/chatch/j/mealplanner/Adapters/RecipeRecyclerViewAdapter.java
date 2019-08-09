package chatch.j.mealplanner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import chatch.j.mealplanner.Models.Recipe;
import chatch.j.mealplanner.R;

/**
 * This class is the adapter for the RecyclerView that is used
 * in the recipe fragment. This will populate the section titles
 * and the recycler views of recipe pictures. This class assumes that
 * the Recipes entered are already sorted by category
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeSectionViewHolder> {

    private Context mContext;
    private ArrayList<ArrayList<Recipe>> mRecipeSections;
    private RecipeSectionRecyclerViewAdapter horizontalAdapter;

    public RecipeRecyclerViewAdapter(Context context, ArrayList<ArrayList<Recipe>> recipeSections) {
        mContext = context;
        mRecipeSections = recipeSections;
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
    public RecipeSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // the custom layout for the horizontal recycler view is inflated here
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_section_layout, parent, false);
        RecipeSectionViewHolder viewHolder = new RecipeSectionViewHolder(view);

        // Return the viewHolder with the view as a parameter of the ViewHolder
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
    public void onBindViewHolder(@NonNull RecipeSectionViewHolder holder, int position) {
        // Order for categories to index is (0)Breakfast, (1)Lunch, (2)Dinner, (3)Dessert, (4)Other
        // I don't like that this relies on assumed positions
        // @TODO change how we determine categories so they aren't assumed
        Recipe.Category category;

        switch(position){
            case 0:
                category = Recipe.Category.BREAKFAST;
                break;
            case 1:
                category = Recipe.Category.LUNCH;
                break;
            case 2:
                category = Recipe.Category.DINNER;
                break;
            case 3:
                category = Recipe.Category.DESSERT;
                break;
            case 4:
                category = Recipe.Category.OTHER;
                break;
            default:
                category = Recipe.Category.OTHER;
                break;
        }

        holder.bindSections(category, mRecipeSections.get(position));

        // @DONE bind the inner recycler view
        // Bind the inner recycler view to an adapter
        horizontalAdapter = new RecipeSectionRecyclerViewAdapter(mContext, mRecipeSections.get(position));
        holder.recipeImageRecyclerView.setHasFixedSize(true);
        holder.recipeImageRecyclerView.setAdapter(horizontalAdapter);
        holder.recipeImageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

    }

    /**
     * Returns the total number of sections in the data set held by the adapter.
     *
     * @return The total number of sections in this adapter.
     */
    @Override
    public int getItemCount() {
        return mRecipeSections.size();
    }

    /**
     * This is the custom ViewHolder class used for the RecyclerView in the recipe
     * fragment. This ViewHolder will connect to the TextView and inner RecyclerView
     * in the recipe RecyclerView and allow us an easier route to populating them
     * with the proper recipe info
     */
    public class RecipeSectionViewHolder extends RecyclerView.ViewHolder{
        private TextView sectionTitleTextView;
        protected RecyclerView recipeImageRecyclerView;

        public RecipeSectionViewHolder(@NonNull View itemView) {
            super(itemView);

            // Connect components to their counterparts in the xml
            mContext = itemView.getContext();
            sectionTitleTextView = itemView.findViewById(R.id.sectionTitleTextView);
            recipeImageRecyclerView = itemView.findViewById(R.id.recipeImageRecyclerView);
        }

        /**
         * This method populates the text view and the inner recycler view
         * based on the section that is entered
         */
        public void bindSections(Recipe.Category category, ArrayList<Recipe> recipeSection){

             // @TODO Revisit later to address when a section has no recipes
            // Set the title based on the section category
            switch(category){
                case BREAKFAST:
                    System.out.println("Breakfast");
                    sectionTitleTextView.setText(R.string.breakfastTitle);
                    break;
                case LUNCH:
                    System.out.println("Lunch");
                    sectionTitleTextView.setText(R.string.lunchTitle);
                    break;
                case DINNER:
                    System.out.println("Dinner");
                    sectionTitleTextView.setText(R.string.dinnerTitle);
                    break;
                case DESSERT:
                    System.out.println("Dessert");
                    sectionTitleTextView.setText(R.string.dessertTitle);
                    break;
                case OTHER:
                    System.out.println("Other");
                    sectionTitleTextView.setText(R.string.otherTitle);
                    break;
            }
        }
    }
}
