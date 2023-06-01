package com.example.foodplanner.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.allmeal.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signIn = findViewById(R.id.btn_signIn);


        if (mAuth.getCurrentUser() != null)
        {
            gotoHomeActivity();
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString();

                if (isValidEmail(emailText) && isValidPassword(passwordText)) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        gotoHomeActivity();
                                        Toast.makeText(SignInActivity.this, "Sign-in successful", Toast.LENGTH_LONG).show();

                                    } else {
                                        String errorMessage = task.getException().getMessage();
                                        Log.d("error", errorMessage);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void gotoHomeActivity() {
        Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {

        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

}
