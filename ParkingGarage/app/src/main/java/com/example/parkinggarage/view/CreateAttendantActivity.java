package com.example.parkinggarage.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.users.Attendant;

public class CreateAttendantActivity extends AppCompatActivity {

    private EditText firstNameField, lastNameField, passwordField, confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_attendant);

        setTitle("Create Attendant");

        Intent intent = getIntent();

        firstNameField = findViewById(R.id.activity_create_attendant_firstNameField);
        lastNameField = findViewById(R.id.activity_create_attendant_lastNameField);
        passwordField = findViewById(R.id.activity_create_attendant_passwordField);
        confirmPasswordField = findViewById(R.id.activity_create_attendant_confirmPasswordField);
        Button createAttendantBtn = findViewById(R.id.activity_create_attendant_createAttendantBtn);

        createAttendantBtn.setOnClickListener(v -> {
            if (checkFields()) {
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String password = passwordField.getText().toString();

                Intent resultIntent = new Intent();
                Attendant attendant = new Attendant(firstName, lastName, password);
                resultIntent.putExtra("attendant", attendant);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });


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

        if (!confirmPasswordField.getText().toString().equals(passwordField.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Make sure your passwords match", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    private boolean isEmpty(EditText field) {
        return field.getText().toString().equals("");
    }
}
