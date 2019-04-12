package com.example.parkinggarage.controller.action_controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.parkinggarage.R;

public class ParkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String vehicleType;
    private EditText licenseField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        setTitle("Park");

        Spinner vehicleTypeSpinner = findViewById(R.id.activity_park_vehicleTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapter);
        vehicleTypeSpinner.setOnItemSelectedListener(this);

        licenseField = findViewById(R.id.activity_park_licenseField);
        Button parkBtn = findViewById(R.id.activity_park_parkBtn);

        parkBtn.setOnClickListener(v -> {
            if (checkFields()) {

            }
        });
    }

    private boolean checkFields() {
        boolean result = true;

        if (isEmpty(licenseField)) {
            licenseField.setError("Enter a license plate number");
            result = false;
        }

        if (vehicleType.equals("")) {
            Toast.makeText(this, "Select a vehicle type", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    private boolean isEmpty(EditText field) {
        return field.getText().equals("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        vehicleType = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
