package chatch.j.mealplanner;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.warkiz.widget.IndicatorSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * This is a Fragment that takes in the bulk of the information for
 * a new Recipe from the user.
 * The information taken using this fragment includes:
 *     - Recipe Title: Required (TextView)
 *     - Creator: Optional (TextView)
 *     - Cook Time: Optional (SeekBar)
 *     - Difficulty: Optional (Three buttons)
 *     - Image: Optional (normal button that opens up the gallery to take a picture)
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

    private Button chooseImageButton;

    private String recipeName;
    private String recipeCreator;
    private int cookTime;
    private String difficulty;
    private String category;

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
        difficulty = "EASY";
        category = "OTHER";

        // Connect java objects to their xml counterparts
        nameWarningTextView = view.findViewById(R.id.nameWarningTextView);
        newRecipeTitleEditText = view.findViewById(R.id.newRecipeTitleEditText);
        newRecipeCreatorEditText = view.findViewById(R.id.newRecipeCreatorEditText);
        cookTimeSeekbar = view.findViewById(R.id.cookTimeSeekBar);
        easyDifficultyButton = view.findViewById(R.id.easyDifficultyButton);
        mediumDifficultyButton = view.findViewById(R.id.mediumDifficultyButton);
        hardDifficultyButton = view.findViewById(R.id.hardDifficultyButton);

        // Connect TextViews to their xml counterparts
        mealsTextView = view.findViewById(R.id.mealsTextView);
        dessertsTextView = view.findViewById(R.id.dessertsTextView);
        drinksTextView = view.findViewById(R.id.drinksTextView);
        othersTextView = view.findViewById(R.id.othersTextView);

        chooseImageButton = view.findViewById(R.id.chooseImageButton);

        // Start off with the mealsTextView being "selected"
        mealsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));
        mealsTextView.setText(R.string.mealsTitle);
        dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));
        dessertsTextView.setText("");
        drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));
        drinksTextView.setText("");
        othersTextView.setBackground(getResources().getDrawable(R.drawable.other));
        othersTextView.setText("");

        // These animations will be used to fade the current background out and fade the new background in
        final Animation fadeIn = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_out);

        // onClickListener for the meal TextView
        mealsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable backgroundColorDrawable = null;

                // Get the current background color
                if (mealsTextView.getBackground() instanceof ColorDrawable) {
                    backgroundColorDrawable = (ColorDrawable) mealsTextView.getBackground();
                }

                // If background color is  not "sizzling red"
                //(If mealTextView wasn't the last category selected)
                if (backgroundColorDrawable == null) {

                    // Find the previously selected TextView
                    // Which will have the background color of sizzling red and will be
                    // an instance of ColorDrawable
                    if(drinksTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));

                        // Get rid of the category title
                        drinksTextView.setText("");

                        // Fade background in
                        drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else if(dessertsTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));

                        // Get rid of the category title
                        dessertsTextView.setText("");

                        // Fade background in
                        dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else{ // By process of elimination othersTextView was selected
                        // Change it to the unselected background
                        othersTextView.setBackground(getResources().getDrawable(R.drawable.other));

                        // Get rid of the category title
                        othersTextView.setText("");

                        // Fade background in
                        othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    }

                    // Change mealsTextView to the background of sizzling red
                    mealsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));

                    // Add "MEALS" text
                    mealsTextView.setText(R.string.mealsTitle);

                    // Fade in new background
                    mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                }
            }
        });

        // onClickListener for the desserts TextView
        dessertsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable backgroundColorDrawable = null;

                // Get the current background color
                if (dessertsTextView.getBackground() instanceof ColorDrawable) {
                    backgroundColorDrawable = (ColorDrawable) dessertsTextView.getBackground();
                }

                // If background color is  not "sizzling red"
                //(If dessertsTextView wasn't the last category selected)
                if (backgroundColorDrawable == null) {

                    // Find the previously selected TextView
                    // Which will have the background color of sizzling red and will be
                    // an instance of ColorDrawable
                    if(mealsTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        mealsTextView.setBackground(getResources().getDrawable(R.drawable.meal));

                        // Get rid of the category title
                        mealsTextView.setText("");

                        // Fade background in
                        mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else if(drinksTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));

                        // Get rid of the category title
                        drinksTextView.setText("");

                        // Fade background in
                        drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else{ // By process of elimination othersTextView was selected
                        // Change it to the unselected background
                        othersTextView.setBackground(getResources().getDrawable(R.drawable.other));

                        // Get rid of the category title
                        othersTextView.setText("");

                        // Fade background in
                        othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    }

                    // Change dessertsTextView to the background of sizzling red
                    dessertsTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));

                    // Add "DESSERTS" text
                    dessertsTextView.setText(R.string.dessertsTitle);

                    // Fade in new background
                    dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                }
            }
        });

        // onClickListener for the drinks TextView
        drinksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable backgroundColorDrawable = null;

                // Get the current background color
                if (drinksTextView.getBackground() instanceof ColorDrawable) {
                    backgroundColorDrawable = (ColorDrawable) drinksTextView.getBackground();
                }

                // If background color is  not "sizzling red"
                //(If drinksTextView wasn't the last category selected)
                if (backgroundColorDrawable == null) {

                    // Find the previously selected TextView
                    // Which will have the background color of sizzling red and will be
                    // an instance of ColorDrawable
                    if(mealsTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        mealsTextView.setBackground(getResources().getDrawable(R.drawable.meal));

                        // Get rid of the category title
                        mealsTextView.setText("");

                        // Fade background in
                        mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else if(dessertsTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));

                        // Get rid of the category title
                        dessertsTextView.setText("");

                        // Fade background in
                        dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else{ // By process of elimination othersTextView was selected
                        // Change it to the unselected background
                        othersTextView.setBackground(getResources().getDrawable(R.drawable.other));

                        // Get rid of the category title
                        othersTextView.setText("");

                        // Fade background in
                        othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    }

                    // Change drinksTextView to the background of sizzling red
                    drinksTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));

                    // Add "DRINKS" text
                    drinksTextView.setText(R.string.drinksTitle);

                    // Fade in new background
                    drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                }
            }
        });

        // onClickListener for the others TextView
        othersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable backgroundColorDrawable = null;

                // Get the current background color
                if (othersTextView.getBackground() instanceof ColorDrawable) {
                    backgroundColorDrawable = (ColorDrawable) othersTextView.getBackground();
                }

                // If background color is  not "sizzling red"
                //(If othersTextView wasn't the last category selected)
                if (backgroundColorDrawable == null) {

                    // Find the previously selected TextView
                    // Which will have the background color of sizzling red and will be
                    // an instance of ColorDrawable
                    if(mealsTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        mealsTextView.setBackground(getResources().getDrawable(R.drawable.meal));

                        // Get rid of the category title
                        mealsTextView.setText("");

                        // Fade background in
                        mealsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else if(drinksTextView.getBackground() instanceof ColorDrawable){
                        // Change it to the unselected background
                        drinksTextView.setBackground(getResources().getDrawable(R.drawable.drinks_example));

                        // Get rid of the category title
                        drinksTextView.setText("");

                        // Fade background in
                        drinksTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    } else{ // By process of elimination dessertsTextView was selected
                        // Change it to the unselected background
                        dessertsTextView.setBackground(getResources().getDrawable(R.drawable.dessert_example));

                        // Get rid of the category title
                        dessertsTextView.setText("");

                        // Fade background in
                        dessertsTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                    }

                    // Change othersTextView to the background of sizzling red
                    othersTextView.setBackground(new ColorDrawable(getResources().getColor(R.color.sizzlingRed)));

                    // Add "OTHERS" text
                    othersTextView.setText(R.string.othersTitle);

                    // Fade in new background
                    othersTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                }
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

                // difficulty should be "EASY"
                difficulty = "EASY";
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

                // difficulty should be set to "MEDIUM"
                difficulty = "MEDIUM";
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

                // difficulty should be set to "HARD"
                difficulty = "HARD";
            }
        });

        // Time to ask the user for a picture to use from their gallery for their recipe
        // Set the onClickListener for the image selecting button
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
