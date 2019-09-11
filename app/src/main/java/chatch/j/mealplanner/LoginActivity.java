package chatch.j.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * This is the Login Activity which is used to allow the user to sign into their
 * existing accounting or to sign up with a new account. The user can only have one
 * account per email. This activity utilizes Firebase for authentification and will
 * give the user access to the Recipes that are save to the database under their account.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
