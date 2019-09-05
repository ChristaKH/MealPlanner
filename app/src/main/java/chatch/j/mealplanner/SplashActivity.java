package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private TextView appTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
