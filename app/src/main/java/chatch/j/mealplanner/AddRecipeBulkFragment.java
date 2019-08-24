package chatch.j.mealplanner;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;

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
    private ImageView mealImageView;
    private ImageView dessertImageView;
    private ImageView drinkImageView;
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
        mealImageView = view.findViewById(R.id.mealImageView);
        dessertImageView = view.findViewById(R.id.dessertImageView);
        drinkImageView = view.findViewById(R.id.drinkImageView);
        otherImageView = view.findViewById(R.id.otherImageView);

        // Curve the corners of the category images
        mealImageView.setImageDrawable(getRoundCorners(R.drawable.meal_example, 8));
        dessertImageView.setImageDrawable(getRoundCorners(R.drawable.meal_example, 8));
        drinkImageView.setImageDrawable(getRoundCorners(R.drawable.meal_example, 8));
        otherImageView.setImageDrawable(getRoundCorners(R.drawable.meal_example, 8));

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

    /**
     * This method takes in the id of a drawable and the desired radius of the
     * curved corners and returns the changed image. The changed image is different
     * from the first because the corners are curves.
     * @param drawableId    Id of the image we wish to change
     * @param cornerRadius  Desired radius of the curved corners. Must be greater than 0
     * @return  Adjusted image as a drawable
     */
    private Drawable getRoundCorners(int drawableId, int cornerRadius){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(
                                                            getResources(), bitmap);

        if(cornerRadius > 0) {
            // Set the RoundedBitmapDrawable corners radius
            roundedBitmapDrawable.setCornerRadius(cornerRadius);
        }

                /*
                    setAntiAlias(boolean aa)
                        Enables or disables anti-aliasing for this drawable.
                */
        roundedBitmapDrawable.setAntiAlias(false);

        // Set the ImageView image as drawable object
        return roundedBitmapDrawable;
    }
}
