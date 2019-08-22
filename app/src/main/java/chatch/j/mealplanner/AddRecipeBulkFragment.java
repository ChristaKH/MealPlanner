package chatch.j.mealplanner;


import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

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
 *     - Category: Optional (drop down menu, radio buttons, or seek bar)
 */
public class AddRecipeBulkFragment extends Fragment {

    // Important components from the xml
    private EditText newRecipeTitleEditText;
    private EditText newRecipeCreatorEditText;
    private IndicatorSeekBar cookTimeSeekbar;
    private Button easyDifficultyButton;
    private Button mediumDifficultyButton;
    private Button hardDifficultyButton;

    private String recipeName;
    private String recipeCreator;
    private int cookTime;
    private String difficulty;

    public AddRecipeBulkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe_bulk, container, false);

        // Give instance variables values
        recipeName = "";
        recipeCreator = "";
        cookTime = 0;
        difficulty = "EASY";

        // Connect java objects to their xml counterparts
        newRecipeTitleEditText = view.findViewById(R.id.newRecipeTitleEditText);
        newRecipeCreatorEditText = view.findViewById(R.id.newRecipeCreatorEditText);
        cookTimeSeekbar = view.findViewById(R.id.cookTimeSeekBar);
        easyDifficultyButton = view.findViewById(R.id.easyDifficultyButton);
        mediumDifficultyButton = view.findViewById(R.id.mediumDifficultyButton);
        hardDifficultyButton = view.findViewById(R.id.hardDifficultyButton);

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

        return view;
    }
}
