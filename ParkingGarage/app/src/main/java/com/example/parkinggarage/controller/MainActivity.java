package com.example.parkinggarage.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.action_controllers.CreateGarageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Parking Garage App");

        Button createGarageBtn = findViewById(R.id.activity_main_createGarageBtn);
        Button loadGarageBtn = findViewById(R.id.activity_main_loadGarageBtn);

        createGarageBtn.setOnClickListener(v -> openCreateGarageActivity());

        loadGarageBtn.setOnClickListener(v -> {
            // todo
            // open file manager screen
        });
    }

    private void openCreateGarageActivity() {
        Intent intent = new Intent(this, CreateGarageActivity.class);
        startActivity(intent);
    }
}
