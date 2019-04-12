package com.example.parkinggarage.controller.user_controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.action_controllers.CreateAttendantActivity;
import com.example.parkinggarage.controller.DataHolder;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.users.Attendant;

public class ManagerActivity extends AppCompatActivity {

    private Garage garage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        garage = DataHolder.getGarage();

        Button signOutBtn = findViewById(R.id.activity_manager_signOutBtn);
        Button createAttendantBtn = findViewById(R.id.activity_manager_createAttendantBtn);

        setTitle(garage.getManager().getUsername());

        signOutBtn.setOnClickListener(v -> {
            finish();
        });

        createAttendantBtn.setOnClickListener(v -> {
            openCreateAttendantView();
        });

    }

    private void openCreateAttendantView() {
        Intent intent = new Intent(this, CreateAttendantActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Attendant attendant = (Attendant) data.getSerializableExtra("attendant");
                String message = "Attendant " + attendant.getUsername() + " created";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                garage.getUserBag().addAttendant(attendant);
            }

            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
