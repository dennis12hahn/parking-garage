package com.example.parkinggarage.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.garage.Garage;

public class MainActivity extends AppCompatActivity {

    private Button createGarageBtn, loadGarageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Parking Garage App");

        createGarageBtn = (Button) findViewById(R.id.activity_main_createGarageBtn);
        loadGarageBtn = (Button) findViewById(R.id.activity_main_loadGarageBtn);

        createGarageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateGarageActivity();
            }
        });

        loadGarageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo
                // open file manager screen
            }
        });
    }

    private void openCreateGarageActivity() {
        Intent intent = new Intent(this, CreateGarageActivity.class);
        startActivity(intent);
    }
}
