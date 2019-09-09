package chatch.j.mealplanner;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.warkiz.widget.IndicatorSeekBar;

import chatch.j.mealplanner.Models.Recipe;

/**
 * A simple {@link Fragment} subclass.
 * This is a Fragment that takes in the bulk of the information for
 * a new Recipe from the user.
 * The information taken using this fragment includes:
 *     - Recipe Title: Required (TextView)
 *     - Creator: Optional (TextView)
 *     - Cook Time: Optional (SeekBar)
 *     - Difficulty: Optional (Three buttons)
 *     - Category: Optional (clickable TextViews)
 */
public class AddRecipeBulkFragment extends Fragment {

    // Important components from the xml
    private TextView nameWarningTextView;
    private EditText newRecipeTitleEditText;
    private EditText newRecipeCreatorEditText;
    private IndicatorSeekBar cookTimeSeekbar;
    private Button easyDifficultyButton;
    private Button mediumDifficultyButton;
    private Button hardDifficultyButton;

    // TextViews that represent the possible recipe categories
    private TextView mealsTextView;
    private TextView dessertsTextView;
    private TextView drinksTextView;
    private TextView othersTextView;

    private Button toIngredientsNextButton;

    // These will hold the values to be put in a Recipe object
    private String recipeName;
    private String recipeCreator;
    private int cookTime;
    private Recipe.Difficulty difficulty;
    private Recipe.Category category;

    // Define the listener of the interface type
    private AddRecipeBulkInteractionListener listener;

    public AddRecipeBulkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_recipe_bulk, container, false);

        // Give instance variables default values
        recipeName = "";
        recipeCreator = "";
        cookTime = 0;
        difficulty = Recipe.Difficulty.EASY;
        category = Recipe.Category.OTHER;

        // Connect java objects to their xml counterparts
        nameWarningTextView = view.findViewById(R.id.nameWarningTextView);
        newRecipeTitleEditText = view.findViewById(R.id.newRecipeTitleEditText);
        newRecipeCreatorEditText = view.findViewById(R.id.newRecipeCreatorEditText);
        cookTimeSeekbar = view.findViewById(R.id.cookTimeSeekBar);
        easyDifficultyButton = view.findViewById(R.id.easyDifficultyButton);
        mediumDifficultyButton = view.findViewById(R.id.mediumDifficultyButton);
        hardDifficultyButton = view.findViewById(R.id.hardDifficultyButton);
        toIngredientsNextButton = view.findViewById(R.id.toIngredientsNextButton);

        // Connect TextViews to their xml counterparts
        mealsTextView = view.findViewById(R.id.mealsTextView);
        dessertsTextView = view.findViewById(R.id.dessertsTextView);
        drinksTextView = view.findViewById(R.id.drinksTextView);
        othersTextView = view.findViewById(R.id.othersTextView);

        // Start off with the mealsTextView being "selected"
        mealsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
        mealsTextView.setText(R.string.mealsTitle);
        category = Recipe.Category.MEAL;
        dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));
        dessertsTextView.setText("");
        drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));
        drinksTextView.setText("");
        othersTextView.setBackground(getResources().getDrawable(R.drawable.other));
        othersTextView.setText("");

        // onClickListener for the meal TextView
        mealsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRecipeCategory(Recipe.Category.MEAL);
            }
        });

        // onClickListener for the desserts TextView
        dessertsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRecipeCategory(Recipe.Category.DESSERT);
            }
        });

        // onClickListener for the drinks TextView
        drinksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRecipeCategory(Recipe.Category.DRINK);
            }
        });

        // onClickListener for the others TextView
        othersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRecipeCategory(Recipe.Category.OTHER);
            }
        });


        // Set onClickListeners for the three difficulty buttons
        // Only one button at a time can have red background and white text
        easyDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Easy button is clicked
                // Easy button should have red background and white words
                easyDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.sizzlingRed));
                easyDifficultyButton.setTextColor(getResources().getColor(R.color.white));

                // Medium and Hard button should have white backgrounds and black words
                mediumDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                mediumDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                hardDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                hardDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                // difficulty should be EASY
                difficulty = Recipe.Difficulty.EASY;
            }
        });

        mediumDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Medium button is clicked
                // Medium button should have red background and white words
                mediumDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.sizzlingRed));
                mediumDifficultyButton.setTextColor(getResources().getColor(R.color.white));

                // Easy and Hard button should have white backgrounds and black words
                easyDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                easyDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                hardDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                hardDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                // difficulty should be set to MEDIUM
                difficulty = Recipe.Difficulty.MEDIUM;
            }
        });

        hardDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Hard button is clicked
                // Hard button should have red background and white words
                hardDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.sizzlingRed));
                hardDifficultyButton.setTextColor(getResources().getColor(R.color.white));

                // Easy and Medium button should have white backgrounds and black words
                easyDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                easyDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                mediumDifficultyButton.setBackgroundTintList(view.getResources().getColorStateList(R.color.white));
                mediumDifficultyButton.setTextColor(getResources().getColor(R.color.black));

                // difficulty should be set to HARD
                difficulty = Recipe.Difficulty.HARD;
            }
        });

        // Set the onClickListener for the Next button
        // When clicked, it should transition to the fragment that allows for the addition
        // Of ingredients for the recipe.
        // It should also check to see if the recipe name TextView is filled out.
        // If not, the warning TextView should show up to alert the user and the transition to the
        // Next fragment will not take place
        // All filled out information should be passed to the next fragment
        toIngredientsNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the EditText for the recipe name is empty show the user the warning
                if(newRecipeTitleEditText.getText().length() == 0){
                    nameWarningTextView.setVisibility(View.VISIBLE);
                } else{ // EditText for the recipe name has content inside
                    nameWarningTextView.setVisibility(View.INVISIBLE);

                    // Retrieve the recipe name
                    recipeName = newRecipeTitleEditText.getText().toString();

                    // If the EditText for the creator name isn't empty, retrieve the creator name
                    if(newRecipeCreatorEditText.getText().length() != 0){
                        recipeCreator = newRecipeCreatorEditText.getText().toString();
                    }

                    // SeekBar will always yield a result so retrieve SeekBar value for cook time
                    cookTime = cookTimeSeekbar.getProgress();

                    // Difficulty is already updated in their button onClickListeners so no need to retrieve value
                    // Category is already updated in the selectRecipeCategory() method so no need to retrieve value

                    // Change the values of the newRecipe object in the NewRecipe activity
                    NewRecipeActivity.newRecipe.setTitle(recipeName);
                    NewRecipeActivity.newRecipe.setCreator(recipeCreator);
                    NewRecipeActivity.newRecipe.setCookTime(cookTime);
                    NewRecipeActivity.newRecipe.setDifficulty(difficulty);
                    NewRecipeActivity.newRecipe.setCategory(category);

                    // Switch to the AddIngredientsFragment
                    onNextClick(view);
                }
            }
        });
        return view;
    }

    /**
     * This method is used for the selecting of the recipe category and will change the
     * background color of the selected TextView to sizzling red as well as add the words for
     * the selected category. It will also remove the text of the last selected TextView (if it
     * is different than the current one) and change the background to the image chosen to represent
     * the category as well as change the value of the category variable to the appropriate value.
     * If the current selected TextView is different from the previously selected TextView
     * then both will be faded in using an animation.
     * @param currentCategory category representing the current selected TextView
     */
    private void selectRecipeCategory(Recipe.Category currentCategory){
        Recipe.Category previousCategory;

        // Find which TextView was the last one selected
        if(mealsTextView.getBackground() instanceof ColorDrawable){
            previousCategory = Recipe.Category.MEAL;
        } else if(drinksTextView.getBackground() instanceof ColorDrawable){
            previousCategory = Recipe.Category.DRINK;
        } else if(dessertsTextView.getBackground() instanceof  ColorDrawable){
            previousCategory = Recipe.Category.DESSERT;
        } else{
            previousCategory = Recipe.Category.OTHER;
        }

        // If the current and previous category are different
        if(previousCategory != currentCategory) {
            // Set the background to sizzling red for the current category's TextView
            // Set the text for the current category's TextView
            // Fade in current TextView
            switch(currentCategory){
                case MEAL:
                    mealsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
                    mealsTextView.setText(R.string.mealsTitle);
                    mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    break;
                case DRINK:
                    drinksTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
                    drinksTextView.setText(R.string.drinksTitle);
                    drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    break;
                case DESSERT:
                    dessertsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
                    dessertsTextView.setText(R.string.dessertsTitle);
                    dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    break;
                case OTHER:
                    othersTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
                    othersTextView.setText(R.string.othersTitle);
                    othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    break;
            }

            // Change the background for the previous category's TextView
            // Remove the text for the previous category's TextView
            // Fade in previous category's TextView
            // Set the value of the category
            switch (previousCategory){
                case MEAL:
                    mealsTextView.setBackground(getResources().getDrawable(R.drawable.meal));
                    mealsTextView.setText("");
                    mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    category = Recipe.Category.MEAL;
                    break;
                case DRINK:
                    drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));
                    drinksTextView.setText("");
                    drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    category = Recipe.Category.DRINK;
                    break;
                case DESSERT:
                    dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));
                    dessertsTextView.setText("");
                    dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    category = Recipe.Category.DESSERT;
                    break;
                case OTHER:
                    othersTextView.setBackground(getResources().getDrawable(R.drawable.other));
                    othersTextView.setText("");
                    othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    category = Recipe.Category.OTHER;
                    break;
            }
        }
    }

    // Define the events that the fragment will use to communicate
    public interface AddRecipeBulkInteractionListener{
        // This is the method for when the next button is clicked
        public void onBulkNextClicked();
    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddRecipeBulkInteractionListener) {
            listener = (AddRecipeBulkInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement AddRecipeBulkFragment.AddRecipeBulkInteractionListener");
        }
    }

    // Now we can fire the next button event when the user selects the next button in the fragment
    public void onNextClick(View v) {
        listener.onBulkNextClicked();
    }
}
