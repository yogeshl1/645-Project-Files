// LoginActivity.java
package com.example.yourpackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView createAccountTextView = findViewById(R.id.createAccountTextView);
        Button loginButton = findViewById(R.id.loginButton);

        // Replace with your logo resource
        logoImageView.setImageResource(R.drawable.unihub_logo);

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the activity where users can create a new account
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add login logic here
            }
        });
    }
}
