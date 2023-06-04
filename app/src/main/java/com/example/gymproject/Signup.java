package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private EditText firstNameEditText, secondNameEditText, emailEditText, userTypeEditText;
    private Button signupButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Get references to views
        firstNameEditText = findViewById(R.id.editTextTextPersonName3);
        secondNameEditText = findViewById(R.id.editTextTextPersonName5);
        emailEditText = findViewById(R.id.editTextTextEmailAddress5);
        userTypeEditText = findViewById(R.id.editTextTextPersonName7);
        signupButton = findViewById(R.id.button3);

        // Set click listener for signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from EditText fields
                String firstName = firstNameEditText.getText().toString().trim();
                String secondName = secondNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String userType = userTypeEditText.getText().toString().trim();

                // Create a map with user data
                Map<String, Object> user = new HashMap<>();
                user.put("firstName", firstName);
                user.put("secondName", secondName);
                user.put("email", email);
                user.put("userType", userType);

                // Add user data to Firestore
                db.collection("PlayerDetails")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Signup.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                finish(); // Finish the Signup activity
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this, "Error creating account. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
