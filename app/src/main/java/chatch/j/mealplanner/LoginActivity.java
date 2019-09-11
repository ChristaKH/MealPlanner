package chatch.j.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private Context mContext;

    // Important components of the login xml
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button signUpButton;
    private TextView loginErrorTextView;

    // Firebase objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String emailError = "Must enter a valid email";
    private String emptyPasswordError = "Must enter a valid password";
    private String shortPasswordError = "Password must be at least 6 character long";
    private String incorrectLoginError = "Incorrect login information was entered";
    private String usedEmailError = "Email is already in use";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        // Connect to xml components
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        loginErrorTextView = findViewById(R.id.loginErrorTextView);

        loginErrorTextView.setVisibility(View.INVISIBLE);

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
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Attempt to sign in using the sign in method
                // Error checking is done here too
                signIn(email, password);
            }
        });

        // Listener for when the sign up button is clicked
        signUpButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Attempt to sign up using the sign up method
                // Error checking done here too
                signUp(email, password);
            }
        }));
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

    /**
     * Customizable toast message
     * @param message   Message that will be shown through a toast
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method exists to error check the entered email and password as well as
     * sign in the user. If the email or password EditText is empty, the error message
     * will be displayed. If both are filled out, the values will attempt to be authenticated.
     * If authentification is unsuccessful the user will be notified with the TextView error
     * message. If the authentification is sucessful, the user will be notified via toast and
     * will then move on to the MainActivity.
     * @param email
     * @param password
     */
    private void signIn(String email, String password){
        // If there was no email entered, display email error message
        if(email.length() == 0){
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(!email.contains("@")) {
            // If the entered email doesn't contain an '@' character
            // Than it is not a proper email so show an error message
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(email.lastIndexOf('.') < email.indexOf('@')){
            // If the '.' character used for the .com, .edu, etc. comes before the
            // '@' character then the email is in the wrong format
            // Alert the user with error message
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(password.length() == 0){
            // If there was no password entered, display password error message
            loginErrorTextView.setText(emptyPasswordError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(password.length() < 6){
            // If the password was too short, display other password error message
            loginErrorTextView.setText(shortPasswordError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If successfully signed in, show a toast to  indicate success
                            // Log successful sign in
                            // Move to the MainActivity
                            if(task.isSuccessful()){
                                Log.d(TAG, "signInWithEmailAndPassword:Success");
                                toastMessage("Logged in with " + emailEditText.getText().toString());
                                loginErrorTextView.setVisibility(View.INVISIBLE);
                                Intent mainIntent = new Intent(mContext, MainActivity.class);
                                startActivity(mainIntent);
                            } else{
                                // If user was not signed in, alert them using the error TextView
                                // Log successful login attempt
                                Log.w(TAG, "signInWithEmailAndPassword:Failed", task.getException());
                                loginErrorTextView.setText(incorrectLoginError);
                                loginErrorTextView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
    }

    /**
     * This method is used to sign up a new user. This method checks first that
     * the appropriate email and password are entered. After that, the method checks
     * to see if the email is already in use. If already in use, method displays error
     * message to user. If not in use, method creates a new account with the given information
     * then logs the user in and displays successful login with a toast. Finally, continue
     * to the MainActivity.
     * @param email Email given by the user. Must contain '@' and '.'
     * @param password  Password given by user. Must be at least 6 characters in length
     */
    private void signUp(String email, String password){
        // If there was no email entered, display email error message
        if(email.length() == 0){
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(!email.contains("@")) {
            // If the entered email doesn't contain an '@' character
            // Than it is not a proper email so show an error message
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(email.lastIndexOf('.') < email.indexOf('@')){
            // If the '.' character used for the .com, .edu, etc. comes before the
            // '@' character then the email is in the wrong format
            // Alert the user with error message
            loginErrorTextView.setText(emailError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(password.length() == 0){
            // If there was no password entered, display password error message
            loginErrorTextView.setText(emptyPasswordError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else if(password.length() < 6){
            // If the password was too short, display other password error message
            loginErrorTextView.setText(shortPasswordError);
            loginErrorTextView.setVisibility(View.VISIBLE);
        } else {
            // If valid email and password are entered, attempt to create new account
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If the account was successfully created
                    // Log success
                    // Display success using a toast
                    // Transition to MainActivity
                    if(task.isSuccessful()){
                        Log.d(TAG, "createUserWithEmailAndPassword:Success");
                        toastMessage("Logged in with " + emailEditText.getText().toString());
                        loginErrorTextView.setVisibility(View.INVISIBLE);
                        Intent mainIntent = new Intent(mContext,MainActivity.class);
                        startActivity(mainIntent);
                    } else{
                        // If account was unsuccessfully created
                        // Show error message indicating that email is already in use
                        // Log sign up failure
                        loginErrorTextView.setText(usedEmailError);
                        loginErrorTextView.setVisibility(View.VISIBLE);
                        Log.w(TAG, "createUserWithEmailAndPassword:Failed", task.getException());
                    }
                }
            });
        }
    }
}
