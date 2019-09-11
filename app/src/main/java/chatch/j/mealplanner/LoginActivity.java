package chatch.j.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This is the Login Activity which is used to allow the user to sign into their
 * existing accounting or to sign up with a new account. The user can only have one
 * account per email. This activity utilizes Firebase for authentification and will
 * give the user access to the Recipes that are save to the database under their account.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // Important components of the login xml
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button signUpButton;

    // Firebase objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instantiate our Firebase object
        mAuth = FirebaseAuth.getInstance();

        // Create listener for checking if the user is already signed in
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // User is logged in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else{
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out:");
                }
            }
        };

        // Listener for when the sign in button is clicked
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Connect to xml components
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

    }

    /**
     * Upon start of activity, attach listener to check and see
     * if the user is already signed in
     */
    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }
}
