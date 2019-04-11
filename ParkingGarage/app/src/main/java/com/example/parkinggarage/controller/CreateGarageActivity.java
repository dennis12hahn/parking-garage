package com.example.parkinggarage.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.parkinggarage.R;

public class CreateGarageActivity extends AppCompatActivity {

    private EditText firstNameField, lastNameField, passwordField, confirmPasswordField,
            numMotoSpacesField, numCarSpacesField, numTruckSpacesField;
    private Button createGarageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garage);

        setTitle("Create Garage");

        firstNameField = (EditText) findViewById(R.id.activity_create_garage_firstNameField);
        lastNameField = (EditText) findViewById(R.id.activity_create_garage_lastNameField);
        passwordField = (EditText) findViewById(R.id.activity_create_garage_passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.activity_create_garage_confirmPasswordField);
        numMotoSpacesField = (EditText) findViewById(R.id.activity_create_garage_numMotoSpaces);
        numCarSpacesField = (EditText) findViewById(R.id.activity_create_garage_numCarSpaces);
        numTruckSpacesField = (EditText) findViewById(R.id.activity_create_garage_numTruckSpaces);
        createGarageBtn = (Button) findViewById(R.id.activity_create_garage_createGarageBtn);

        createGarageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    createGarage();
                }
            }
        });
    }

    private void createGarage() {
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String password = passwordField.getText().toString();
        int numMotoSpaces = Integer.parseInt(numMotoSpacesField.getText().toString());
        int numCarSpaces = Integer.parseInt(numCarSpacesField.getText().toString());
        int numTruckSpaces = Integer.parseInt(numTruckSpacesField.getText().toString());


    }

    private boolean checkFields() {
        return false;
    }
}
