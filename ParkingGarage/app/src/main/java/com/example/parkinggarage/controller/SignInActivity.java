package com.example.parkinggarage.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parkinggarage.R;

public class SignInActivity extends AppCompatActivity {

    private TextView usernameField, passwordField;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTitle("Sign In");

        usernameField = (TextView) findViewById(R.id.activity_sign_in_usernameField);
        passwordField = (TextView) findViewById(R.id.activity_sign_in_passwordField);
        signInButton = (Button) findViewById(R.id.activity_sign_in_signInBtn);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    signIn();
                }
            }
        });
    }

    private void signIn() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // todo
    }

    private boolean checkFields() {
        if (usernameField.getText() == "") {
            return false;
        }

        if (passwordField.getText() == "") {
            return false;
        }

        return true;
    }
}
