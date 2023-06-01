package com.example.foodplanner.auth;

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
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.btn_signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (isValidEmail(emailText) && isValidPassword(passwordText)) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(SignUpActivity.this, "User added successfully", Toast.LENGTH_LONG).show();

                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    Log.d("error", errorMessage);
                                }
                            });
                } else {
                    Toast.makeText(SignUpActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}
