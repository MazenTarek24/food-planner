package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.allmeal.view.HomeActivity;
import com.example.foodplanner.auth.SignInActivity;
import com.example.foodplanner.auth.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    Button signUp;
    Button signIn;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

        signUp = findViewById(R.id.btn_signUp_email);
        login = findViewById(R.id.txt_login);

        Button googleSignInButton = findViewById(R.id.btn_google_sign_in);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToHomeActivity();
        }
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, task -> {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "SignIn with Google successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "SignIn with Google failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("TAG", "Google signIn failed", e);
                Toast.makeText(MainActivity.this, "Google signIn failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
