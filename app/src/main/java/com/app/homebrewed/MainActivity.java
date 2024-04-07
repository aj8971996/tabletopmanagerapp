package com.app.homebrewed;

import static com.app.homebrewed.R.id.btn_signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // For a standard Button
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private AppCompatImageButton signInButton; // Change from 'Button' to 'AppCompatImageButton'
    // Add a variable for the sign-in button

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.username_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        signInButton = findViewById(btn_signin); // Find the sign-in button

        // Attach the click listener to the signInButton
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(); // Call your existing signIn method
            }
        });
    }

    public void signIn() {
/*
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Basic input validation (add more if needed)
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success!
                            Toast.makeText(MainActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();

                            // Navigate to the next activity after successful sign-in
                            Intent intent = new Intent(MainActivity.this, CharacterListActivty.class);
                            startActivity(intent);

                        } else {
                            // Sign in failed, display error message
                            Toast.makeText(MainActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
*/
        // Start CharacterListActivty directly
        Intent intent = new Intent(MainActivity.this, CharacterListActivity.class);
        startActivity(intent);
    }
}

