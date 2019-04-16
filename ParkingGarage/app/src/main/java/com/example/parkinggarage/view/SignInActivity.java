package com.example.parkinggarage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.User;

public class SignInActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Garage garage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        usernameField = findViewById(R.id.activity_sign_in_usernameField);
        passwordField = findViewById(R.id.activity_sign_in_passwordField);
        Button signInButton = findViewById(R.id.activity_sign_in_signInBtn);

        garage = GarageController.getGarage();

        setTitle("Sign In");

        signInButton.setOnClickListener(v -> {
            if (checkFields()) {
                signIn();
            }
        });
    }

    private void signIn() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if (garage.getUserBag().verifyUsername(username)) {
            if (garage.getUserBag().verifyPassword(username, password)) {

                User user = garage.getUserBag().getUser(username);

                if (user instanceof Attendant) {
                    Intent intent = new Intent(this, AttendantActivity.class);
                    intent.putExtra("attendant", user);
                    openUserActivity(intent);
                } else {
                    openUserActivity(new Intent(this, ManagerActivity.class));
                }
            } else {
                passwordField.setError("Incorrect password");
            }
        } else {
            usernameField.setError("Username not found");
        }
    }

    public void openUserActivity(Intent intent) {
        clearFields();
        startActivity(intent);
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private boolean checkFields() {
        boolean result = true;

        if (isEmpty(usernameField)) {
            usernameField.setError("Enter a username");
            result = false;
        }

        if (isEmpty(passwordField)) {
            passwordField.setError("Enter a password");
            result = false;
        }

        return result;
    }

    private boolean isEmpty(EditText field) {
        return field.getText().toString().equals("");
    }

}
