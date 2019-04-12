package com.example.parkinggarage.controller.user_controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.DataHolder;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.users.Attendant;

public class AttendantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant);

        Garage garage = DataHolder.getGarage();
        Attendant attendant = (Attendant) getIntent().getSerializableExtra("attendant");

        setTitle(attendant.getUsername());

        Button parkBtn = findViewById(R.id.activity_attendant_parkBtn);
        Button exitBtn = findViewById(R.id.activity_attendant_retrieveBtn);

        parkBtn.setOnClickListener(v -> {

        });

        exitBtn.setOnClickListener(v -> {

        });

    }
}
