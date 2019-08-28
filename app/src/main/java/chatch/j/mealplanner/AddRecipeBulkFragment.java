package chatch.j.mealplanner;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    private RelativeLayout mealRelativeLayout;
    private ImageView mealImageView;
    private TextView mealTextView;
    private RelativeLayout dessertRelativeLayout;
    private ImageView dessertImageView;
    private RelativeLayout drinkRelativeLayout;
    private ImageView drinkImageView;
    private RelativeLayout otherRelativeLayout;
    private ImageView otherImageView;

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
        final View view = inflater.inflate(R.layout.fragment_add_recipe_bulk, container, false);

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
        mealRelativeLayout = view.findViewById(R.id.mealRelativeLayout);
        mealImageView = view.findViewById(R.id.mealImageView);
        mealTextView = view.findViewById(R.id.mealTextView);
        dessertRelativeLayout = view.findViewById(R.id.dessertRelativeLayout);
        dessertImageView = view.findViewById(R.id.dessertImageView);
        drinkRelativeLayout = view.findViewById(R.id.drinkRelativeLayout);
        drinkImageView = view.findViewById(R.id.drinkImageView);
        otherRelativeLayout = view.findViewById(R.id.otherRelativeLayout);
        otherImageView = view.findViewById(R.id.otherImageView);

        // Use RoundedBitmapDrawable to give the ImageViews curved corners
        // Curved mealImageView corners
        Resources res = getResources();

        Bitmap src =  BitmapFactory.decodeResource(res, R.drawable.meal);
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 10.0f);
        mealImageView.setImageDrawable(dr);

        // Curved drinkImageView corners
        src = BitmapFactory.decodeResource(res, R.drawable.drinks_example);
        dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 10.0f);
        drinkImageView.setImageDrawable(dr);

        // Curved dessertImageView corners
        // Curved drinkImageView corners
        src = BitmapFactory.decodeResource(res, R.drawable.dessert_example);
        dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 10.0f);
        dessertImageView.setImageDrawable(dr);

        // Curved otherImageView corners
        src = BitmapFactory.decodeResource(res, R.drawable.other_pic);
        dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 10.0f);
        otherImageView.setImageDrawable(dr);

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

        // Set onClickListeners for the recipe category ImageViews
        mealRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is the onClick method for when the mealImageView is clicked.
                // When clicked, this ImageView should become semi-opaque to show it was clicked
                // Also, the other 3 ImageViews should display the corresponding
                // food images at full opacity
                mealImageView.setAlpha(0.5f);
                drinkImageView.setAlpha(1.0f);
                dessertImageView.setAlpha(1.0f);
                otherImageView.setAlpha(1.0f);
            }
        });


        dessertRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is the onClick method for when the dessertImageView is clicked.
                // When clicked, this ImageView should become semi-opaque to show it was clicked
                // Also, the other 3 ImageViews should display the corresponding
                // food images at full opacity
                dessertImageView.setAlpha(0.5f);
                drinkImageView.setAlpha(1.0f);
                mealImageView.setAlpha(1.0f);
                otherImageView.setAlpha(1.0f);
            }
        });

        drinkRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is the onClick method for when the drinkImageView is clicked.
                // When clicked, this ImageView should become semi-opaque to show it was clicked
                // Also, the other 3 ImageViews should display the corresponding
                // food images at full opacity
                drinkImageView.setAlpha(0.5f);
                dessertImageView.setAlpha(1.0f);
                mealImageView.setAlpha(1.0f);
                otherImageView.setAlpha(1.0f);
            }
        });

        otherRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is the onClick method for when the mealImageView is clicked.
                // When clicked, this ImageView should become semi-opaque to show it was clicked
                // Also, the other 3 ImageViews should display the corresponding
                // food images at full opacity
                otherImageView.setAlpha(0.5f);
                drinkImageView.setAlpha(1.0f);
                mealImageView.setAlpha(1.0f);
                dessertImageView.setAlpha(1.0f);
            }
        });

        return view;
    }
}
