package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView mainBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the components of the xml to the main activity
        toolbar = findViewById(R.id.toolbar);
        mainBottomNavigationView = findViewById(R.id.mainBottomNavigationView);
        setSupportActionBar(toolbar);
    }
}
