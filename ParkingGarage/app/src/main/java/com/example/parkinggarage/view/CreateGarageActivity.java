package com.example.parkinggarage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.garage.Garage;

public class CreateGarageActivity extends AppCompatActivity {

    private EditText firstNameField, lastNameField, passwordField, confirmPasswordField,
            numMotoSpacesField, numCarSpacesField, numTruckSpacesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garage);

        setTitle("Create Garage");

        firstNameField = findViewById(R.id.activity_create_garage_firstNameField);
        lastNameField = findViewById(R.id.activity_create_garage_lastNameField);
        passwordField = findViewById(R.id.activity_create_garage_passwordField);
        confirmPasswordField = findViewById(R.id.activity_create_garage_confirmPasswordField);
        Button setPricesBtn = findViewById(R.id.activity_create_garage_setPricesBtn);

        setPricesBtn.setOnClickListener(v -> {
            if (checkFields()) {
                Garage garage = createGarage();

            }
        });
    }

    private Garage createGarage() {
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String password = passwordField.getText().toString();
        Garage garage = new Garage(firstName, lastName, password);

        String message = "The manager's username is " + garage.getManager().getUsername();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        makePrices(garage);
        Toast.makeText(this, "Prices set", Toast.LENGTH_SHORT).show();

        generateSpaces(garage);
        Toast.makeText(this, "Spaces generated", Toast.LENGTH_SHORT).show();


        return garage;
    }

    private void makePrices(Garage garage) {
        setContentView(R.layout.activity_set_prices);

    }

    private void generateSpaces(Garage garage) {
        setContentView(R.layout.activity_number_spaces);
        numMotoSpacesField = findViewById(R.id.activity_number_spaces_numMotoSpacesField);
        numCarSpacesField = findViewById(R.id.activity_number_spaces_numCarSpacesField);
        numTruckSpacesField = findViewById(R.id.activity_number_spaces_numTruckSpacesField);

        Button continueBtn = findViewById(R.id.activity_number_spaces_continueBtn);

        continueBtn.setOnClickListener(v -> {
            if (checkNumberSpacesFields()) {
                int numMotoSpaces = Integer.parseInt(numMotoSpacesField.getText().toString());
                int numCarSpaces = Integer.parseInt(numCarSpacesField.getText().toString());
                int numTruckSpaces = Integer.parseInt(numTruckSpacesField.getText().toString());
                garage.generateSpaces(numMotoSpaces, numCarSpaces, numTruckSpaces);
            }
        });
    }

    private void openSignInActivity(Garage garage) {
        Intent intent = new Intent(this, SignInActivity.class);
        GarageController.setGarage(garage);
        startActivity(intent);
    }

    private boolean checkFields() {
        boolean result = true;

        if (isEmpty(firstNameField)) {
            firstNameField.setError("Enter a first name");
            result = false;
        }

        if (isEmpty(lastNameField)) {
            lastNameField.setError("Enter a last name");
            result = false;
        }

        if (isEmpty(passwordField)) {
            passwordField.setError("Enter a password");
            result = false;
        }

        if (isEmpty(confirmPasswordField)) {
            confirmPasswordField.setError("Confirm your password");
            result = false;
        }

        if (!passwordField.getText().toString().equals(confirmPasswordField.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Make sure your passwords match", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    private boolean checkNumberSpacesFields() {
        boolean result = true;

        if (isEmpty(numMotoSpacesField)) {
            numMotoSpacesField.setError("Enter total motorcycle spaces");
            result = false;
        }

        if (isEmpty(numCarSpacesField)) {
            numCarSpacesField.setError("Enter total car spaces");
            result = false;
        }

        if (isEmpty(numTruckSpacesField)) {
            numTruckSpacesField.setError("Enter total truck spaces");
            result = false;
        }

        return result;
    }

    private boolean isEmpty(EditText field) {
        return field.getText().toString().equals("");
    }
}
