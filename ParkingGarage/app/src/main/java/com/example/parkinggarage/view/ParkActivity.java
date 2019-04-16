package com.example.parkinggarage.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String vehicleType;
    private EditText licenseField;
    private Spinner vehicleTypeSpinner;
    private Attendant attendant;
    private Vehicle vehicle;
    private Space space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        setTitle("Park");

        String username = (String) getIntent().getSerializableExtra("attendant_username");
        attendant = (Attendant) GarageController.getGarage().getUserBag().getUser(username);

        licenseField = findViewById(R.id.activity_park_licenseField);
        Button parkBtn = findViewById(R.id.activity_park_parkBtn);
        createVehicleSpinner();


        parkBtn.setOnClickListener(v -> {
            if (checkFields()) {
                vehicle = createVehicle();
                space = GarageController.getGarage().getClosestSpace(vehicle, "peek");

                if (space == null) {
                    Toast.makeText(this, "No spaces available", Toast.LENGTH_SHORT).show();
                } else {
                    displayOption();
                }

            }
        });
    }

    private void displayOption() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        StringBuilder title = new StringBuilder();
        StringBuilder message = new StringBuilder();

        setTitleAndMessage(title, message);

        builder.setTitle(title.toString());
        builder.setMessage(message.toString());

        builder.setPositiveButton("Confirm",
                ((dialog, which) -> {
                    Document doc = attendant.park(vehicle, GarageController.getGarage());

                    if (doc == null) {
                        Toast.makeText(this, "Unable to park vehicle", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("document", doc);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }

                }));

        builder.setNegativeButton("Cancel",
                (dialog, which) -> {
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setTitleAndMessage(StringBuilder title, StringBuilder message) {
        if (vehicle.getSize() < space.getSize()) {
            title.append("Only Larger Space is Available");
            message.append("This space is larger than necessary and costs more but is the only one available. ");
        } else {
            title.append("Space Found");
        }

        if (LocalDateTime.now().getHour() < 8) {
            message.append("The price of this space is $" + space.getEarlyBirdPrice() + " for the day. ");
        } else {
            message.append("The rate of this space is $" + space.getRate() + "/hr. ");
        }
        message.append("Park in this space?");
    }

    private void createVehicleSpinner() {
        vehicleTypeSpinner = findViewById(R.id.activity_park_vehicleTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapter);
        vehicleTypeSpinner.setOnItemSelectedListener(this);
    }

    private Vehicle createVehicle() {
        switch (vehicleType) {
            case "Motorcycle":
                return new Motorcycle(licenseField.getText().toString());
            case "Car":
                return new Car(licenseField.getText().toString());
            case "Truck":
                return new Truck(licenseField.getText().toString());
        }
        return null;
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
