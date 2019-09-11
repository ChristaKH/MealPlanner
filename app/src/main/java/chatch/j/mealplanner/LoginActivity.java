package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

/**
 * This is the Login Activity which is used to allow the user to sign into their
 * existing accounting or to sign up with a new account. The user can only have one
 * account per email. This activity utilizes Firebase for authentification and will
 * give the user access to the Recipes that are save to the database under their account.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth; // Firebase object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instantiate our Firebase object
        mAuth = FirebaseAuth.getInstance();
    }
}
