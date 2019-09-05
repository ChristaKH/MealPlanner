package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The SplashActivity is used to display the splash screen. This activity has a
 * TextView with the app's title which will slowly fade into view. (To be added later)
 * The activity also displays the logo/icon at the start and once the TextView is fully
 * faded into view it'll perform an animation to spin the icon/logo.
 */
public class SplashActivity extends AppCompatActivity {

    private TextView appTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Animation for the app title TextView
        // Starts off invisible and then fades into view
        appTitleTextView = findViewById(R.id.appTitleTextView);
        appTitleTextView.setVisibility(View.INVISIBLE);
        appTitleTextView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_title_fade_in));
        appTitleTextView.setVisibility(View.VISIBLE);

        // Delay the activity with a TimerTask
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Finish current activity
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000); // Delay is in milliseconds
    }
}
